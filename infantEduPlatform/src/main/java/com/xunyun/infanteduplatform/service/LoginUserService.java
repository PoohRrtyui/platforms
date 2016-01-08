package com.xunyun.infanteduplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.persistent.LoginUserMapper;

@Service("loginUserService")
public class LoginUserService
{

    @Autowired
    private LoginUserMapper loginUserMapper;
    
  /**
   *  根据登陆账号获取用户信息
   * @param username
   * @return
   */
  public LoginUserInfo findLoginUserById(LoginUserInfo loginUserInfo) {
    return this.loginUserMapper.findLoginUserById(loginUserInfo);
  }
  
}
