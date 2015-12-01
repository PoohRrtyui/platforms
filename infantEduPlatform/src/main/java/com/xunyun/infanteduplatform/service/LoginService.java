package com.xunyun.infanteduplatform.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.persistent.LoginMapper;


@Service("loginService")
public class LoginService {
	
	
	@Autowired
	private LoginMapper loginMapper;
	
	
    /**
     *  根据登陆账号获取用户信息
     * @param username
     * @return
     */
	public Integer entry(SysUserLogin loginer) {
	   return this.loginMapper.entry(loginer);
	}
}
