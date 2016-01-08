package com.xunyun.infanteduplatform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.AppInfo;
import com.xunyun.infanteduplatform.persistent.AppInfoMapper;

@Service("appInfo")
public class AppInfoService {

  @Resource
  private AppInfoMapper appInfoMapper;
  
  public AppInfoMapper getAppInfoMapper() {
    return appInfoMapper;
  }

  public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
    this.appInfoMapper = appInfoMapper;
  }

  public AppInfo findById(int id) {
    
    return appInfoMapper.findById(id);
  }

  public int addAppInfo(AppInfo appInfo) {
   
    return appInfoMapper.addAppInfo(appInfo);
  }

  public List<AppInfo> findAll() {
    
    return appInfoMapper.findAll();
  }

  public List<AppInfo> queryAppInfoPage(AppInfo item) {
    
    return appInfoMapper.queryAppInfoPage(item);
  }

  public int deleteAppInfo(int id) {
   
    return appInfoMapper.deleteAppInfo(id);
  }

  public int updateAppInfo(AppInfo appInfo) {
    
    return appInfoMapper.updateAppInfo(appInfo);
  }

  public int updateList(Map<String, Object> map) {
    
    return appInfoMapper.updateList(map);
  }

  public int closeList(Map<String, Object> map) {
    
    return appInfoMapper.closeList(map);
  }

  public int deleteList(Map<String, Object> map) {
    
    return appInfoMapper.deleteList(map);
  }

  public int updateValidate(AppInfo appInfo) {
    
    return appInfoMapper.updateValidate(appInfo);
  }

  public boolean findByAppCode(String appCode) {
    if(appInfoMapper.findByAppCode(appCode)>0){
      return true;
    }else{
      return false;
    }  
  }
}
