package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;

public class MorningCheck {
     
  private long checkId;
  
  private long userId;
  
  private Integer state;
  
  private String checkSummary;
  
  private Date checkTime;
  
  private String strCheckTime;
  
  private String strState;
  
  private String keyValue;
  
  public String getKeyValue() {
    return keyValue;
  }

  public void setKeyValue(String keyValue) {
    this.keyValue = keyValue;
  }

  public String getStrState() {
    return strState;
  }

  public void setStrState(String strState) {
    this.strState = strState;
  }

  public String getStrCheckTime() {
    return strCheckTime;
  }

  public void setStrCheckTime(String strCheckTime) {
    this.strCheckTime = strCheckTime;
  }

  private long teacherId;
  private String checkteacherName;
  private String studentName;
  
  
  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getCheckteacherName() {
    return checkteacherName;
  }

  public void setCheckteacherName(String checkteacherName) {
    this.checkteacherName = checkteacherName;
  }

  //开始条数
  private Integer startNumber;
  public Integer getStartNumber() {
    return startNumber;
  }

  public void setStartNumber(Integer startNumber) {
    this.startNumber = startNumber;
  }

  public Integer getEndNumber() {
    return endNumber;
  }

  public void setEndNumber(Integer endNumber) {
    this.endNumber = endNumber;
  }

  public Integer getDataCount() {
    return dataCount;
  }

  public void setDataCount(Integer dataCount) {
    this.dataCount = dataCount;
  }

  // 结束条数
  private Integer endNumber;
  // 总条数
  private Integer dataCount;
  
  private long classId;
  
  private long organizationId;

  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }

  public long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(long organizationId) {
    this.organizationId = organizationId;
  }

  public long getCheckId() {
    return checkId;
  }

  public void setCheckId(long checkId) {
    this.checkId = checkId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getCheckSummary() {
    return checkSummary;
  }

  public void setCheckSummary(String checkSummary) {
    this.checkSummary = checkSummary;
  }

  public Date getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(Date checkTime) {
    this.checkTime = checkTime;
  }

  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }
}
