package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.BureauRelation;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/1.
 * 单位Mapper接口
 */
@Repository
public interface BureauMapper {
    /*单位相关操作*/
    List<BureauInfo> queryBureauList(int id);
    BureauInfo queryBureauInfo(int BureauId);
    Integer saveBureauInfo(BureauInfo bureauInfo);
    Integer updateBureauInfo(BureauInfo bureauInfo);
    Integer deleteBureau(Integer BureauId);

    /*单位关联信息操作*/
    Integer saveBureauRelation(BureauRelation bureauRelation);
    Integer updateBureauRelation(BureauRelation bureauRelation);
    List<BureauRelation> queryBureauChildren(BureauRelation bureauRelation);
}
