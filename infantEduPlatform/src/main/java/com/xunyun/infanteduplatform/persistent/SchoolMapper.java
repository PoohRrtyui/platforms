package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.SchoolInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * 学校Mapper
 */
public interface SchoolMapper {
    List<SchoolInfo> querySchoolByBureauId(int BureauId);
}
