package com.xunyun.infanteduplatform.persistent;

import java.util.List;
import java.util.Map;

import com.xunyun.infanteduplatform.domain.AppInfo;

public interface AppInfoMapper {

  AppInfo findById(int id);

  int addAppInfo(AppInfo appInfo);

  List<AppInfo> findAll();

  List<AppInfo> queryAppInfoPage(AppInfo item);

  int deleteAppInfo(int id);

  int updateAppInfo(AppInfo appInfo);

  int updateList(Map<String, Object> map);

  int closeList(Map<String, Object> map);

  int deleteList(Map<String, Object> map);

  int updateValidate(AppInfo appInfo);

  Integer findByAppCode(String appCode);
  
}
