package com.xunyun.infanteduplatform.persistent;

import java.util.Map;

import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {

  public int register(SysUserLogin login);

  public int updateValidate(Map<String,Object> map);
  
  public int addUserInfo(SysUserInfo userInfo);

  public int addUserInOrg(SysUserInorg userInorg);
  
  public int registerValidate(Map<String, Object> map);
 
}
