package com.xunyun.infanteduplatform.domain.interaction;

import java.sql.Blob;

public class ImageRelation {
  //机构代码
  private Integer organizationId;
  //模块Id
  private Integer moduleId;
  //图片Id(唯一编号)
  private Integer imageId;
  //图片内容
  private Blob imageContent;
  
  public Integer getOrganizationId() {
    return organizationId;
  }
  public void setOrganizationId(Integer organizationId) {
    this.organizationId = organizationId;
  }
  public Integer getModuleId() {
    return moduleId;
  }
  public void setModuleId(Integer moduleId) {
    this.moduleId = moduleId;
  }
  public Integer getImageId() {
    return imageId;
  }
  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }
  public Blob getImageContent() {
    return imageContent;
  }
  public void setImageContent(Blob imageContent) {
    this.imageContent = imageContent;
  }
  
}
