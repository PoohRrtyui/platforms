package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.FamilyMembers;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import org.springframework.stereotype.Repository;

/**
 * 家长学生关系Mapper
 * Created by Pooh on 2015/12/22.
 */
@Repository
public interface FamilyMembersMapper {
    Integer saveFamilyMembers(FamilyMembers familyMembers);
    Integer delFamilyMembers(Integer userId);
    Integer updateFamilyMembers(FamilyMembers familyMembers);

    FamilyMembers queryFamilyInfoByParentId(SysUserInorg sysUserInorg);
}
