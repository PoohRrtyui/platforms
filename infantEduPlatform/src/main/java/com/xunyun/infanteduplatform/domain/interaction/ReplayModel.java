package com.xunyun.infanteduplatform.domain.interaction;

public class ReplayModel {
  
    //回复内容
    private String ReplayContent;
    //回复时间String
    private String ReplayTime;
    //回复者姓名
    private String name;
    
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getReplayContent() {
      return ReplayContent;
    }
    public void setReplayContent(String replayContent) {
      ReplayContent = replayContent;
    }
    public String getReplayTime() {
      return ReplayTime;
    }
    public void setReplayTime(String ReplayTime) {
      this.ReplayTime = ReplayTime;
    }
    
}
