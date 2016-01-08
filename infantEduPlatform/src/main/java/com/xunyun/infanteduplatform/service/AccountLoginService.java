package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.AccountBinding;
import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.persistent.AccountLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 第三方登录Service
 * Created by Pooh on 2015/12/30.
 */
@Service("accountLoginService") public class AccountLoginService {
    @Autowired public AccountLoginMapper accountLoginMapper;
    @Autowired public RegisterService registerService;
    @Autowired public LoginUserService loginUserService;

    public AccountBinding queryAccount(AccountBinding accountBinding) {
        return accountLoginMapper.queryAccount(accountBinding);
    }

    public void saveAccount(AccountBinding accountBinding) {
        accountLoginMapper.saveAccount(accountBinding);
    }

    public void accountRegister(String openID,String appCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(openID);
        AccountBinding accountBinding = new AccountBinding();
        accountBinding.setAccount(openID);
        accountBinding.setAccount(appCode);
        accountBinding = this.queryAccount(accountBinding);
        if (null == accountBinding) {//存在用户，登录并跳转
            SysUserLogin userLogin = new SysUserLogin();
            userLogin.setName(String.format("plat%s", (new SimpleDateFormat("yyyyMMddHHmmssSSS"))
                .format(Calendar.getInstance().getTime())));
            userLogin.setPassword("125312");
            userLogin.setMobile("");
            registerService.register(userLogin);
            /**
             * 1.添加第三方账号绑定
             * 2.登录
             */
            accountBinding = new AccountBinding();
            accountBinding.setUserId(userLogin.getId());
            accountBinding.setAppCode(appCode);
            accountBinding.setAppType(0);
            accountBinding.setAccount(openID);
            accountBinding.setBindingTime(Calendar.getInstance().getTime());
            this.saveAccount(accountBinding);
        }
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        //获取登陆用户的userId
        loginUserInfo.setUserid(accountBinding.getUserId());
        //根据用户id查询用户信息，并放到session中
        session.setAttribute("loginUserInfo", loginUserService.findLoginUserById(loginUserInfo));
    }
}
