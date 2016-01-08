package com.xunyun.infanteduplatform.service;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import com.xunyun.infanteduplatform.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.persistent.RegisterMapper;

@Service("register") public class RegisterService {

    @Resource private RegisterMapper registerMapper;


    public int saveUserLogin(SysUserLogin userRegister) {
        userRegister.setName(userRegister.getName().toLowerCase());
        userRegister.setPassword(MD5Util.MD5(userRegister.getPassword()));
        return registerMapper.register(userRegister);
    }

    public int updateValidate(Map<String, Object> map) {

        return registerMapper.updateValidate(map);
    }

    /**
     * 用户注册 添加用户到userLogin,userInOrg,userInfo
     *
     * @param userLogin 用户信息(注册名 name,密码 password,手机 mobile)
     * @return
     */
    public int register(SysUserLogin userLogin) {
        userLogin.setName(userLogin.getName().toLowerCase());
        userLogin.setPassword(MD5Util.MD5(userLogin.getPassword()));
        registerMapper.register(userLogin);
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setUserid(userLogin.getId());
        userInfo.setUsername(userLogin.getName().toLowerCase());
        userInfo.setMobile(userLogin.getMobile());
        userInfo.setGendercode(0);
        userInfo.setIdtype(01);
        userInfo.setDeleteflg(0);
        userInfo.setCreationtime(Calendar.getInstance().getTime());
        String by = userLogin.getId().toString();
        userInfo.setCreatedby(by);
        userInfo.setLastupdatetime(Calendar.getInstance().getTime());
        userInfo.setLastupdatedby(by);
        SysUserInorg userInorg = new SysUserInorg();
        userInorg.setUserId(userLogin.getId());
        userInorg.setUserType(0);
        userInorg.setIsAdmin(0);
        userInorg.setIsAuthentication(0);
        registerMapper.addUserInfo(userInfo);
        return registerMapper.addUserInOrg(userInorg);
    }

    public int registerValidate(Map<String, Object> map) {

        return registerMapper.registerValidate(map);
    }

}
