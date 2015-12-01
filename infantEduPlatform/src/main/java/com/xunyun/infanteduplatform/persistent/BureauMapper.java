package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.BureauInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * 单位Mapper接口
 */

public interface BureauMapper {
    List<BureauInfo> queryBureauList(int id);
    List<BureauInfo> queryBureauInfo(int BureauId);
}
