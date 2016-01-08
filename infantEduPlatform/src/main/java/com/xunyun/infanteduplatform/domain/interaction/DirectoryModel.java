package com.xunyun.infanteduplatform.domain.interaction;

public class DirectoryModel {
  // 用户Id
  private Integer userId;

  // 姓名
  private String name;

  // 个人头像图片转为byte[]
  private byte[] ImageContent;

  // 手机号
  private String mobile;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getImageContent() {
    return ImageContent;
  }

  public void setImageContent(byte[] imageContent) {
    ImageContent = imageContent;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }



}
