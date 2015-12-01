package com.xunyun.infanteduplatform.domain;


public class SysDepartmentRelation {
  private Integer DepartmentId;//部门机构编码
  private Integer BureauId;//学校代码
  private Integer ParentDepartmentId;//上级部门编码
  private Integer IsChild;//是否有子部门
  
  public Integer getDepartmentId() {
    return DepartmentId;
  }
  public void setDepartmentId(Integer departmentId) {
    DepartmentId = departmentId;
  }
  public Integer getBureauId() {
    return BureauId;
  }
  public void setBureauId(Integer bureauId) {
    BureauId = bureauId;
  }
  public Integer getParentDepartmentId() {
    return ParentDepartmentId;
  }
  public void setParentDepartmentId(Integer parentDepartmentId) {
    ParentDepartmentId = parentDepartmentId;
  }
  public Integer getIsChild() {
    return IsChild;
  }
  public void setIsChild(Integer isChild) {
    IsChild = isChild;
  }
  
}
