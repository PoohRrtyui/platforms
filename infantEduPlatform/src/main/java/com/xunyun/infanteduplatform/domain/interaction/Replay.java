package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;

public class Replay {
    //回复Id
    private Integer ReplayId;
    //模块Id
    private Integer ModuleId;
    //回复数据的主键Id
    private Integer TargetId;
    //用户Id
    private Integer UserId;
    //回复内容
    private String ReplayContent;
    //回复时间
    private Date ReplayTime;
    //回复者姓名
    private String name;
    
    public Integer getReplayId() {
      return ReplayId;
    }
    public void setReplayId(Integer replayId) {
      ReplayId = replayId;
    }
    public Integer getModuleId() {
      return ModuleId;
    }
    public void setModuleId(Integer moduleId) {
      ModuleId = moduleId;
    }
    public Integer getTargetId() {
      return TargetId;
    }
    public void setTargetId(Integer targetId) {
      TargetId = targetId;
    }
    public Integer getUserId() {
      return UserId;
    }
    public void setUserId(Integer userId) {
      UserId = userId;
    }
    public String getReplayContent() {
      return ReplayContent;
    }
    public void setReplayContent(String replayContent) {
      ReplayContent = replayContent;
    }
    public Date getReplayTime() {
      return ReplayTime;
    }
    public void setReplayTime(Date replayTime) {
      ReplayTime = replayTime;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    
}
