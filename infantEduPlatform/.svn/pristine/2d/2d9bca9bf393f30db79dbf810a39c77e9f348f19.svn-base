package com.xunyun.infanteduplatform.controller.interaction.healthcare;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.BulletinListModel;
import com.xunyun.infanteduplatform.domain.interaction.BulletinModel;
import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.domain.interaction.ImageModel;
import com.xunyun.infanteduplatform.domain.interaction.ImageRelation;
import com.xunyun.infanteduplatform.domain.interaction.MorningCheck;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.ImageService;
import com.xunyun.infanteduplatform.service.MorningCheckService;
import com.xunyun.infanteduplatform.utils.DateUtils;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.ReadInputStreamUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;


@Controller
@RequestMapping("healthcare")
public class HealthCareController {

  @Autowired
  private BulletinService bulletinService;

  @Autowired
  private ImageService imageService;


  @Autowired
  private AppInfoService appInfoService;

  @Autowired
  private MorningCheckService morningcheckservice;

  /**
   * @author 方法描述：该班级下的健康关怀列表获取
   */
  @RequestMapping(value = "/queryHealthCareList")
  @ResponseBody
  public Map<String, Object> queryHealthCareList(HttpServletRequest req) throws Exception {
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
    //机构id
    Integer organizationId=ValueChangeUtils.changeValue(jsonobject.get("organizationId"),null);
    //班级id
    Integer classId=ValueChangeUtils.changeValue(jsonobject.get("classId"),null);
    // 公告Id(默认为0)
    Integer bulletinId=ValueChangeUtils.changeValue(jsonobject.get("bulletinId"),0);
    // 显示数(默认为10)
    Integer count=ValueChangeUtils.changeValue(jsonobject.get("count"),10);
    // 数据方向(默认为0)
    Integer direction=ValueChangeUtils.changeValue(jsonobject.get("direction"),0);

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
      map.put("message", "班级代码为空，查询失败！");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息！");
      map.put("data", "");
    } else {
      List<BulletinListModel> list = new ArrayList<BulletinListModel>();


      // 声明对象
      Bulletin item = new Bulletin();
      // 条数
      item.setCount(count);
      // 数据方向
      item.setDirection(direction);
      // 机构代码
      item.setOrganizationId(organizationId);
      // 班级代码
      item.setClassId(classId);
      // 公告Id
      item.setBulletinId(bulletinId);
      // 类型
      item.setBulletinType(4);
      // 获取数据
      List<Bulletin> listData = bulletinService.selectBulletinList(item);
      for (Bulletin bul : listData) {
        // 声明对象
        BulletinListModel bulletinListModel = new BulletinListModel();
        // 公告Id
        bulletinListModel.setBulletinId(bul.getBulletinId());
        // 公告标题
        bulletinListModel.setTitle(bul.getTitle());
        // 公告内容
        bulletinListModel.setBulletinContent(bul.getBulletinContent());
        // 获取创建时间
        String creationTime = DateUtils.getTimeString(bul.getCreationTime());
        bulletinListModel.setCreationTime(creationTime);
        // 获取创建人
        bulletinListModel.setCreatedBy(bul.getCreatedBy());

        list.add(bulletinListModel);
      }

      map.put("state", "success");
      // 提示信息
      map.put("message", "");
      // 数据列表
      map.put("data", list);
    }
    return map;
  }


  /**
   * @author 方法描述：健康信息查询
   * @throws Exception
   */
  @RequestMapping(value = "/queryHealthCare")
  @ResponseBody
  public Map<String, Object> queryHealthCare(HttpServletRequest req) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
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
    //公告id
    Integer bulletinId=ValueChangeUtils.changeValue(jsonobject.get("bulletinId"),null);
    // 判断应用状态
    if (appCode == null || "".equals(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为空，获取信息失败");
      map.put("data", "");

    } else if (bulletinId == null) {
      map.put("state", "error");
      map.put("message", "公告Id为空，查询失败！！");
      map.put("data", "");

    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息！");
      map.put("data", "");

    } else {
      // 获取公告信息
      Bulletin item = bulletinService.queryByBulletinId(bulletinId);
      // 声明对象
      BulletinModel bulletinModel = new BulletinModel();
      // 公告Id
      bulletinModel.setBulletinId(item.getBulletinId());
      // 获取公告内容
      bulletinModel.setBulletinContent(item.getBulletinContent());
      // 获取创建人
      bulletinModel.setCreatedBy(item.getCreatedBy());
      // 获取标题
      bulletinModel.setTitle(item.getTitle());
      // 获取创建时间
      String creationTime = DateUtils.getTimeString(item.getCreationTime());
      bulletinModel.setCreationTime(creationTime);
      // 查询图片列表
      List<Image> listPicture = imageService.queryImageList(bulletinId);
      List<ImageModel> list = new ArrayList<ImageModel>();
      // 查询图片内容
      for (Image image : listPicture) {

        if (image.getStrImageContent() != null) {
          // 创建ImageModel类实例
          ImageModel imageModel = new ImageModel();
          // 创建InputStream类实例
          InputStream inStream = new ByteArrayInputStream(image.getStrImageContent().getBytes());
          // 读取输入流
          byte data[] = ReadInputStreamUtils.readInputStream(inStream);
          inStream.read(data);
          // 关闭输入流
          inStream.close();
          // 获取图片内容
          imageModel.setImageContent(data);
          list.add(imageModel);

        }

      }
      bulletinModel.setImageModelList(list);

      // 状态
      map.put("state", "success");
      // 提示信息
      map.put("message", "");
      // 返回数据
      map.put("data", bulletinModel);
    }
    return map;
  }


  /**
   * @author 方法描述：健康关怀信息保存
   * @throws Exception
   */
  @RequestMapping(value = "/insertHealthCare")
  @ResponseBody
  public Map<String, Object> insertHealthCare(HttpServletRequest req

  ) throws Exception {
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
    //机构id
    Integer organizationId=ValueChangeUtils.changeValue(jsonobject.get("organizationId"),null);
    //班级id
    Integer classId=ValueChangeUtils.changeValue(jsonobject.get("classId"),null);
    //用户id
    Integer userId=ValueChangeUtils.changeValue(jsonobject.get("userId"),null);
    String title = (String) jsonobject.get("title");
    String bulletinContent = (String) jsonobject.get("bulletinContent");
    // 图片内容
    List<Blob> imageContent = (List<Blob>) jsonobject.get("imageContent");
    Map<String, Object> map = new HashMap<>();
    if (appCode == null && "".equals(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为空，获取信息失败");
      map.put("data", "");

    } else if (organizationId == null) {
      map.put("state", "error");
      map.put("message", "机构代码为空，传入参数有误！");
      map.put("data", "");

    } else if (classId == null) {
      map.put("state", "error");
      map.put("message", "班级代码为空，传入参数有误！");
      map.put("data", "");

    } else if (title == null && "".equals(title)) {
      map.put("state", "error");
      map.put("message", "标题不能为空！");
      map.put("data", "");

    } else if (bulletinContent == null && "".equals(bulletinContent)) {
      map.put("state", "error");
      map.put("message", "公告内容不能为空！");
      map.put("data", "");

    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户Id为空，传入参数有误！");
      map.put("data", "");

    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息！");
      map.put("data", "");

    } else {
      // 声明对象
      Bulletin bulletin = new Bulletin();
      // 获取机构代码
      bulletin.setOrganizationId(organizationId);
      // 获取公告类型
      bulletin.setBulletinType(4);
      // 获取公告级别
      bulletin.setBulletinLevel(1);
      // 获取班级Id
      bulletin.setClassId(classId);
      // 获取来源
      bulletin.setSources(0);
      // 获取标题
      bulletin.setTitle(title);
      // 获取公告内容
      bulletin.setBulletinContent(bulletinContent);
      // 获取创建时间
      bulletin.setCreationTime(new Date());
      // 获取修改时间
      bulletin.setLastUpdateTime(new Date());
      // 获取创建人
      bulletin.setCreatedBy((userId).toString());
      // 获取修改人
      bulletin.setLastUpdatedBy((userId).toString());
      /* 插入公告信息数据 */
      bulletinService.addBulletinInfo(bulletin);
      // 返回添加公告的公告Id
      Integer moduleId = bulletin.getBulletinId();
      try {
        for (Blob item : imageContent) {
          // 声明对象
          Image image = new Image();
          // TODO :文件流获取
          File file = InteractionUtils.blob2File(item);
          // 图片名称
          String fileName = file.getName();
          // 名称（没有扩展名）
          String name = fileName.substring(0, fileName.lastIndexOf('.'));
          // 扩展名
          String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
          // 获取图片内容
          image.setImageContent(item);
          // 机构Id
          image.setOrganizationId(organizationId);
          // 文件名称
          image.setImageName(name);
          // 大小
          image.setImageSize(file.length());
          // 扩展名
          image.setExtension(extension);
          // 创建人
          image.setCreatedBy(userId.toString());
          // 创建时间
          image.setCreationTime(new Date());
          // 最终修改人
          image.setLastUpdatedBy(userId.toString());
          // 最终修改时间
          image.setLastUpdateTime(new Date());
          // 添加图片表
          imageService.addImageList(image);

          // 返回添加图片的图片Id
          Integer imageId = image.getImageId();
          // 声明对象
          ImageRelation imageRelation = new ImageRelation();
          // 模块Id
          imageRelation.setModuleId(moduleId);
          // 图片Id
          imageRelation.setImageId(imageId);
          // 机构Id
          imageRelation.setOrganizationId(organizationId);
          // 添加图片关联表
          imageService.addImageRelation(imageRelation);
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      // 状态
      map.put("state", "success");
      // 提示信息
      map.put("message", "保存成功!");
      map.put("data", "");
    }
    return map;
  }

  /**
   * @author 方法描述：查询该班级下的学生晨检信息
   * 
   */
  @RequestMapping(value = "/queryStudentInClasssList")
  @ResponseBody
  public Map<String, Object> queryStudentInClasssList(HttpServletRequest req

  ) throws Exception {
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
    //机构id
    Integer organizationId=ValueChangeUtils.changeValue(jsonobject.get("organizationId"),null);
    //班级id
    Integer classId=ValueChangeUtils.changeValue(jsonobject.get("classId"),null);
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


      MorningCheck morningcheck = new MorningCheck();
      // 参数放入实体类对象中
      morningcheck.setClassId(classId);
      morningcheck.setOrganizationId(organizationId);

      // 获取数据
      List<MorningCheck> listData = morningcheckservice.queryStudentInClasssList(morningcheck);



      // 状态
      map.put("state", "success");
      // 提示信息
      map.put("message", "");
      // 数据列表
      map.put("data", listData);

    }
    return map;
  }



  /**
   * @author 方法描述：新增当天学生晨检信息
   * @return
   * 
   */
  @RequestMapping(value = "/insertCheckMoring")
  @ResponseBody
  public Map<String, Object> insertCheckMoring(HttpServletRequest req

  ) throws Exception {
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
    String checkSummary = (String) jsonobject.get("checkSummary");
    //用户id
    Integer userId=ValueChangeUtils.changeValue(jsonobject.get("userId"),null);
    //教师id
    Integer teacherId=ValueChangeUtils.changeValue(jsonobject.get("teacherId"),null);
    //状态
    Integer state=ValueChangeUtils.changeValue(jsonobject.get("state"),null);
    Map<String, Object> map = new HashMap<>();
    MorningCheck morningcheck = new MorningCheck();

    if (appCode == null && "".equals(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为空，获取信息失败");
      map.put("data", "");

    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户id为空，传入参数有误！");
      map.put("data", "");

    } else if (teacherId == null) {
      map.put("state", "error");
      map.put("message", "晨检老师id不能为空！");
      map.put("data", "");

    } else if (state == null) {
      map.put("state", "error");
      map.put("message", "状态不能为空！");
      map.put("data", "");

    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息！");
      map.put("data", "");
    } else {
      Date date = new Date();
      // 年月日格式的String类型的晨检时间
      String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
      // 年月日格式的Date类型的晨检时间
      morningcheck.setCheckTime(new SimpleDateFormat("yyyy-MM-dd").parse(str));
      // 学生id
      morningcheck.setUserId(userId);
      // 取当前时间和userid查看此当前学生是否晨检过
      Integer morningCheckNumber = morningcheckservice.queryMorningCheckNumber(morningcheck);
      if (morningCheckNumber != 0) {
        map.put("state", "error");
        map.put("message", "该学生今天已晨检过!");
        map.put("data", "");
      } else {
        // 学生id
        morningcheck.setUserId(userId);
        // 健康状态
        morningcheck.setState(state);
        // 描述
        morningcheck.setCheckSummary(checkSummary);
        // 年月日格式的Date类型的晨检时间
        morningcheck.setCheckTime(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        // 晨检老师id
        morningcheck.setTeacherId(teacherId);
        // 添加
        morningcheckservice.insertCheckMoring(morningcheck);
        // 状态
        map.put("state", "success");
        // 提示信息
        map.put("message", "保存成功!");
        map.put("data", "");
      }
    }
    return map;
  }
}
