package com.xunyun.infanteduplatform.controller.oauth;

import com.xunyun.infanteduplatform.service.OAuthService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取token值
 * Created by Pooh on 2016/1/5.
 */
@Controller @RequestMapping("authToken") public class AccessTokenController {

    @Autowired private OAuthService oAuthService;

    @RequestMapping(value = "/accessToken", produces = "application/json")
    public void accessToken(HttpServletRequest request, HttpServletResponse servletResponse)
        throws OAuthSystemException, IOException {

        try {
            //构建OAuth请求
            OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
            PrintWriter out = servletResponse.getWriter();
            servletResponse.setContentType(OAuth.ContentType.JSON);
            //生成访问令牌
            OAuthIssuerImpl authIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            String accessToken = authIssuerImpl.accessToken();
            String refreshToken = authIssuerImpl.refreshToken();
            //验证类型CLIENT_CREDENTIALS
            if (tokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                .equals(GrantType.CLIENT_CREDENTIALS.toString())) {

                //生成OAuth响应
                OAuthResponse response = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken).setRefreshToken(refreshToken)
                    .setExpiresIn(String.valueOf(oAuthService.getExpireIn())).buildJSONMessage();

                //输出OAuth信息(access_token,refresh_token,expires_in)
                servletResponse.setStatus(response.getResponseStatus());
                out.print(response.getBody());
                out.flush();
            } else if (GrantType.AUTHORIZATION_CODE.toString()
                .equals(tokenRequest.getParam(OAuth.OAUTH_GRANT_TYPE))) {
                String authCode = tokenRequest.getParam(OAuth.OAUTH_CODE);
                if (!oAuthService.checkAuthCode(authCode)) {
                    OAuthResponse response =
                        OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("错误的授权码").buildJSONMessage();

                    //输出OAuth信息(access_token,refresh_token,expires_in)
                    servletResponse.setStatus(response.getResponseStatus());
                    out.print(response.getBody());
                    out.flush();
                } else {
                    //生成OAuth响应
                    OAuthResponse response =
                        OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK)
                            .setAccessToken(accessToken).setRefreshToken(refreshToken)
                            .setExpiresIn(String.valueOf(oAuthService.getExpireIn()))
                            .buildJSONMessage();

                    //输出OAuth信息(access_token,refresh_token,expires_in)
                    servletResponse.setStatus(response.getResponseStatus());
                    out.print(response.getBody());
                    out.flush();
                }
            } else {
                OAuthResponse response =
                    OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_GRANT)
                        .setErrorDescription("grant_type错误").buildJSONMessage();

                //输出OAuth信息(access_token,refresh_token,expires_in)
                servletResponse.setStatus(response.getResponseStatus());
                out.print(response.getBody());
                out.flush();
            }



        } catch (OAuthProblemException ex) {
            //exception
            OAuthResponse response = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                .location(ex.getRedirectUri()).error(ex).buildJSONMessage();

            servletResponse.setContentType(OAuth.ContentType.JSON);
            servletResponse.setStatus(response.getResponseStatus());
            PrintWriter out = servletResponse.getWriter();
            out.print(response.getBody());
            out.flush();
        }

    }
}
