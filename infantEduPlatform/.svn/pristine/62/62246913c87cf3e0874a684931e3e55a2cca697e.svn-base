package com.xunyun.infanteduplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.persistent.userMapper;

@Service("userService")
public class userService
{

	@Autowired
	private userMapper userMapper;
    
	/**
     *  根据登陆账号获取用户信息
     * @param username
     * @return
     */
    public SysUserInfo getUserByUsername(String username){
    	return this.userMapper.getUserByUsername(username);
    }

}
