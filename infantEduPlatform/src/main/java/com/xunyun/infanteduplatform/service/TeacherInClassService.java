package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.TeacherInClassEntity;
import com.xunyun.infanteduplatform.persistent.TeacherInClassMapper;
import com.xunyun.infanteduplatform.persistent.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师任教班级Service
 * Created by Pooh on 2015/12/21.
 */
@Service("TeaInClassService")
public class TeacherInClassService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherInClassMapper teacherInClassMapper;
    public Integer saveTeaInClass(TeacherInClassEntity teacherInClass){
        return userMapper.saveTClass(teacherInClass);
    }

    public Integer deleteTeaInClass(Integer userId){
        return userMapper.delTClassById(userId);
    }

    public List<TeacherInClassEntity> queryTeaInClass(TeacherInClassEntity teacherInClass){
        return teacherInClassMapper.queryTeaInClass(teacherInClass);
    }

    public Integer updateTeaInClass(TeacherInClassEntity teacherInClass){
        return teacherInClassMapper.updateTeaInClass(teacherInClass);
    }

    public ClassManageEntity queryClassByUserId(SysUserInorg sysUserInorg){
        return teacherInClassMapper.queryClassByUserId(sysUserInorg);
    };
}
