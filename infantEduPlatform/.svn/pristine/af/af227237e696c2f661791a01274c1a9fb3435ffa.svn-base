package com.xunyun.infanteduplatform.controller.interaction.login;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.BufferedReader;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.domain.interaction.UserInfo;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.LoginService;
import com.xunyun.infanteduplatform.service.LoginUserService;
import com.xunyun.infanteduplatform.service.RegisterService;
import com.xunyun.infanteduplatform.service.UserService;
import com.xunyun.infanteduplatform.utils.FileUploadUtil;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.MD5Util;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;

@Controller
@RequestMapping("registerApp")
public class RegisterAppController {

  @Resource
  private RegisterService registerService;
  @Resource
  private UserService userService;
  @Resource
  private LoginService loginService;
  @Resource
  private LoginUserService loginUserService;
  @Resource
  private AppInfoService appInfoService;
  @Resource
  private BulletinService bulletinService;

  // 用户注册
  @RequestMapping("/register")
  @ResponseBody
  public Map<String, Object> register(HttpServletRequest req) throws Exception {

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
    // 手机号码
    String mobile = (String) jsonobject.get("mobile");
    // 密码
    String password = (String) jsonobject.get("password");

    Map<String, Object> map = new HashMap<String, Object>();
    if (password.equals("") || password == null) {
      map.put("state", "error");
      map.put("message", "密码不能为空，注册失败");
      map.put("data", "");
    } else if (mobile.equals("") || mobile == null) {
      map.put("state", "error");
      map.put("message", "手机号码不能为空，注册失败");
      map.put("data", "");
    } else if (password.length() < 8) {
      map.put("state", "error");
      map.put("message", "密码长度不能少于8位，注册失败");
      map.put("data", "");
    } else {
      Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
      Matcher matcher = pattern.matcher(mobile);
      if (!matcher.find()) {
        map.put("state", "error");
        map.put("message", "手机号码格式错误，注册失败");
        map.put("data", "");
      } else {
        // 手机号码查重
        Map<String, Object> mobileMap = new HashMap<String, Object>();
        mobileMap.put("mobile", mobile);
        int mobileCount = registerService.registerValidate(mobileMap);

        if (mobileCount != 0) {
          map.put("state", "error");
          map.put("message", "此手机号码已注册，注册失败");
          map.put("data", "");
        } else {
          // 用戶注册
          password = MD5Util.MD5(password);
          SysUserLogin userRegister = new SysUserLogin();
          userRegister.setName(mobile);
          userRegister.setPassword(password);
          userRegister.setMobile(mobile);
          int count = registerService.register(userRegister);

          if (count > 0) {
            map.put("state", "success");
            map.put("message", "注册成功");
            map.put("data", "");
          }
        }
      }
    }
    return map;
  }

