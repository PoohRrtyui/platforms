package com.xunyun.infanteduplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.persistent.changePwdMapper;

@Service("changepwdService")
public class changePwdService {
	@Autowired
	private changePwdMapper changepwdMapper;
	
	//获取用户信息
	public String searPwd(SysUserLogin sysUserLogin) {
		   return this.changepwdMapper.searPwd(sysUserLogin);
		}
	
	//修改密码
	public int updatePwd(SysUserLogin updatepwd) {
		   return this.changepwdMapper.updatePwd(updatepwd);
		}
}
