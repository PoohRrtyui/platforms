package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.SchoolInfo;
import com.xunyun.infanteduplatform.persistent.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * 学校Service
 */
@Service("SchoolService")
public class SchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    public List<SchoolInfo> querySchoolByBureauId (int bureauId){
        return this.schoolMapper.querySchoolByBureauId(bureauId);
    }
}