  // 用户登录
  @RequestMapping("/login")
  @ResponseBody
  public Map<String, Object> login(HttpServletRequest req) throws Exception {
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
    // 账号
    String name = (String) jsonobject.get("name");
    // 密码
    String password = (String) jsonobject.get("password");

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode.equals("") || appCode == null) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (name.equals("") || name == null) {
      map.put("state", "error");
      map.put("message", "账号不能为空，登陆失败");
      map.put("data", "");
    } else if (password.equals("") || password == null) {
      map.put("state", "error");
      map.put("message", "密码不能为空，登陆失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息");
      map.put("data", "");
    } else {
      SysUserLogin loginer = new SysUserLogin();
      loginer.setName(name.toLowerCase());
      loginer.setPassword(MD5Util.MD5(password));

      // 登陆验证
      Integer logged = loginService.entry(loginer);
      if (logged == null) {
        map.put("state", "error");
        map.put("message", "账号或密码错误，登陆失败");
        map.put("data", "");
      } else {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUserid(logged);

        // 获取用户信息
        loginUserInfo = loginUserService.findLoginUserById(loginUserInfo);
        // TODO:图片转换
        loginUserInfo.setImageContent(InteractionUtils.getPhoto(loginUserInfo.getPhotourl()));

        map.put("state", "success");
        map.put("message", "登陆成功");
        map.put("data", loginUserInfo);
      }
    }
    return map;
  }

  // 用户信息获取
  @RequestMapping("/select")
  @ResponseBody
  public Map<String, Object> select(HttpServletRequest req) throws Exception {

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

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode.equals("") || appCode == null) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户ID不能为非法数字，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息");
      map.put("data", "");
    } else {
      // 获取用户信息
      UserInfo userInfo = userService.searchById(userId);
      if (userInfo == null) {
        map.put("state", "error");
        map.put("message", "此用户不存在");
        map.put("data", "");
      } else {
        // TODO：图片转换
        userInfo.setImageContent(InteractionUtils.getPhoto(userInfo.getPhotoUrl()));
        map.put("state", "success");
        map.put("message", "获取信息成功");
        map.put("data", userInfo);
      }
    }
    return map;
  }

  // 用户头像修改
  @RequestMapping("/updateImage")
  @ResponseBody
  public Map<String, Object> updateImage(HttpServletRequest request,
      @RequestParam("imgFile") MultipartFile imgFile, @RequestParam("appCode") String appCode,
      @RequestParam("userId") Integer userId, @RequestParam("x") String x,
      @RequestParam("y") String y, @RequestParam("w") String w, @RequestParam("h") String h)
      throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode.equals("") || appCode == null) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户ID不能为空，获取信息失败");
      map.put("data", "");
    } else if (imgFile == null) {
      map.put("state", "error");
      map.put("message", "图片不能为空，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息");
      map.put("data", "");
    } else if (!userService.findById(userId)) {
      map.put("state", "error");
      map.put("message", "此用户不存在");
      map.put("data", "");
    } else {
      // 绝对路径
      String realPath = request.getSession().getServletContext().getRealPath("/");
      String resourcePath = "resources/uploadImages/interactionImages/";
      // 获取用户id，文件夹名称更新到用户头像字段
      String idString = Integer.toString(userId);
      String imgFolder = idString;
      if (FileUploadUtil.allowUpload(imgFile.getContentType())) {
        // 文件夹存在时候的路径
        String existPath = realPath + resourcePath + imgFolder + "/";
        // 上传路径
        File dir = new File(existPath);
        if (!dir.exists()) {
          dir.mkdirs();
        }
        // 上传
        File file = new File(dir, "_src.jpg");
        imgFile.transferTo(file);

        int imageX = Integer.parseInt(x);
        int imageY = Integer.parseInt(y);
        int imageH = Integer.parseInt(h);
        int imageW = Integer.parseInt(w);

        try {
          Image img;
          ImageFilter cropFilter;
          BufferedImage bi = ImageIO.read(new File(existPath + "\\_src.jpg"));// 读取原图

          int srcWidth = bi.getWidth();
          int srcHeight = bi.getHeight();
          if (srcWidth >= imageW && srcHeight >= imageH) {// 裁剪后的比原图小
            Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
            cropFilter = new CropImageFilter(imageX, imageY, imageW, imageH);
            img =
                Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image.getSource(), cropFilter));
            BufferedImage tag = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            // 输出裁剪后的图片
            ImageIO.write(tag, "JPEG", new File(existPath + "\\_cut.jpg"));
            File cutedFile = new File(existPath, "\\_cut.jpg");
            // 读取裁剪后的图片
            BufferedImage cutedImage = ImageIO.read(new File(existPath + "\\_cut.jpg"));

            BufferedImage tag2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
            tag2.getGraphics().drawImage(
                cutedImage.getScaledInstance(50, 50, cutedImage.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(tag2, "JPEG", new File(existPath + "\\50x50.jpg"));

            // 删除裁剪后的图片
            cutedFile.delete();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        // 删除原图
        file.delete();

        // 修改用户头像
        Map<String, Object> images = new HashMap<String, Object>();
        images.put("userId", userId);
        images.put("imgFolder", imgFolder);
        Integer count = userService.updateImage(images);
        if (count > 0) {
          map.put("state", "success");
          map.put("message", "修改成功");
          map.put("data", "");
        } else {
          map.put("state", "error");
          map.put("message", "修改失败");
          map.put("data", "");
        }
      }
    }
    return map;
  }

  // 用户信息修改
  @RequestMapping("/updateUser")
  @ResponseBody
  public Map<String, Object> updateUser(HttpServletRequest req) throws Exception {

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
    // 手机号码
    String mobile = (String) jsonobject.get("mobile");
    // 邮箱
    String email = (String) jsonobject.get("email");
    // 昵称
    String nickName = (String) jsonobject.get("nickName");
    // 姓名
    String name = (String) jsonobject.get("name");
    // 性别码
    Integer genderCode = ValueChangeUtils.changeValue(jsonobject.get("genderCode"), null);
    // 身份证号
    String idNo = (String) jsonobject.get("idNo");
    // 出生日期(yyyy-MM-dd)
    Date birthday = ValueChangeUtils.changeDate(jsonobject.get("birthday"), null);

    // 联系电话
    String telephone = (String) jsonobject.get("telephone");
    // QQ
    String qq = (String) jsonobject.get("qq");
    // 个人介绍
    String description = (String) jsonobject.get("description");

    Map<String, Object> map = new HashMap<String, Object>();
    if (appCode.equals("") || appCode == null) {
      map.put("state", "error");
      map.put("message", "应用系统代码不能为空，获取信息失败");
      map.put("data", "");
    } else if (userId == null) {
      map.put("state", "error");
      map.put("message", "用户ID不能为非法数字，获取信息失败");
      map.put("data", "");
    } else if (!appInfoService.findByAppCode(appCode)) {
      map.put("state", "error");
      map.put("message", "应用状态为非开启状态，无权获取用户信息");
      map.put("data", "");
    } else if (!userService.findById(userId)) {
      map.put("state", "error");
      map.put("message", "此用户不存在");
      map.put("data", "");
    } else if ((mobile != null && !mobile.equals("")) || (email != null && !email.equals(""))) {
      // 验证用户基本信息
      map = updateUserinfo(nickName, name, genderCode, idNo, birthday, telephone, qq, description);
      if (map.get("message").equals("验证成功")) {
        // 验证用户手机和邮箱信息
        map = mobileReg(userId, mobile, email);
        if (map.get("message").equals("修改成功")) {
          // 通过全部验证后修改对应的信息
          map =
              chooseUserinfo(userId, nickName, name, genderCode, idNo, birthday, telephone, qq,
                  description);
        }
      } else if (!map.get("message").equals("验证成功")) {
        // 当没有用户基本信息的时候直接修改手机和邮箱信息
        map = mobileReg(userId, mobile, email);
      }
    } else {
      // 验证用户基本信息
      map = updateUserinfo(nickName, name, genderCode, idNo, birthday, telephone, qq, description);
      if (map.get("message").equals("验证成功")) {
        // 通过全部验证后修改对应的信息
        map =
            chooseUserinfo(userId, nickName, name, genderCode, idNo, birthday, telephone, qq,
                description);
      }
    }
    return map;
  }

  // 修改用户基本信息
  private Map<String, Object> chooseUserinfo(Integer userId, String nickName, String name,
      Integer genderCode, String idNo, Date birthday, String telephone, String qq,
      String description) {
    Map<String, Object> updateMap = new HashMap<String, Object>();

    // 修改用户信息
    Map<String, Object> map = new HashMap<String, Object>();
    updateMap.put("userId", userId);
    updateMap.put("nickName", nickName);
    updateMap.put("name", name);
    updateMap.put("genderCode", genderCode);
    updateMap.put("idNo", idNo);
    updateMap.put("birthday", birthday);
    updateMap.put("telephone", telephone);
    updateMap.put("qq", qq);
    updateMap.put("description", description);
    updateMap.put("lastUpdateTime", Calendar.getInstance().getTime());
    updateMap.put("lastUpdatedBy", userId.toString());
    Integer count = userService.updateUserinfo(updateMap);
    if (count != 0) {
      map.put("state", "success");
      map.put("message", "修改成功");
      map.put("data", "");
    } else {
      map.put("state", "error");
      map.put("message", "修改失败");
      map.put("data", "");
    }
    return map;
  }

  // 修改用户手机号码或邮箱
  private Map<String, Object> chooseMobile(Integer userId, String mobile, String email) {
    Map<String, Object> map = new HashMap<String, Object>();

    // 用户手机号码查重
    Map<String, Object> mobileMap = new HashMap<String, Object>();
    mobileMap.put("userId", userId);
    mobileMap.put("mobile", mobile);
    int mobileCount = registerService.registerValidate(mobileMap);

    // 用户邮箱查重
    Map<String, Object> emailMap = new HashMap<String, Object>();
    emailMap.put("userId", userId);
    emailMap.put("email", email);
    int emailCount = registerService.registerValidate(emailMap);

    // 修改用户手机号码或邮箱信息
    Map<String, Object> me = new HashMap<String, Object>();
    me.put("userId", userId);
    me.put("mobile", mobile);
    me.put("email", email);
    me.put("lastUpdateTime", Calendar.getInstance().getTime());
    me.put("lastUpdatedBy", userId.toString());
    int count = 0;
    if (mobile != null && mobileCount != 0) {
      map.put("state", "error");
      map.put("message", "此手机号码已注册，修改失败");
      map.put("data", "");
    } else if (email != null && emailCount != 0) {
      map.put("state", "error");
      map.put("message", "此邮箱已注册，修改失败");
      map.put("data", "");
    } else {
      count = userService.updateLoginMobile(me);
      count = userService.updateUserInfoMobile(me);
      if (count != 0) {
        map.put("state", "success");
        map.put("message", "修改成功");
        map.put("data", "");
      } else {
        map.put("state", "error");
        map.put("message", "修改失败");
        map.put("data", "");
      }
    }
    return map;
  }

  // 验证手机号码格式
  private Map<String, Object> mobileReg(Integer userId, String mobile, String email) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (mobile != null && !mobile.equals("")) {
      Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
      Matcher matcher = pattern.matcher(mobile);
      if (!matcher.find()) {
        map.put("state", "error");
        map.put("message", "手机号码格式错误，修改失败");
        map.put("data", "");
      } else if (email != null && !email.equals("")) {
        map = emailReg(userId, mobile, email);
      } else {
        map = chooseMobile(userId, mobile, email);
      }
    } else if (email != null) {
      map = emailReg(userId, mobile, email);
    }
    return map;
  }

  // 验证邮箱格式
  private Map<String, Object> emailReg(Integer userId, String mobile, String email) {
    Map<String, Object> map = new HashMap<String, Object>();
    Pattern emailp =
        Pattern
            .compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    Matcher emailm = emailp.matcher(email);
    if (!emailm.find()) {
      map.put("state", "error");
      map.put("message", "邮箱格式错误，修改失败");
      map.put("data", "");
    } else {
      map = chooseMobile(userId, mobile, email);
    }
    return map;
  }

  private Map<String, Object> updateUserinfo(String nickName, String name, Integer genderCode,
      String idNo, Date birthday, String telephone, String qq, String description) {
    Map<String, Object> map = new HashMap<String, Object>();
    if ((nickName != null && !nickName.equals("")) || (name != null && !name.equals(""))
        || genderCode != null || (idNo != null && !idNo.equals("")) || birthday != null
        || (telephone != null && !telephone.equals("")) || (qq != null && !qq.equals(""))
        || (description != null && !description.equals(""))) {
      map.put("state", "success");
      map.put("message", "验证成功");
      map.put("data", "");
    } else {
      map.put("state", "error");
      map.put("message", "验证失败");
      map.put("data", "");
    }
    return map;
  }
}
