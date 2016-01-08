package com.xunyun.infanteduplatform.controller.oauth;

import com.xunyun.infanteduplatform.constants.OAuth2Constants;
import com.xunyun.infanteduplatform.service.OAuthService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * apache Oltu OAuto 2.0 Authorization Server
 * * Created by Pooh on 2016/1/4.
 */
@Controller @RequestMapping("oAuth") public class OauthController {

    @Autowired private OAuthService oAuthService;

    /**
     * 用户访问客户端，后者将前者导向认证服务器。此为认证服务器，判断是否授权
     * @param request HttpServletRequest
     * @return 重定向URI redirectURI & 授权码 authCode & 当前状态 state
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     */
    @RequestMapping("/authorize") public ModelAndView Authorize(HttpServletRequest request)
        throws OAuthSystemException, OAuthProblemException {
        ModelAndView mav = new ModelAndView();
        /*
        String username, String webKey, String scope, String state,String diasplay
        构建OAuth请求
        */
        OAuthAuthzRequest oAuthzRequest = new OAuthAuthzRequest(request);
        //获取OAuth客户端Id
        String clientId = oAuthzRequest.getClientId();
        //校验客户端Id是否正确
        if (!oAuthService.checkClientId(clientId)) {
            OAuthResponse oAuthResponse =
                OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                    .setErrorDescription("无效的客户端Id").buildJSONMessage();
            mav.addObject(OAuth2Constants.OAUTH_AUTHORIZE_FAILED_KEY, "无效的客户端Id");
            mav.setViewName("forward:/oauth2/authorizefailed");
            return mav;
            //			return new ResponseEntity(oAuthResponse.getBody(), HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
        }

        String username = "admin";
        String state = oAuthzRequest.getState();
        //生成授权码
        String authCode = null;
        String responseType = oAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
        //ResponseType仅支持CODE和TOKEN
        if (responseType.equals(ResponseType.CODE.toString())) {
            OAuthIssuerImpl oAuthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            authCode = oAuthIssuerImpl.authorizationCode();
            oAuthService.addAuthCode(authCode, username);
        }

        //构建OAuth响应
        OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
            OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

        //设置授权码
        builder.setCode(authCode);

        //获取客户端重定向地址
        String redirectURI = oAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

        //构建响应
        OAuthResponse response = builder.location(redirectURI).buildBodyMessage();
        //根据OAuthResponse返回ResponseEntity响应
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setLocation(new URI(response.getLocationUri()));
            //			return new ResponseEntity<>(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mav.addObject(OAuth.OAUTH_CODE, authCode);
        mav.addObject(OAuth.OAUTH_STATE, state);
        mav.setViewName("redirect:" + redirectURI);
        return mav;
    }

    @RequestMapping("/oAuthLogin")
    public ModelAndView oAuthLogin(HttpServletRequest request)
        throws OAuthProblemException, OAuthSystemException {
        ModelAndView mav = new ModelAndView();
        OAuthAuthzRequest oAuthzRequest = new OAuthAuthzRequest(request);
        String username = oAuthzRequest.getParam(OAuth.OAUTH_USERNAME);
        String state = oAuthzRequest.getState();
        //生成授权码
        String authCode = null;
        String responseType = oAuthzRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
        //ResponseType仅支持CODE和TOKEN
        if (responseType.equals(ResponseType.CODE.toString())) {
            OAuthIssuerImpl oAuthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            authCode = oAuthIssuerImpl.authorizationCode();
            oAuthService.addAuthCode(authCode, username);
        }
        //构建OAuth响应
        OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
            OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
        //设置授权码
        builder.setCode(authCode);

        //获取客户端重定向地址
        String redirectURI = oAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

        //构建响应
        OAuthResponse response = builder.location(redirectURI).buildBodyMessage();
        //根据OAuthResponse返回ResponseEntity响应
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setLocation(new URI(response.getLocationUri()));
            //			return new ResponseEntity<>(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mav.addObject(OAuth.OAUTH_CODE, authCode);
        mav.addObject(OAuth.OAUTH_STATE, state);
        mav.setViewName("redirect:" + redirectURI);
        return mav;
    }
}
