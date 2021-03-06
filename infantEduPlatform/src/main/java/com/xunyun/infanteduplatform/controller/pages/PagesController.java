package com.xunyun.infanteduplatform.controller.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面controller Created by PoohD on 2015/11/27.
 */
@Controller
@RequestMapping("page")
public class PagesController {

  @Autowired
  private SchoolService schoolService;

  @RequestMapping("/data")
  public ModelAndView layout() {
    return new ModelAndView("pages/dataTables");
  }

  /**
   * 单位信息列表页面
   * 
   * @return ModelAndView
   */
  @RequestMapping("/bureau")
  public ModelAndView bureau() {
    return new ModelAndView("pages/bureau/bureauInfo");
  }

  /**
   * 单位操作页面
   * 
   * @return ModelAndView
   */
  @RequestMapping("/bureauOp")
  public ModelAndView bureauOperation(
      @RequestParam(value = "bureauId", required = false) Long BureauId) {
    ModelAndView mav = new ModelAndView("pages/bureau/bureauOp");
    mav.addObject("BureauId", BureauId);
    return mav;
  }

  /**
   * 学校操作
   * 
   * @return ModelAndView
   */
  @RequestMapping("/schoolOp")
  public ModelAndView schoolOperation(
      @RequestParam(value = "schoolId", required = false) Integer schoolId) {
    ModelAndView mav = new ModelAndView("pages/school/schoolOp");
    mav.addObject("schoolId", schoolId);
    return mav;
  }

  @RequestMapping("/classmanage")
  public ModelAndView skipQueryClass() {
    return new ModelAndView("pages/classmanage/classManage");
  }


  @RequestMapping("/passwordmanage")
  public ModelAndView changepwd() {
    return new ModelAndView("pages/passwordmanage/changepwd");
  }

  /* 数据字典页面跳转 */
  @RequestMapping("/codemaster")
  public ModelAndView codeMasterView() {
    return new ModelAndView("pages/codeMaster/codeMaster");
  }
  
  /* 健康关怀 */
  @RequestMapping("/morningcheck")
  public ModelAndView morningCheck() {
    return new ModelAndView("pages/kindergarten/morningCheck");
  }
  
  /* 通知公告*/
  @RequestMapping("/bulletin")
  public ModelAndView bulletin() {
    return new ModelAndView("pages/kindergarten/bulletin");
  }
 
  
  /* 通知公告*/
  @RequestMapping("/classDynamic")
  public ModelAndView classDynamic() {
    return new ModelAndView("pages/kindergarten/classDynamic");
  }

  
  /* 交流社区*/
  @RequestMapping("/discussion")
  public ModelAndView discussion() {
    return new ModelAndView("pages/kindergarten/discussionInfo");
  }
  @RequestMapping("/headImage")
  public ModelAndView headImage(HttpServletRequest request) {

    ModelAndView mView = new ModelAndView("pages/headimage/headimage");

    // 获取用户id，文件夹名称更新到用户头像字段
    HttpSession session = request.getSession();
    LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");

    String scheme = request.getScheme();
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    String contextPath = request.getContextPath();
    String resourcePath = "/defaultPhoto/";

    String subPath = "/static/images/uploadImages/userLogo";
    try {
      // 初始加载路径
      String loadPath =
              scheme + "://" + serverName + ":" + serverPort + contextPath + subPath
                      + resourcePath.replaceAll("\\|", "/");
      
      // 文件夹存在时候的路径
      String existPath =
              scheme + "://" + serverName + ":" + serverPort + contextPath + subPath
                      + "/" + loginUserInfo.getPhotourl() + "/";

      if (!"".equals(loginUserInfo.getPhotourl()) && loginUserInfo.getPhotourl() != null) {
        mView.addObject("pic1", existPath + "180x180.jpg");
        mView.addObject("pic2", existPath + "50x50.jpg");
        mView.addObject("pic3", existPath + "30x30.jpg");
        mView.addObject("imgFile", existPath + "180x180.jpg");
      } else {
        mView.addObject("pic1", loadPath + "180x180.jpg");
        mView.addObject("pic2", loadPath + "128x128.jpg");
        mView.addObject("pic3", loadPath + "64x64.jpg");
        mView.addObject("imgFile", loadPath + "180x180.jpg");
      }
    }catch (Exception e){
      System.out.println(e.getMessage());
    }

    return mView;
  }

  /**
   * 绑定用户信息
   * 
   * @return 绑定用户页面
   */
  @RequestMapping("/bindUserInfo")
  public ModelAndView bindUserInfo() {
    return new ModelAndView("pages/binduserinfo/bindUserInfo");
  }

  /**
   * 显示学校信息页面
   * 
   * @return view (pages/school/SchoolInfo)
   */
  @RequestMapping("/schoolInfo")
  public ModelAndView schoolInfo(HttpServletRequest request) {
    HttpSession session = request.getSession();
    LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
    Integer orgId = loginUserInfo.getOrganizationid();
    ModelAndView modelAndView = new ModelAndView("pages/school/SchoolInfo");
    modelAndView.addObject("schoolId", orgId);
    return modelAndView;
  }

  /**
   * 顶部用护栏中点击登出按钮，跳转首页并且清空登录的session
   * 
   * @return 顶部用护栏中点击登出按钮，跳转首页并且清空登录的session
   */
  @RequestMapping("/logOff")
  public ModelAndView LogOff(HttpServletRequest request) {
    HttpSession session = request.getSession();
    LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
    if (loginUserInfo != null) {
      try {
        session.invalidate();
      } catch (Exception e) {
      }
    }
    return new ModelAndView("pages/login/login");
  }
}
