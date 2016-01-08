package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.TeacherInClassEntity;
import com.xunyun.infanteduplatform.domain.UserInClassEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassManageMapper {

  // 初始查询班级
  public List<ClassManageEntity> queryclass(ClassManageEntity classmanageentity);

  // 修改班级时获取该班级的信息
  public ClassManageEntity queryupdateclassinfo(ClassManageEntity classmanageentity);


  // 修改时获取班级主班老师信息
  public ClassManageEntity queryupdateclassmainteacher(ClassManageEntity classmanageentity);

  // 修改时获取班级配班信息
  public ClassManageEntity queryupdateclasswithteacher(ClassManageEntity classmanageentity);

  // 修改时获取班级保育员信息
  public ClassManageEntity queryupdatenurserygoverness(ClassManageEntity classmanageentity);

  // 修改班级时将教师任教表中被替换的教师信息删除
  public int deletemainteacherinclass(TeacherInClassEntity teacherinclassentity);


  // 修改班级时，在classinfo中修改班级名称及班级类型
  public int updateclassinfo(ClassManageEntity classmanageentity);
  
  //修改班级时修改最终修改人与最终修改时间
  public int updateclassupdateinfo(ClassManageEntity classmanageentity);

  // 添加班级时查询教师信息
  public List<?> queryclassteacher(SysUserInorg sysuserinorg);
  
  //添加班级时班级名称查重
  public ClassManageEntity queryrepeatclassname(ClassManageEntity classmanageentity);
  
 //添加班级时班级代码查重
  public ClassManageEntity queryrepeatclasscode(ClassManageEntity classmanageentity);

 //修改时班级名称查重
  public ClassManageEntity queryupdaterepeatclassname(ClassManageEntity classmanageentity);
  
  // 添加班级
  public int insertclass(ClassManageEntity classmanageentity);

  // 添加classinforelation数据
  public int insertclassinforelation(ClassManageEntity classmanageentity);

  // 教师任教表中添加主班老师信息
  public int insertmainteacherinclass(SysUserInorg sysuserinorg);

  // 教师任教表中添加副班老师信息
  public int insertwithteacherinclass(SysUserInorg sysuserinorg);

  // 教师任教表中添加保育员老师信息
  public int insertnurserygovernessid(SysUserInorg sysuserinorg);


  // 用户所属机构表中修改主班用户类型
  public int updatemainteacherinorg(SysUserInorg sysuserinorg);

  // 用户所属机构表中修改副班用户类型
  public int updatewithteacherinorg(SysUserInorg sysuserinorg);

  // 用户所属机构表中修改保育员用户类型
  public int updatenurserygovernessinorg(SysUserInorg sysuserinorg);

  // 逻辑删除班级classinfo信息
  public int updateclassdeleteflg(ClassManageEntity classmanageentity);

  // 删除classinforelation信息
  public int deleteclassinforelation(ClassManageEntity classmanageentity);

  // 删除班级时查询该学校该班级下是否有学生存在
  public List<?> queryclassstudents(UserInClassEntity userinclassentity);

  // 删除班级时查询该学校该班级下是否有教师，教职工存在
  public List<?> queryclassteachers(TeacherInClassEntity teacherinclassentity);
  
  //删除teacherinclass中该班级下的主班，配班，保育员老师
  public int deletespecialteacherinclass(TeacherInClassEntity teacherinclassentity);
  
 //进入首页时查询的登陆用户信息，判断其权限进行左侧菜单显示
  public SysUserInorg querylogininfo(SysUserInorg sysuserinorg);


   List<ClassManageEntity> queryClassBySchoolId(ClassManageEntity classManageEntity);
}
