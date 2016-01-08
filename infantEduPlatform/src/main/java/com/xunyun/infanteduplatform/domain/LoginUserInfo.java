package com.xunyun.infanteduplatform.domain;

public class LoginUserInfo {
  //用户ID
  private Integer userid;
  //登录名
  private String username;
  //昵称
  private String nickname;
  //真实姓名
  private String name;
  //性别
  private long gendercode;
  //证件类型
  private long idtype;
  //身份证号
  private String idno;
  //出生日期
  private String birthday;
  //手机
  private String mobile;
  //邮箱
  private String email;
  //个人头像图片文件夹名称
  private String photourl;
  //个人头像图片转为byte[]
  private byte[] imageContent;
  //组织id
  private Integer organizationid;
  //组织类型
  private String organizationtype;
  //单位名称
  private String bureauName;
  //用户类型
  private Integer usertype;
  //是否管理员
  private Integer isadmin;
  //部门id
  private Integer departmentid;
  //部门名称
  private String departmentName;
  //教师班级关系id
  private Integer teacherClassId;
  //教师类型
  private Integer teacherType;
  //学生班级关系id
  private Integer studentClassId;
  //班级名称
  private String className;
  //学校名称
  private String schoolName;
  
  public String getSchoolName() {
    return schoolName;
  }
  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }
  public String getBureauName() {
    return bureauName;
  }
  public void setBureauName(String bureauName) {
    this.bureauName = bureauName;
  }

  public String getDepartmentName() {
    return departmentName;
  }
  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }
  public String getClassName() {
    return className;
  }
  public void setClassName(String className) {
    this.className = className;
  }
  public Integer getUserid() {
    return userid;
  }
  public void setUserid(Integer userid) {
    this.userid = userid;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public long getGendercode() {
    return gendercode;
  }
  public void setGendercode(long gendercode) {
    this.gendercode = gendercode;
  }
  public long getIdtype() {
    return idtype;
  }
  public void setIdtype(long idtype) {
    this.idtype = idtype;
  }
  public String getIdno() {
    return idno;
  }
  public void setIdno(String idno) {
    this.idno = idno;
  }
  public String getBirthday() {
    return birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhotourl() {
    return photourl;
  }
  public void setPhotourl(String photourl) {
    this.photourl = photourl;
  }
  public Integer getOrganizationid() {
    return organizationid;
  }
  public void setOrganizationid(Integer organizationid) {
    this.organizationid = organizationid;
  }
  public String getOrganizationtype() {
    return organizationtype;
  }
  public void setOrganizationtype(String organizationtype) {
    this.organizationtype = organizationtype;
  }
  public Integer getUsertype() {
    return usertype;
  }
  public void setUsertype(Integer usertype) {
    this.usertype = usertype;
  }
  public Integer getIsadmin() {
    return isadmin;
  }
  public void setIsadmin(Integer isadmin) {
    this.isadmin = isadmin;
  }
  public Integer getDepartmentid() {
    return departmentid;
  }
  public void setDepartmentid(Integer departmentid) {
    this.departmentid = departmentid;
  }
  public Integer getTeacherClassId() {
    return teacherClassId;
  }
  public void setTeacherClassId(Integer teacherClassId) {
    this.teacherClassId = teacherClassId;
  }
  public Integer getTeacherType() {
    return teacherType;
  }
  public void setTeacherType(Integer teacherType) {
    this.teacherType = teacherType;
  }
  public Integer getStudentClassId() {
    return studentClassId;
  }
  public void setStudentClassId(Integer studentClassId) {
    this.studentClassId = studentClassId;
  }

  public byte[] getImageContent() {
    return imageContent;
  }

  public void setImageContent(byte[] imageContent) {
    this.imageContent = imageContent;
  }
}
