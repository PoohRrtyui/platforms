package com.xunyun.infanteduplatform.controller.passwordmanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserLogin;
import com.xunyun.infanteduplatform.service.changePwdService;
import com.xunyun.infanteduplatform.utils.MD5Util;

@Controller
@RequestMapping("changepwd")
public class changepwdController {
	@Autowired
	private changePwdService changepwdService;
	
	/*@RequestMapping("/changepwd")
    public ModelAndView layout() {
        return new ModelAndView("pages/changepwd/changepwd");
    }*/
	
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	@ResponseBody
	public int changepwd(@RequestParam(value="newpwd",required=false) String newpwd,
			HttpServletRequest request,
			@RequestParam(value="oldpwd",required=false) String oldpwd){
			int status=0; 
			oldpwd = MD5Util.MD5(oldpwd);
			HttpSession session = request.getSession();
	        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
			Integer userId = loginUserInfo.getUserid();
			SysUserLogin sysUserLogin = new SysUserLogin();
			sysUserLogin.setId(userId);
			newpwd=MD5Util.MD5(newpwd);
			String password=this.changepwdService.searPwd(sysUserLogin);
			if(oldpwd.equals(password)){
				SysUserLogin updatepwd = new SysUserLogin();
				updatepwd.setPassword(newpwd);
				updatepwd.setId(userId);
				status=this.changepwdService.updatePwd(updatepwd);
				return status;
			}else{
				return 0;
			}
			}
	
}
