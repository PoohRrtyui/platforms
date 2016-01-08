package com.xunyun.infanteduplatform.controller.interaction.parenting;

import java.io.BufferedReader;
import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.domain.interaction.ImageRelation;
import com.xunyun.infanteduplatform.domain.interaction.Message;
import com.xunyun.infanteduplatform.domain.interaction.Parenting;
import com.xunyun.infanteduplatform.domain.interaction.Replay;
import com.xunyun.infanteduplatform.domain.interaction.SelectDynamic;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.ImageService;
import com.xunyun.infanteduplatform.service.MessageService;
import com.xunyun.infanteduplatform.service.ParentingService;
import com.xunyun.infanteduplatform.utils.DateUtils;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.SysConfigUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;

@Controller
@RequestMapping("parenting")
public class ParentingController {

  @Resource
  private AppInfoService appInfoService;

  @Resource
  private BulletinService bulletinService;

  @Resource
  private ParentingService parentingService;

  @Resource
  private ImageService imageService;

  @Resource
  private MessageService messageService;

  // 亲子任务列表获取
  @RequestMapping("/missionAcquire")
  @ResponseBody
  public Map<String, Object> missionAcquire(HttpServletRequest req) throws Exception {

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
    // 公告Id(默认为0)
    Integer bulletinId = ValueChangeUtils.changeValue(jsonobject.get("bulletinId"), 0);
    // 显示数(默认为10)
    Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"), 10);
    // 数据方向(默认为0)
    Integer direction = ValueChangeUtils.changeValue(jsonobject.get("direction"), 0);

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
      // 条数
      item.setCount(count);
      // 数据方向
      item.setDirection(direction);
      // 机构代码
      item.setOrganizationId(organizationId);
      // 公告Id
      item.setBulletinId(bulletinId);
      // 类型
      item.setBulletinType(2);
      item.setClassId(classId);
      List<SelectDynamic> listData = bulletinService.queryBulletinPage(item);
      List<Parenting> listPar = new ArrayList<Parenting>();
      if (listData != null && listData.size() > 0) {
        for (SelectDynamic dynamic : listData) {
          Parenting parenting = new Parenting();
          // 公告Id
          parenting.setBulletinId(dynamic.getBulletinId());
          // 公告标题
          parenting.setTitle(dynamic.getTitle());
          // 公告内容
          parenting.setBulletinContent(dynamic.getBulletincontent());
          // 获取创建人
          parenting.setCreatedBy(dynamic.getName());
          // 获取创建时间
          parenting.setCreationtime(dynamic.getStrcreationtime());
          listPar.add(parenting);
        }

        map.put("state", "success");
        // 提示信息
        map.put("message", "");
        // 数据列表
        map.put("data", listPar);
      } else {
        map.put("state", "error");
        map.put("message", "数据为空");
        map.put("data", "");
      }
    }
    return map;
  }

  // 亲子信息获取
  @RequestMapping("/parentingInfo")
  @ResponseBody
  public Map<String, Object> parentingInfo(HttpServletRequest req) throws Exception {

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
    // 状态
    Integer state = ValueChangeUtils.changeValue(jsonobject.get("state"), null);
    // 公告Id
    Integer bulletinId = ValueChangeUtils.changeValue(jsonobject.get("bulletinId"), null);

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (state == null) {
      map.put("state", "error");
      map.put("message", "状态不能为空，获取信息失败");
      map.put("data", "");
    } else if (bulletinId == null) {
      map.put("state", "error");
      map.put("message", "公告Id不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，查询失败!");
      map.put("data", "");
    } else {
      Map<String, Object> datas = new HashMap<String, Object>();

      // 获取公告信息
      Bulletin bulletin = bulletinService.queryByBulletinId(bulletinId);
      Parenting parenting = new Parenting();
      parenting.setBulletinId(bulletinId);
      parenting.setTitle(bulletin.getTitle());
      parenting.setBulletinContent(bulletin.getBulletinContent());
      parenting.setCreatedBy(bulletin.getCreatedBy());
      parenting.setCreationtime(DateUtils.getTimeString(bulletin.getCreationTime()));

      // 获取图片信息
      List<Image> images = imageService.queryImageList(bulletinId);
      // 将图片转为Blob返回
      List<Blob> imageContents = new ArrayList<Blob>();
      if (images.size() > 0) {
        for (Image image : images) {
          imageContents.add(image.getImageContent());
        }
        datas.put("images", imageContents);
      }
      if (state == 0) {
        // 完成任务的家长名字
        List<String> finished = parentingService.findByFinished(bulletinId);

        Map<String, Object> names = new HashMap<String, Object>();
        names.put("classId", bulletin.getClassId());
        names.put("organizationId", organizationId);
        names.put("bulletinId", bulletinId);

        // TODO:效率低
        // 未完成任务的家长名字
        List<Bulletin> unfinisheds = parentingService.findByUnfinished(names);
        List<String> unfinished = new ArrayList<String>();
        for (int i = 0; i < unfinisheds.size(); i++) {
          unfinished.add(unfinisheds.get(i).getName());
        }
        datas.put("bulletin", parenting);
        datas.put("finished", finished);
        datas.put("unfinished", unfinished);
        map.put("state", "success");
        map.put("message", "获取信息成功!");
        map.put("data", datas);
      } else {
        datas.put("bulletin", parenting);
        map.put("state", "success");
        map.put("message", "获取信息成功!");
        map.put("data", datas);
      }
    }
    return map;
  }

  // 亲子任务信息保存
  @RequestMapping("/missionSave")
  @ResponseBody
  public Map<String, Object> missionSave(HttpServletRequest req) throws Exception {

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
    // 用户id
    Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
    // 标题
    String title = (String) jsonobject.get("title");
    // 亲子任务信息
    String bulletinContent = (String) jsonobject.get("bulletinContent");
    // 图片
    List<Blob> picturecontents = (List<Blob>) jsonobject.get("picturecontents");

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
      map.put("message", "班级id不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户id不能为空，获取信息失败");
      map.put("data", "");
    } else if (title == null || title.equals("")) {
      map.put("state", "error");
      map.put("message", "标题不能为空，获取信息失败");
      map.put("data", "");
    } else if (bulletinContent == null || bulletinContent.equals("")) {
      map.put("state", "error");
      map.put("message", "亲子任务信息不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，查询失败");
      map.put("data", "");
    } else {
      Integer count = null;
      // 公告信息保存
      Bulletin bulletin = new Bulletin();
      bulletin.setOrganizationId(organizationId);
      bulletin.setClassId(classId);
      bulletin.setTitle(title);
      bulletin.setBulletinType(2);
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
          image.setLastUpdatedBy("");
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
        map.put("message", "信息保存成功");
        map.put("data", "");
      } else {
        map.put("state", "error");
        map.put("message", "信息保存失败");
        map.put("data", "");
      }
    }
    return map;
  }

  // 亲子任务回复
  @RequestMapping("/missionReply")
  @ResponseBody
  public Map<String, Object> missionReply(HttpServletRequest req) throws Exception {

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
    // 模块id
    Integer moduleId = ValueChangeUtils.changeValue(jsonobject.get("moduleId"), null);
    // 回复对象id
    Integer targetId = ValueChangeUtils.changeValue(jsonobject.get("targetId"), null);
    // 用户id
    Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
    // 回复内容
    String replayContent = (String) jsonobject.get("replayContent");
    // 图片
    List<Blob> picturecontents = (List<Blob>) jsonobject.get("picturecontents");

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (moduleId == null) {
      map.put("state", "error");
      map.put("message", "模块id不能为空，获取信息失败");
      map.put("data", "");
    } else if (targetId == null) {
      map.put("state", "error");
      map.put("message", "回复对象id不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户id不能为空，获取信息失败");
      map.put("data", "");
    } else if (replayContent == null || replayContent.equals("")) {
      map.put("state", "error");
      map.put("message", "回复内容不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，查询失败");
      map.put("data", "");
    } else {
      // 回复信息保存
      Replay replay = new Replay();
      replay.setModuleId(moduleId);
      replay.setTargetId(targetId);
      replay.setUserId(userId);
      replay.setReplayContent(replayContent);
      replay.setReplayTime(Calendar.getInstance().getTime());
      Integer count = bulletinService.insertReplay(replay);
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
          image.setLastUpdatedBy("");
          // 图片信息保存
          count = imageService.addImageList(image);
          ImageRelation imageRelation = new ImageRelation();
          imageRelation.setImageId(image.getImageId());
          imageRelation.setOrganizationId(organizationId);
          imageRelation.setModuleId(replay.getReplayId());
          // 图片关联表信息保存
          count = imageService.addImageRelation(imageRelation);
        }
      }

      if (count > 0) {
        map.put("state", "success");
        map.put("message", "信息保存成功");
        map.put("data", "");
      } else {
        map.put("state", "error");
        map.put("message", "信息保存失败");
        map.put("data", "");
      }
    }
    return map;
  }

  // 任务提醒
  @RequestMapping("/missionRemind")
  @ResponseBody
  public Map<String, Object> missionRemind(HttpServletRequest req) throws Exception {

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
    // 模块id
    Integer moduleId = ValueChangeUtils.changeValue(jsonobject.get("moduleId"), null);

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode == null || appCode.equals("")) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (moduleId == null) {
      map.put("state", "error");
      map.put("message", "模块id不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用系统代码未开启，查询失败");
      map.put("data", "");
    } else {
      // 获取公告信息
      Bulletin bulletin = bulletinService.queryByBulletinId(moduleId);
      Map<String, Object> names = new HashMap<String, Object>();
      names.put("classId", bulletin.getClassId());
      names.put("organizationId", organizationId);
      names.put("bulletinId", moduleId);
      // TODO:效率低
      // 未完成任务的家长
      List<Bulletin> bulletins = parentingService.findByUnfinished(names);
      Integer count = null;
      // 给未完成任务的家长发送一个提醒消息
      for (int i = 0; i < bulletins.size(); i++) {
        // 声明对象
        Message message = new Message();
        // 发件人id
        message.setSendOutUserId(bulletin.getUserId());
        // 收件人id
        message.setReceiveUserId(bulletins.get(i).getUserId());
        // 内容
        message.setContent(SysConfigUtils.getRemind());
        // 时间
        message.setSendOutTime(new Date());
        // 执行方法
        count = messageService.insertMessage(message);
      }
      if (count > 0) {
        map.put("state", "success");
        map.put("message", "提醒成功");
        map.put("data", "");
      } else {
        map.put("state", "error");
        map.put("message", "提醒失败");
        map.put("data", "");
      }
    }
    return map;
  }
}
