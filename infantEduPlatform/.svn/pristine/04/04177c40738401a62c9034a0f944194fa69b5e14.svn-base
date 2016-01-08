package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.domain.UserInClassEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生所属班级Mapper
 * Created by Pooh on 2015/12/23.
 */
@Repository
public interface UserInClassMapper {
    Integer updateUserInClass(UserInClassEntity userInClass);
    List<SysUserLogin> queryUsersInClass(Map<String,Integer> map);

    ClassManageEntity queryClassByUserId(SysUserInorg sysUserInorg);
}
