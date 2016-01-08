package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.SysDepartmentInfo;
import com.xunyun.infanteduplatform.domain.SysUserInDepartment;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.persistent.UserInDeptMapper;
import com.xunyun.infanteduplatform.persistent.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户所在部门Service
 * Created by Pooh on 2015/12/22.
 */
@Service("UserInDeptService")
public class UserInDepartmentService {

    @Autowired UserMapper userMapper;
    @Autowired UserInDeptMapper userInDeptMapper;
    public Integer saveUserInDept(SysUserInDepartment userInDepartment){
        return userMapper.saveUDep(userInDepartment);
    }

    public Integer deleteUserInDept(Integer userId){
        return userMapper.delUDepbyId(userId);
    }

    public Integer updateUserInDept(SysUserInDepartment sysUserInDepartment){
        return userInDeptMapper.updateUserInDept(sysUserInDepartment);
    }

    public SysDepartmentInfo queryUserInDeptByUserId(SysUserInorg sysUserInorg){
        return userInDeptMapper.queryUserInDeptByUserId(sysUserInorg);
    }
}
