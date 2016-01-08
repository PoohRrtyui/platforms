package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.SchoolInfo;
import com.xunyun.infanteduplatform.domain.SchoolRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * 学校Mapper
 */
@Repository
public interface SchoolMapper {
    List<SchoolInfo> querySchoolByBureauId(int BureauId);
    Integer saveSchool(SchoolInfo schoolInfo);
    Integer saveSchoolRelation(SchoolRelation schoolRelation);
    Integer updateSchool(SchoolInfo schoolInfo);
    Integer updateSchoolRelation(SchoolRelation schoolRelation);
    SchoolInfo querySchool(SchoolInfo schoolInfo);

    void deleteSchool(Integer schoolId);
    void deleteSchoolRelation(Integer schoolId);
    
    List<SchoolRelation> queryChildSchool(int BureauId);

    Integer querySchoolByNameAndBureauId(SchoolInfo schoolInfo);
}
