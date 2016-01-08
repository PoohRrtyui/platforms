package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.SysDepartmentInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;

import java.util.List;

public interface DepartmentInfoMapper {
//查询部门详情信息
  public SysDepartmentInfo findById(int id);
//增加部门信息
  public int addDepartment(SysDepartmentInfo departmentInfo);
//删除部门信息
  public int deleteDepartmentInfo(Integer id);
//增加、修改查重
  public int updateValidate(TreeEntity entity);
//修改部门信息
  public int updateDepartmentInfo(SysDepartmentInfo departmentInfo);
//刷新树节点 
  public int findCound(Integer pId);
//修改节点状态
  public int update(Integer pId);
//查询部门是否被用户使用中
  public int selectCount(Integer id);
//增加时修改节点状态  
  public int updatePid(int parentId);

  List<SysDepartmentInfo> queryDeptBySchoolId(SysDepartmentInfo departmentInfo);
  
  Integer updateDeptIsChild(Integer parentDeptId);

}
