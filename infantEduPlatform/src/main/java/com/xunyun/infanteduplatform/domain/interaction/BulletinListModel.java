package com.xunyun.infanteduplatform.domain.interaction;

public class BulletinListModel {

  // 公告Id
  private Integer bulletinId;
  // 标题
  private String title;
  // 公告内容
  private String bulletinContent;
  // 创建时间
  private String creationTime;
  // 创建人
  private String createdBy;

  public Integer getBulletinId() {
    return bulletinId;
  }

  public void setBulletinId(Integer bulletinId) {
    this.bulletinId = bulletinId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBulletinContent() {
    return bulletinContent;
  }

  public void setBulletinContent(String bulletinContent) {
    this.bulletinContent = bulletinContent;
  }

  public String getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(String creationTime) {
    this.creationTime = creationTime;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }



}
