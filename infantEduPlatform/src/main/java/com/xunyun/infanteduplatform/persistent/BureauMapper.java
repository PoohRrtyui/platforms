package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.BureauInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/1.
 * 单位Mapper接口
 */

public interface BureauMapper {
    List<BureauInfo> queryBureauList(int id);
    BureauInfo queryBureauInfo(int BureauId);
    Integer saveBureauInfo(BureauInfo bureauInfo);
    Integer updateBureauInfo(BureauInfo bureauInfo);
    Integer deleteBureau(Integer BureauId);
}
