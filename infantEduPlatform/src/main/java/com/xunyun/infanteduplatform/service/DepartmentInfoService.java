package com.xunyun.infanteduplatform.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.SysDepartmentInfo;
import com.xunyun.infanteduplatform.domain.SysDepartmentRelation;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.persistent.DepartmentInfoMapper;
import com.xunyun.infanteduplatform.persistent.DepartmentRelationMapper;

import java.util.List;

@Service("departmentInfo")
@Transactional
public class DepartmentInfoService {

  @Resource
  private DepartmentInfoMapper departmentInfoMapper;

  @Resource
  private DepartmentRelationMapper departmentRelationMapper;

  public DepartmentInfoMapper getDepartmentInfoMapper() {
    return departmentInfoMapper;
  }

  public void setDepartmentInfoMapper(DepartmentInfoMapper departmentInfoMapper) {
    this.departmentInfoMapper = departmentInfoMapper;
  }

  public DepartmentRelationMapper getDepartmentRelationMapper() {
    return departmentRelationMapper;
  }

  public void setDepartmentRelationMapper(DepartmentRelationMapper departmentRelationMapper) {
    this.departmentRelationMapper = departmentRelationMapper;
  }

  // 查询部门详情信息
  public SysDepartmentInfo findById(int id) {

    return departmentInfoMapper.findById(id);
  }
  //增加部门信息
  public int addDepartmentRelation(SysDepartmentRelation departmentRelation,
      SysDepartmentInfo departmentInfo) {
    int count = 0;
    count = departmentInfoMapper.addDepartment(departmentInfo);
    departmentRelation.setDepartmentId(departmentInfo.getDepartmentId());
    count = departmentRelationMapper.addDepartmentRelation(departmentRelation);
    return count;
  }
  //删除部门信息
  public int deleteDepartmentInfo(Integer id) {

    return departmentInfoMapper.deleteDepartmentInfo(id);
  }
    //增加、修改查重
  public int updateValidate(TreeEntity entity) {

    return departmentInfoMapper.updateValidate(entity);
  }
  //修改部门信息
  public int updateDepartmentInfo(SysDepartmentInfo departmentInfo) {

    return departmentInfoMapper.updateDepartmentInfo(departmentInfo);
  }
  //删除时修改节点状态
  public int update(Integer pId) {
   
    return departmentInfoMapper.update(pId);
  }
  //刷新树节点
  public int findCound(Integer pId) {
    
    return departmentInfoMapper.findCound(pId);
  }
  //查询部门是否被用户使用中
  public int selectCount(Integer id) {
    
    return departmentInfoMapper.selectCount(id);
  }
//增加时修改节点状态
  public int updatePid(int parentId) {
    
    return departmentInfoMapper.updatePid(parentId);
  }

  public List<SysDepartmentInfo> queryDeptBySchoolId(SysDepartmentInfo departmentInfo){
    return departmentInfoMapper.queryDeptBySchoolId(departmentInfo);
  }
  
  public Integer updateDeptIsChild(Integer parentDeptId){
    return departmentInfoMapper.updateDeptIsChild(parentDeptId);
  }

}
