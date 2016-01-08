package com.xunyun.infanteduplatform.controller.appinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xunyun.infanteduplatform.domain.AppInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.service.AppInfoService;

@Controller
@RequestMapping("appInfo")
public class AppInfoController {

  @Resource
  private AppInfoService appInfoService;

  public AppInfoService getAppInfoService() {
    return appInfoService;
  }

  public void setAppInfoService(AppInfoService appInfoService) {
    this.appInfoService = appInfoService;
  }
  
  @ResponseBody
  @RequestMapping("/findById")
  public AppInfo findById(int id){
    AppInfo sd=appInfoService.findById(id);
    return sd;
  }
  
  @ResponseBody
  @RequestMapping("/findAll")
  public List<AppInfo> findAll(){
    List<AppInfo> sd=appInfoService.findAll();
    return sd;
  }
  
  // 页面跳转
  @RequestMapping("/updateAppInfo")
  public ModelAndView updateAppInfo(@RequestParam(defaultValue="0")int id) {
    ModelAndView view = new ModelAndView("pages/appinfo/addAppInfo");
    view.addObject("id",id);
    return view ;
  }
  
  // 增加
  @ResponseBody
  @RequestMapping("/addAppInfo")
  public int addAppInfo(AppInfo appInfo){
    if(appInfo.getAppId()!=0){
      appInfo.setLastUpdateTime(Calendar.getInstance().getTime());
      appInfo.setLastUpdatedBy("jeff");
      int  count=appInfoService.updateAppInfo(appInfo);
      return count;
    }else{
      appInfo.setAppCode(UUID.randomUUID().toString());
/*    appInfo.setAppName("qqqqqqqq");
    appInfo.setPlatform(1);
    appInfo.setLocationCode("000000");*/
    appInfo.setState(0);
    appInfo.setCreationTime(Calendar.getInstance().getTime());
    appInfo.setCreatedBy("jeff");
    appInfo.setLastUpdateTime(Calendar.getInstance().getTime());
    appInfo.setLastUpdatedBy("jeff");
    int  count=appInfoService.addAppInfo(appInfo);
    return count;
    }
  }
  
  @RequestMapping("/show")
  public ModelAndView appInfoView(){
      return new ModelAndView("pages/appinfo/appInfo");
  }
  
  // 增加、修改查重
  @ResponseBody
  @RequestMapping("/updateValidate")
  public int updateValidate(AppInfo appInfo) {
      int a=appInfoService.updateValidate(appInfo);
    return a;
  }
  
  // 删除
  @RequestMapping("/deleteAppInfo")
  @ResponseBody
  public int deleteAppInfo(int id) {
    int count=appInfoService.deleteAppInfo(id);
    return count;
  }
  
  @RequestMapping(value = "/queryAppInfo")
  @ResponseBody
  public Map<Object, Object> queryAppInfo(
          @RequestParam(value = "search[value]", required = false) String keyValue,
          @RequestParam(value = "draw", required = false) Integer draw,
          @RequestParam(value = "start", required = false) Integer start,
          @RequestParam(value = "length", required = false) Integer length) {

      // 开始条数
      int startNumber = start + 1;
      // 结束条数
      int endNumber = start + length;
      // 总数目
      int countNumber = 0;

      // 声明对象
      AppInfo item = new AppInfo();
      // 查询条件
      item.setKeyValue(keyValue);
      // 开始条数
      item.setStartNumber(startNumber);
      // 结束条数
      item.setEndNumber(endNumber);
      
      // 获取数据
      List<AppInfo> listData = appInfoService.queryAppInfoPage(item);
      // 数据不为空，取总数
      if (listData != null && listData.size() > 0) {
          countNumber = listData.get(0).getCountNumber();
      }
      
      // 返回对象
      Map<Object, Object> info = new HashMap<Object, Object>();
      // 数据列表
      info.put("data", listData);
      // 总条数
      info.put("recordsTotal", countNumber);
      // 过滤条数
      info.put("recordsFiltered", countNumber);
      // 当前页数
      info.put("draw", draw);

      return info;
  }
  
  //批量修改
  @RequestMapping(value="/updateList", method = RequestMethod.POST)
  @ResponseBody
  public int updateList(@RequestParam(value="ids",required = false)String ids){
    String[] array = ids.split(",");
    List<String> list = new ArrayList<String>();
    Map<String,Object> map = new HashMap<String,Object>();
    for (int  i =0;i<array.length;i++){
      list.add(array[i]);
  }
      map.put("idList", list);
      int count = appInfoService.updateList(map);
      return count;
  }
  
  //批量修改
  @RequestMapping(value="/closeList", method = RequestMethod.POST)
  @ResponseBody
  public int closeList(@RequestParam(value="ids",required = false)String ids){
    String[] array = ids.split(",");
    List<String> list = new ArrayList<String>();
    Map<String,Object> map = new HashMap<String,Object>();
    for (int  i =0;i<array.length;i++){
      list.add(array[i]);
  }
      map.put("idList", list);
      int count = appInfoService.closeList(map);
      return count;
  }
  
  //批量删除
  @RequestMapping(value="/deleteList", method = RequestMethod.POST)
  @ResponseBody
  public int deleteList(@RequestParam(value="ids",required = false)String ids){
    String[] array = ids.split(",");
    List<String> list = new ArrayList<String>();
    Map<String,Object> map = new HashMap<String,Object>();
    for (int  i =0;i<array.length;i++){
      list.add(array[i]);
  }
      map.put("idList", list);
      int count = appInfoService.deleteList(map);
      return count;
  }

}
