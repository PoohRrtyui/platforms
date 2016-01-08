package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.SysUserLogin;

public interface changePwdMapper {
	public String searPwd(SysUserLogin sysUserLogin);
	public int updatePwd(SysUserLogin updatepwd);
}
