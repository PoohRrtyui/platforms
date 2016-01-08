package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.FamilyMembers;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.persistent.FamilyMembersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生家长关系Service
 * Created by Pooh on 2015/12/22.
 */
@Service("FamilyMem")
public class FamilyMembersService {

    @Autowired FamilyMembersMapper familyMembersMapper;
    public Integer saveFamilyMembers(FamilyMembers familyMembers){
        return this.familyMembersMapper.saveFamilyMembers(familyMembers);
    }

    public Integer delFamilyMembers(Integer userId){
        return familyMembersMapper.delFamilyMembers(userId);
    }

    public Integer updateFamilyMembers(FamilyMembers familyMembers){
        return familyMembersMapper.updateFamilyMembers(familyMembers);
    }

    public FamilyMembers queryFamilyInfoByParentId(SysUserInorg sysUserInorg) {
        return familyMembersMapper.queryFamilyInfoByParentId(sysUserInorg);
    }
}
