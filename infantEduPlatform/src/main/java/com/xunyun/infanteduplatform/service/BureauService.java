package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.persistent.BureauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 * 单位Service
 */
@Service("BureauService")
public class BureauService {

    @Autowired
    private BureauMapper bureauMapper;

    /**
     * 查询单位信息列表并转换成树
     * @param BureauId 单位Id
     * @return List<TreeEntity>
     */
    public List<TreeEntity> queryBureauList (int id){
        List<BureauInfo> bureauInfoList = this.bureauMapper.queryBureauList(id);
        TreeEntity treeEntity ;
        List<TreeEntity> treeEntityList = new ArrayList<>(bureauInfoList.size());
        for (BureauInfo bureauInfo : bureauInfoList) {
            treeEntity = new TreeEntity();
            treeEntity.setId(bureauInfo.getOrganizationId().intValue());
            treeEntity.setpId(bureauInfo.getParentBureauId().intValue());
            treeEntity.setName(bureauInfo.getBureauName());
            treeEntity.setIsParent(bureauInfo.getIsChild()==1);
            treeEntityList.add(treeEntity);
        }
        return treeEntityList;
    }

    public List<BureauInfo> queryBureauInfo (int bureauId){
        return this.bureauMapper.queryBureauInfo(bureauId);
    }
}
