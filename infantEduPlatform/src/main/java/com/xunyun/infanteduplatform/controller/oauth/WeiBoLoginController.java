package com.xunyun.infanteduplatform.controller.oauth;

import com.xunyun.infanteduplatform.service.AccountLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 新浪微博登录
 * Created by Pooh on 2015/12/31.
 */
@Controller
@RequestMapping(value="weibo")
public class WeiBoLoginController {
    @Autowired public AccountLoginService accountLoginService;
    /**
     *
     * 新浪微博登录界面
     *
     * @param session
     *  @return
     * @throws WeiboException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginSinaWeibo(HttpServletRequest request, HttpSession session)
        throws WeiboException {
        session.setAttribute("login_current_url", request.getHeader("Referer"));
        weibo4j.Oauth oauth = new weibo4j.Oauth();
        String url = oauth.authorize("code", "");
        return "redirect:" + url;
    }

    /**
     *
     * 新浪微博登录操作
     *
     * @param session
     *  @return
     * @throws WeiboException
     */
    @RequestMapping(value = "sinaAfterLogin", method = RequestMethod.GET)
    public String loginSinaWeiboAction(HttpSession session,
        @RequestParam String code,HttpServletRequest request) throws WeiboException {
        String url = (String) session.getAttribute("login_current_url");
        session.removeAttribute("login_current_url");
        weibo4j.Oauth oauth = new weibo4j.Oauth();
        weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
        String uid = accessToken.getUserUid();
        accountLoginService.accountRegister(uid, "sinaweibo",request);
        return "redirect:" + url;
    }
}
