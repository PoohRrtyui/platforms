package com.xunyun.infanteduplatform.service;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.BureauRelation;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.persistent.BureauMapper;
import com.xunyun.infanteduplatform.persistent.BureauRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/1.
 * 单位Service
 */
@Service("BureauService") public class BureauService {

    @Autowired private BureauMapper bureauMapper;
    @Autowired private BureauRelationMapper bureauRelationMapper;

    /**
     * 查询单位信息列表并转换成树
     *
     * @param id 单位Id
     * @return List<TreeEntity>
     */
    public List<TreeEntity> queryBureauList(int id) {
        List<BureauInfo> bureauInfoList = this.bureauMapper.queryBureauList(id);
        TreeEntity treeEntity;
        List<TreeEntity> treeEntityList = new ArrayList<>(bureauInfoList.size());
        for (BureauInfo bureauInfo : bureauInfoList) {
            treeEntity = new TreeEntity();
            treeEntity.setId(bureauInfo.getOrganizationId());
            treeEntity.setpId(bureauInfo.getParentBureauId());
            treeEntity.setName(bureauInfo.getBureauName());
            treeEntity.setIsParent(bureauInfo.getIsChild() == 1);
            treeEntityList.add(treeEntity);
        }
        return treeEntityList;
    }

    /**
     * 查询单个单位信息
     * @param bureauId 单位Id
     * @return 单位实体类
     */
    public BureauInfo queryBureauInfo(int bureauId) {
        return this.bureauMapper.queryBureauInfo(bureauId);
    }

    /**
     * 保存单位信息(包括单位关联关系)
     * @param bureauInfo 单位实体类
     * @param parentBureauId 上级单位Id
     * @return 保存状态
     */
    public Integer saveBureauInfo(BureauInfo bureauInfo, Integer parentBureauId) {
        //保存单位信息
        this.bureauMapper.saveBureauInfo(bureauInfo);
        //保存单位关联信息
        BureauRelation bureauRelation = new BureauRelation();
        bureauRelation.setBureauId(bureauInfo.getOrganizationId());
        bureauRelation.setParentBureauId(parentBureauId);
        bureauRelation.setIsChild(0);
        this.bureauMapper.saveBureauRelation(bureauRelation);
        //修改上级单位的 isChild 状态为1
        bureauRelation.setIsChild(1);
        return this.bureauMapper.updateBureauRelation(bureauRelation);
    }

    /**
     * 修改单位信息(包括单位关联信息)
     * @param bureauInfo 单位实体类
     * @param parentBureauId 上级单位Id
     * @return 修改状态
     */
    public Integer updateBureauInfo(BureauInfo bureauInfo, Integer parentBureauId) {
        //修改单位信息
        this.bureauMapper.updateBureauInfo(bureauInfo);
        //修改当前单位关联关系
        BureauRelation bureauRelation = new BureauRelation();
        bureauRelation.setBureauId(bureauInfo.getOrganizationId());
        bureauRelation.setParentBureauId(parentBureauId);
        this.bureauMapper.updateBureauRelation(bureauRelation);
        //查询上级单位是否还有子节点
        List<BureauRelation> bureauRelationList = this.bureauMapper.queryBureauChildren(bureauRelation);
        if(bureauRelationList.size()==0){
            bureauRelation.setBureauId(parentBureauId);
            bureauRelation.setIsChild(0);
            this.bureauMapper.updateBureauRelation(bureauRelation);
        }
        return 1;
    }

    /**
     * 删除单位信息(包括单位关联信息)
     * @param bureauId 单位Id
     * @return 删除状态
     */
    public Integer deleteBureauInfo(Integer bureauId) {
        return this.bureauMapper.deleteBureau(bureauId);
    }

}
