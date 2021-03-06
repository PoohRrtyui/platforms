package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.persistent.UserInOrgMapper;
import com.xunyun.infanteduplatform.persistent.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户所属机构Service
 * Created by Pooh on 2015/12/21.
 */
@Service("UserInOrgService")
public class UserInOrgService {

    @Autowired
    private UserInOrgMapper userInOrgMapper;
    public Integer updateUserInOrg(SysUserInorg sysUserInorg){
        return this.userInOrgMapper.updateUserInOrg(sysUserInorg);
    }

    public SysUserInorg queryUserById(SysUserInorg sysUserInorg){
        return this.userInOrgMapper.queryUserById(sysUserInorg);
    }
}
