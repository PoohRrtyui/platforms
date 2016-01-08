package com.xunyun.infanteduplatform.controller.interaction.growth;

import java.io.BufferedReader;
import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Growth;
import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.domain.interaction.ImageRelation;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.GrowthService;
import com.xunyun.infanteduplatform.service.ImageService;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;

@Controller
@RequestMapping("growth")
public class GrowthController {

  @Resource
  private AppInfoService appInfoService;

  @Resource
  private GrowthService growthService;

  @Resource
  private BulletinService bulletinService;

  @Resource
  private ImageService imageService;

  // 班级学生信息获取
  @RequestMapping("/studentInfoList")
  @ResponseBody
  public Map<String, Object> studentInfoList(HttpServletRequest req) throws Exception {

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
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (classId == null) {
      map.put("state", "error");
      map.put("message", "班级Id不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，查询失败!");
      map.put("data", "");
    } else {
      // 声明对象
      Bulletin item = new Bulletin();
      // 机构id
      item.setOrganizationId(organizationId);
      // 班级id
      item.setClassId(classId);

      List<SysUserInfo> listData = growthService.queryGrowthPage(item);
      List<Map> studentList = new ArrayList<Map>();
      if (listData != null && listData.size() > 0) {
        for (SysUserInfo sysUserInfo : listData) {
          Map<String, Object> studentInfo = new HashMap<String, Object>();
          // 学生Id
          studentInfo.put("userId", sysUserInfo.getUserid());
          // 姓名
          studentInfo.put("name", sysUserInfo.getNAME());
          // 家长id
          studentInfo.put("parentUserId", sysUserInfo.getParentUserId());
          // 个人头像
          studentInfo.put("imageContent", InteractionUtils.getPhoto(sysUserInfo.getPhotourl()));
          studentList.add(studentInfo);
        }
        map.put("state", "success");
        map.put("message", "");
        // 数据列表
        map.put("data", studentList);
      } else {
        map.put("state", "error");
        map.put("message", "数据为空");
        map.put("data", "");
      }
    }
    return map;
  }

  // 成长档案信息获取
  @RequestMapping("/growthInfo")
  @ResponseBody
  public Map<String, Object> growthInfo(HttpServletRequest req) throws Exception {

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
    // 用户ID
    Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
    // 发布时间(默认为null)
    String creationTime = null;
    if (jsonobject.get("creationTime") != null && !jsonobject.get("creationTime").equals("")) {
      creationTime = (String) jsonobject.get("creationTime");
    }
    // 显示数(默认为10)
    Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"), 10);
    // 数据方向(默认为0)
    Integer direction = ValueChangeUtils.changeValue(jsonobject.get("direction"), 0);

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户ID不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，获取失败");
      map.put("data", "");
    } else {

      // 获得所有操作数据，按照时间列表展示（倒序）
      Integer parentUserId = growthService.queryParentUserId(userId);

      if (parentUserId != null) {
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("userId", userId);
        para.put("parentUserId", parentUserId);
        para.put("creationTime", creationTime);
        para.put("count", count);
        para.put("direction", direction);
        List<Growth> growth = growthService.queryGrowthInfo(para);

        if (growth.size() > 0) {
          map.put("state", "success");
          map.put("message", "");
          // 数据列表
          map.put("data", growth);
        } else {
          map.put("state", "error");
          map.put("message", "数据为空");
          map.put("data", "");
        }
      } else {
        map.put("state", "error");
        map.put("message", "暂无此用户信息");
        map.put("data", "");
      }
    }
    return map;
  }

  // 成长记录发布
  @RequestMapping("/growthPublish")
  @ResponseBody
  public Map<String, Object> growthPublish(HttpServletRequest req) throws Exception {

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
    // 用户ID
    Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
    // 成长记录信息
    String bulletinContent = (String) jsonobject.get("bulletinContent");
    // 图片
    List<Blob> picturecontents = (List<Blob>) jsonobject.get("picturecontents");

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，发布信息失败");
      map.put("data", "");
    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码不能为空，发布信息失败");
      map.put("data", "");
    } else if (classId == null) {
      map.put("state", "error");
      map.put("message", "班级Id不能为空，发布信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户ID不能为空，发布信息失败");
      map.put("data", "");
    } else if (bulletinContent == null || bulletinContent.equals("")) {
      map.put("state", "error");
      map.put("message", "成长记录信息不能为空，发布信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，发布失败");
      map.put("data", "");
    } else {
      Integer count = null;
      // 公告信息保存
      Bulletin bulletin = new Bulletin();
      bulletin.setOrganizationId(organizationId);
      bulletin.setClassId(classId);
      bulletin.setTitle("");
      bulletin.setBulletinType(8);
      bulletin.setBulletinContent(bulletinContent);
      bulletin.setDeleteFlg(0);
      bulletin.setSources(0);
      bulletin.setBulletinLevel(1);
      bulletin.setCreationTime(Calendar.getInstance().getTime());
      bulletin.setCreatedBy(userId.toString());
      bulletin.setLastUpdateTime(Calendar.getInstance().getTime());
      bulletin.setLastUpdatedBy(userId.toString());
      count = bulletinService.addBulletinInfo(bulletin);
      // TODO：图片信息
      Image image = new Image();
      if (picturecontents != null) {
        for (Blob picturecontent : picturecontents) {
          File file = InteractionUtils.blob2File(picturecontent);
          String filename = file.getName();
          // 名称（没有扩展名）
          String picturename = filename.substring(0, filename.lastIndexOf('.'));
          // 扩展名
          String extension = filename.substring(filename.lastIndexOf('.') + 1);
          // 大小
          long size = file.length();
          image.setImageContent(picturecontent);
          image.setImageName(picturename);
          image.setImageSize(size);
          image.setExtension(extension);
          image.setCreationTime(Calendar.getInstance().getTime());
          image.setCreatedBy(userId.toString());
          image.setOrganizationId(organizationId);
          image.setLastUpdateTime(Calendar.getInstance().getTime());
          image.setLastUpdatedBy(userId.toString());
          // 图片信息保存
          count = imageService.addImageList(image);
          ImageRelation imageRelation = new ImageRelation();
          imageRelation.setImageId(image.getImageId());
          imageRelation.setOrganizationId(organizationId);
          imageRelation.setModuleId(bulletin.getBulletinId());
          // 图片关联表信息保存
          count = imageService.addImageRelation(imageRelation);
        }
      }

      if (count > 0) {
        map.put("state", "success");
        map.put("message", "成长记录信息发布成功");
        map.put("data", "");
      } else {
        map.put("state", "error");
        map.put("message", "成长记录信息发布失败");
        map.put("data", "");
      }
    }
    return map;
  }
}
