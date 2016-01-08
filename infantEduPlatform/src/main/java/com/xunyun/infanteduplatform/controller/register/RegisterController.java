package com.xunyun.infanteduplatform.controller.register;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.service.RegisterService;
import com.xunyun.infanteduplatform.utils.MD5Util;

@Controller
@RequestMapping("register")
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  // 页面跳转
  @RequestMapping("/show")
  public ModelAndView updatedepartment() {

    return new ModelAndView("pages/register/register");
  }

  // 注册查重
  @ResponseBody
  @RequestMapping("/updateValidate")
  public int updateValidate(String name, String mobile) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name.toLowerCase());
    map.put("mobile", mobile);
    return registerService.updateValidate(map);
  }
  
  // 用户注册查重
  @ResponseBody
  @RequestMapping("/userManageValidate")
  public int userValidate(@RequestParam(defaultValue="null")String name, String mobile,Integer userId,String Mobile) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", name.toLowerCase());
    map.put("mobile", mobile);
    map.put("userId", userId);
    map.put("Mobile", Mobile);
    if(mobile!=""&&mobile!=null&&!mobile.equals(Mobile)){
      return registerService.updateValidate(map);
    }else{
      return 0;
    }
  }

  // 用户注册
  @ResponseBody
  @RequestMapping("/register")
  public int register(SysUserLogin userRegister) {
    return registerService.register(userRegister);
  }

}
