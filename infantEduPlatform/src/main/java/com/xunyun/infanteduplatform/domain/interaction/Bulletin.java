package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;

public class Bulletin {
  // 公告Id(唯一编号)
  private Integer bulletinId;
  // 机构代码
  private Integer organizationId;
  // 标题
  private String title;
  // 副标题
  private String subtitle;
  // 类型(1：通知公告，2：任务，3：动态，4：健康关怀，5：育儿助手，6：消息，…)
  private Integer bulletinType;
  // 描述
  private String summary;
  // 公告内容
  private String bulletinContent;
  // 删除标记(0:未删除，1:删除)
  private Integer deleteFlg;
  // 来源
  private Integer sources;
  // 班级Id（默认：0）
  private Integer classId;
  // 级别(1：本班级，2：本园，3：公开（默认：1）)
  private Integer bulletinLevel;
  // 创建时间
  private Date creationTime;
  private String strCreationTime;
  // 创建人
  private String createdBy;
  // 最终修改时间
  private Date lastUpdateTime;
  private String strLastUpdateTime;
  // 最终修改人
  private String lastUpdatedBy;
  // 开始条数
  private Integer startNumber;
  // 结束条数
  private Integer endNumber;
  // 总条数
  private Integer dataCount;
  // 用户姓名
  private String name;
  // 用户头像
  private String photoUrl;
  // 查询条件
  private String keyValue;

  private Integer userId;
  // 专题
  private String subject;
  // 数据条数
  private Integer count;
  // 数据方向
  private Integer direction;

  public Integer getBulletinId() {
    return bulletinId;
  }

  public void setBulletinId(Integer bulletinId) {
    this.bulletinId = bulletinId;
  }

  public Integer getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Integer organizationId) {
    this.organizationId = organizationId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public Integer getBulletinType() {
    return bulletinType;
  }

  public void setBulletinType(Integer bulletinType) {
    this.bulletinType = bulletinType;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getBulletinContent() {
    return bulletinContent;
  }

  public void setBulletinContent(String bulletinContent) {
    this.bulletinContent = bulletinContent;
  }

  public Integer getDeleteFlg() {
    return deleteFlg;
  }

  public void setDeleteFlg(Integer deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  public Integer getSources() {
    return sources;
  }

  public void setSources(Integer sources) {
    this.sources = sources;
  }

  public Integer getClassId() {
    return classId;
  }

  public void setClassId(Integer classId) {
    this.classId = classId;
  }

  public Integer getBulletinLevel() {
    return bulletinLevel;
  }

  public void setBulletinLevel(Integer bulletinLevel) {
    this.bulletinLevel = bulletinLevel;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public String getStrCreationTime() {
    return strCreationTime;
  }

  public void setStrCreationTime(String strCreationTime) {
    this.strCreationTime = strCreationTime;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getStrLastUpdateTime() {
    return strLastUpdateTime;
  }

  public void setStrLastUpdateTime(String strLastUpdateTime) {
    this.strLastUpdateTime = strLastUpdateTime;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKeyValue() {
    return keyValue;
  }

  public void setKeyValue(String keyValue) {
    this.keyValue = keyValue;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getDirection() {
    return direction;
  }

  public void setDirection(Integer direction) {
    this.direction = direction;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }



}