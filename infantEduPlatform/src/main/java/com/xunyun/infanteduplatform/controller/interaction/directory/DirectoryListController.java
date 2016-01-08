package com.xunyun.infanteduplatform.controller.interaction.directory;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.domain.interaction.Directory;
import com.xunyun.infanteduplatform.domain.interaction.DirectoryModel;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.DirectoryService;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;

@Controller
@RequestMapping("directory")
public class DirectoryListController {
  @Autowired
  private DirectoryService directoryService;

  @Autowired
  private AppInfoService appInfoService;

  /**
   * @author lpp 方法描述：联系人列表获取
   * 
   */
  @RequestMapping(value = "/queryDirectoryList")
  @ResponseBody
  public Map<String, Object> queryBulletinList(HttpServletRequest req) throws Exception {
    // 字节流
    BufferedReader reader = req.getReader();

    StringBuilder sb = new StringBuilder();
    char[] buff = new char[1024];
    int len;
    while ((len = reader.read(buff)) != -1) {
      sb.append(buff, 0, len);
    }

    // 字符串转换匿名对象
    JSONObject jsonobject = JSONObject.fromObject(sb.toString());
    // 应用系统代码
    String appCode = (String) jsonobject.get("appCode");
    // 机构代码
    Integer organizationId = ValueChangeUtils.changeValue(jsonobject.get("organizationId"), null);
    // 班级Id
    Integer classId = ValueChangeUtils.changeValue(jsonobject.get("classId"), null);

    Map<String, Object> map = new HashMap<String, Object>();

    if (appCode == null || "".equals(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为空，获取信息失败");
      map.put("data", "");

    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码为空，查询失败！");
      map.put("data", "");

    } else if (classId == null) {
      map.put("state", "error");
      map.put("message", "班级Id为空，查询失败！");
      map.put("data", "");

    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息！");
      map.put("data", "");

    } else {
      // 声明对象
      Directory directory = new Directory();
      // 机构代码
      directory.setOrganizationId(organizationId);
      // 班级Id
      directory.setClassId(classId);
      // 获取数据
      List<Directory> listData = directoryService.queryDirectoryList(directory);

      List<DirectoryModel> list = new ArrayList<DirectoryModel>();
      // 查询数据
      for (Directory dir : listData) {
        // 获取用户头像进行转换
        String photourl = dir.getPhotoUrl();
        byte[] userPhotoUrl = InteractionUtils.getPhoto(photourl);

        // 声明对象
        DirectoryModel directoryModel = new DirectoryModel();
        // 用户Id
        directoryModel.setUserId(dir.getUserId());
        // TODO
        directoryModel.setImageContent(userPhotoUrl);
        // 姓名
        directoryModel.setName(dir.getName());
        // 手机号
        directoryModel.setMobile(dir.getMobile());

        list.add(directoryModel);
      }

      // 状态
      map.put("state", "success");
      // 提示信息
      map.put("message", "");
      // 数据列表
      map.put("data", list);
    }

    return map;
  }

}
