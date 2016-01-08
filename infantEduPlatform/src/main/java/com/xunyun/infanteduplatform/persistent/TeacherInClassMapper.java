package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.TeacherInClassEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师任教班级Mapper
 * Created by Pooh on 2015/12/23.
 */
@Repository
public interface TeacherInClassMapper {
    List<TeacherInClassEntity> queryTeaInClass(TeacherInClassEntity teacherInClass);
    Integer updateTeaInClass(TeacherInClassEntity teacherInClass);

    ClassManageEntity queryClassByUserId(SysUserInorg sysUserInorg);
}
