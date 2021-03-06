package com.xunyun.infanteduplatform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysDepartmentInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.persistent.DepartmentRelationMapper;

@Service("departmentRelation")
public class DepartmentRelationService {
  @Autowired
  private DepartmentRelationMapper departmentRelationMapper;

  // 查询一级部门
  public List<TreeEntity> query(Map<String, Object> map) {
    List<SysDepartmentInfo> departmentInfos = departmentRelationMapper.findName(map);
    TreeEntity entity;
    List<TreeEntity> treeEntities = new ArrayList<TreeEntity>();
    for (SysDepartmentInfo departmentInfo : departmentInfos) {
      entity = new TreeEntity();
      entity.setId(departmentInfo.getDepartmentId());
      entity.setName(departmentInfo.getDepartmentName());
      entity.setpId(departmentInfo.getParentDepartmentId());
      entity.setIsParent(departmentInfo.getIsChild() == 1);
      treeEntities.add(entity);
    }
    return treeEntities;
  }

  public List<ClassManageEntity> getClassList(Integer schoolId) {
    return this.departmentRelationMapper.getClassList(schoolId);
  }

  public List<SysDepartmentInfo> getClassAndDepList(Map<String, Integer> dataMap) {
    return this.departmentRelationMapper.getClassAndDepList(dataMap);
  }
  
  /**
   * 
   * @Param
   * @return 物理删除部门关联表信息
   * */
  public int deleteDepartmentRelation(Integer id) {
    return this.departmentRelationMapper.deletedepartmentrelation(id);
  }
  
}
