package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;
import java.util.List;

public class SelectDynamic {
  // 模块Id
  private Integer BulletinId;
  private String title;
  // 模块内容
  private String bulletincontent;
  // 创建时间
  private Date creationtime;
  // 创建时间
  private String strcreationtime;
  // 用户名
  private String name;
  // 用户头像
  private String photourl;
  // 数据条数
  private Integer dataCount;
  // 回复列表
  private List<ReplayModel> replayList;
  
  private Integer rownumber;
  
  public List<ReplayModel> getReplayList() {
    return replayList;
  }

  public void setReplayList(List<ReplayModel> replayList) {
    this.replayList = replayList;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getBulletinId() {
    return BulletinId;
  }

  public void setBulletinId(Integer bulletinId) {
    BulletinId = bulletinId;
  }

  public String getBulletincontent() {
    return bulletincontent;
  }

  public void setBulletincontent(String bulletincontent) {
    this.bulletincontent = bulletincontent;
  }

  public Date getCreationtime() {
    return creationtime;
  }

  public void setCreationtime(Date creationtime) {
    this.creationtime = creationtime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotourl() {
    return photourl;
  }

  public void setPhotourl(String photourl) {
    this.photourl = photourl;
  }

  public Integer getDataCount() {
    return dataCount;
  }

  public void setDataCount(Integer dataCount) {
    this.dataCount = dataCount;
  }

  public Integer getRownumber() {
    return rownumber;
  }

  public void setRownumber(Integer rownumber) {
    this.rownumber = rownumber;
  }

  public String getStrcreationtime() {
    return strcreationtime;
  }

  public void setStrcreationtime(String strcreationtime) {
    this.strcreationtime = strcreationtime;
  }

}
