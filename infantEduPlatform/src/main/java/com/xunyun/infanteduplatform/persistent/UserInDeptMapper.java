package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.SysDepartmentInfo;
import com.xunyun.infanteduplatform.domain.SysUserInDepartment;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import org.springframework.stereotype.Repository;

/**
 * 用户所属部门Mapper
 * Created by Pooh on 2015/12/23.
 */
@Repository
public interface UserInDeptMapper {
    Integer updateUserInDept(SysUserInDepartment userInDepartment);

    SysDepartmentInfo queryUserInDeptByUserId(SysUserInorg userInOrg);
}
