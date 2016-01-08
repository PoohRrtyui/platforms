package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.domain.UserInClassEntity;
import com.xunyun.infanteduplatform.persistent.UserInClassMapper;
import com.xunyun.infanteduplatform.persistent.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学生所属班级Service
 * Created by Pooh on 2015/12/22.
 */
@Service("UserInClass")
public class UserInClassService {
    @Autowired UserMapper userMapper;
    @Autowired UserInClassMapper userInClassMapper;

    public Integer saveUserInClass(UserInClassEntity userInClass){
        return userMapper.saveUClass(userInClass);
    }
    public Integer deleteUserInClass(Integer userId){
        return userMapper.delUClassById(userId);
    }

    public Integer updateUserInClass(UserInClassEntity userInClass){
        return userInClassMapper.updateUserInClass(userInClass);
    }

    public List<SysUserLogin> queryUsersInClass(Map<String,Integer> map){
        return userInClassMapper.queryUsersInClass(map);
    }

    public ClassManageEntity queryClassByUserId(SysUserInorg sysUserInorg) {
        return userInClassMapper.queryClassByUserId(sysUserInorg);
    }
}
