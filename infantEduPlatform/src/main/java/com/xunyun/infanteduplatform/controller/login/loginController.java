package com.xunyun.infanteduplatform.controller.login;

import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.service.LoginService;
import com.xunyun.infanteduplatform.service.LoginUserService;
import com.xunyun.infanteduplatform.service.UserService;
import com.xunyun.infanteduplatform.utils.MD5Util;
import com.xunyun.infanteduplatform.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 类说明:<br/>
 * 创建人:羊鑫<br/>
 * 修改人:丁焕
 * 创建日期:2015年11月18日<br/>
 * 修改时间:2015年12月29日
 */
@Controller @RequestMapping("login") public class loginController {

    @Autowired private LoginService loginService;

    @Autowired private LoginUserService loginUserService;

    @Autowired private UserService userService;

    public static void main(String[] args) {

    }

    /**
     * 访问登陆页面 方法描述:<br>
     * 创建人:羊鑫<br>
     * 创建日期:2015年11月18日<br>
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/prelogin") public ModelAndView prelogin(String appCode) {
        ModelAndView mav = new ModelAndView("pages/login/login");
        if (appCode != null & appCode != "") {
            mav.addObject("appCode", appCode);
        }
        return mav;
    }

    /**
     * 登录验证 // * 方法描述:<br>
     * 创建人:羊鑫<br>
     * 创建日期:2015年11月18日<br>
     *

     *                       appCode:接入平台提供的标示，url：返回页面，完成验证后会同步给接入平台
     */
    @RequestMapping(value = "/process", method = RequestMethod.POST) public @ResponseBody
    Result loginServer(HttpServletRequest request, String username, String password,
        String appCode) {
        try {

            Result result = new Result();
            SysUserLogin loginer = new SysUserLogin();
            loginer.setName(username.toLowerCase());
            loginer.setPassword(MD5Util.MD5(password));
            Integer logged = this.loginService.entry(loginer);
            if (logged == 0) {
                result.setErrCode(Result.CODE.FAILURE);
            }
            //seesion 保存用户name，后期需要其他字段，可以继续添加
            HttpSession session = request.getSession();

            LoginUserInfo loginUserInfo = new LoginUserInfo();
            //获取登陆用户的userid
            loginUserInfo.setUserid(logged);
            //根据用户id查询用户信息，并放到session中
            session
                .setAttribute("loginUserInfo", loginUserService.findLoginUserById(loginUserInfo));

            //如果平台标示不存在，则是通过统一平台直接登录，不需要appCode&url;
            if (appCode == null || appCode == "") {
                return result;
            } else {
                String token = String.valueOf(logged);
                return result;
            }
        } catch (Exception e) {
            return new Result(Result.CODE.FAILURE, "登录失败！");
        }
    }

    /**
     * 测试session获取user属性// * 方法描述:<br>
     * 创建人:羊鑫<br>
     * 创建日期:2015年11月18日<br>
     *
     *
     */
    @RequestMapping(value = "/test") public ModelAndView test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = String.valueOf(session.getAttribute("userId"));
        String user_name = String.valueOf(session.getAttribute("userName"));
        System.out.println(user_name);
        return new ModelAndView("login/login");
    }

    // /**
    // * 登录验证
    // * 方法描述:<br>
    // * 创建人:羊鑫<br>
    // * 创建日期:2015年11月18日<br>
    // * @param <ResponseView>
    // * @return
    // * @throws Exception<br>
    // */
    // @RequestMapping("/process")
    // public @ResponseBody ResponseView loginServer(String username,String password){
    // rv = new ResponseView();
    // try{
    // //shiro加入身份验证
    // //获取当前用户
    // Subject subject = SecurityUtils.getSubject();
    // UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    // try {
    // subject.login(token);
    // } catch (AuthenticationException e) {
    // logger.warn("登陆失败：" + e.getMessage());
    // result = new Result(Result.CODE.FAILURE,"身份验证失败，您的账号或密码不正确!");
    // }
    // }catch(Exception e){
    // logger.error("登陆出错：" + e.getMessage() ,e);
    // result = new Result(Result.CODE.FAILURE,"登陆失败!");
    // }
    // return rv;
    // }



}
