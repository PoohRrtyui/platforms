//获取项目url（含端口）
var serverUrl = window.location.host;
// 获取项目名称
var serverProPath = window.location.pathname;
var serverProName = serverProPath.split("/")[1];
// 拼接项目访问地址
var projectUrl = "http://" + serverUrl + "/" + serverProName;

var userMenus = {
	classManage:"page/classmanage",
    bureauUrl: "page/bureau",
    headImgUrl: "page/headImage",
    codeMasterUrl: "page/codemaster",
    department: "departmentInfo/show",
    pwdUrl: "page/passwordmanage",
    bindUserInfo: "page/bindUserInfo",
    userinfo:"user/index",
    updateSchoolInfo:"page/schoolInfo",
    userDetail:"user/detail",
    appInfo:"appInfo/show",
    morningcheck:"page/morningcheck",
    bulletin:"page/bulletin",
    classDynamic:"page/classDynamic",
    discussion:"page/discussion"
};

var _i="<li class=\"header\">栏目菜单</li>";
/*var _a1="<li class=\"treeview active\"><a href=\"page/headImage \"><i class=\"fa fa-link\"></i><span>";*/
var _a="<li class=\"treeview\"><a href=\"\"><i class=\"fa fa-link\"></i><span>";
//var _b="</span><i class=\"fa fa-angle-left pull-right\"></i></a><ul class=\"treeview-menu\"><li><a href=\"";
var _b2="</span><i class=\"fa fa-angle-left pull-right\"></i></a>";
var _b1="<ul class=\"treeview-menu\"><li><a href=\"";
var _c="\">";
//var _d="</a></li></ul></li>";
var _d1="</a></li></ul>";
var _d2="</li>";
var _e="<li class=\"treeview\"><a href=\"";
var _f="\"><i class=\"fa fa-link\"></i><span>";
var _g="</span></a></li>";


var userType = {
		path : '',
		init : function(url) {
			  queryPltMenu();
			  //顶部用户栏中显示已登录用户的昵称
			  queryHeadNickName();
		}
	};

/*$(function () {
    queryPltMenu();
  //顶部用户栏中显示吃登录用户的昵称
    queryHeadNickName();
});*/

// 拼接左侧菜单
function queryPltMenu() {
	//获取登陆用户权限信息
     $.ajax({
    	 dataType:"JSON",
          url:"classManage/queryLoginInfo",
        	  success:function(data){
        		  var organizationId="?schoolId="+data.organizationId;
        		  //var userorganizationId="?type=S&schoolId="+data.organizationId;
        		 if(data.isAdmin == 1 || data.isAdmin ==2) { //单位/学校管理员(2为默认管理员，在此无异)
        			if(data.organizationType == 'S'){//学校管理员
        					var html =_i
        					+_e+userMenus.updateSchoolInfo+_f+"学校信息"+_g
        					+_e+userMenus.department+organizationId+_f+"部门管理"+_g
        					+_e+userMenus.classManage+organizationId+_f+"班级管理"+_g
        					+_e+userMenus.userinfo+_f+"用户管理"+_g
        					+_a+"基本信息"+_b2
        					+_b1+userMenus.userDetail+_c+"用户信息"+_d1
        					+_b1+userMenus.headImgUrl+_c+"头像上传"+_d1
        					+_d2
        					+_e+userMenus.bindUserInfo+_f+"身份信息"+_g
        					+_e+userMenus.pwdUrl+_f+"修改密码"+_g;
        					$(".sidebar-menu").append(html);
        				}else{
        				if(data.organizationType =='B'){//单位管理员
        					var html=_i
        					+_a+"机构管理"+_b2
        					+_b1+userMenus.bureauUrl+_c+"单位管理"+_d1+_d2
        					+_a+"基本信息"+_b2
        					+_b1+userMenus.userDetail+_c+"用户信息"+_d1
        					+_b1+userMenus.headImgUrl+_c+"头像上传"+_d1
        					+_d2
        					+_e+userMenus.bindUserInfo+_f+"身份信息"+_g
        					+_e+userMenus.pwdUrl+_f+"修改密码"+_g;
        					$(".sidebar-menu").append(html);
        				}
        			}
        		 }else{
        			 if(data.isAdmin==0){//普通用户
        				 var html=_i
        				 +_a+"基本信息"+_b2
     					 +_b1+userMenus.userDetail+_c+"用户信息"+_d1
     					 +_b1+userMenus.headImgUrl+_c+"头像上传"+_d1
     					 +_d2
     					 +_e+userMenus.bindUserInfo+_f+"身份信息"+_g
        				 +_e+userMenus.pwdUrl+_f+"修改密码"+_g;
						 
			             $(".sidebar-menu").append(html);
        			 }else{
        				 if(data.isAdmin==3){//超级管理员
        				     var html=_i
        				     +_e+userMenus.appInfo+_f+"应用管理"+_g
        				     +_e+userMenus.codeMasterUrl+_f+"数据字典管理"+_g
        				     +_a+"机构管理"+_b2
         					 +_b1+userMenus.bureauUrl+_c+"单位管理"+_d1
         					 +_d2
        				     +_a+"基本信息"+_b2
         					 +_b1+userMenus.userDetail+_c+"用户信息"+_d1
         					 +_b1+userMenus.headImgUrl+_c+"头像上传"+_d1
         					 +_d2
        				     +_e+userMenus.pwdUrl+_f+"修改密码"+_g
        				     +_a+"APP后台管理"+_b2
         					 +_b1+userMenus.bureauUrl+_c+"健康关怀"+_d1
         					 +_b1+userMenus.bulletin+_c+"通知公告"+_d1
         					 +_b1+userMenus.classDynamic+_c+"班级动态"+_d1
         					 +_b1+userMenus.discussion+_c+"交流社区"+_d1
         					 +_b1+userMenus.morningcheck+_c+"晨检信息"+_d1
         					 +_d2
        				     ; 
        					  $(".sidebar-menu").append(html);
        				 }
        			 }
        		 }
        	  },
        	  error:function(data){
        		  alert("查询用户信息失败！");
        	  }
     });
}

//顶部用户栏中显示吃登录用户的昵称和头像
function queryHeadNickName() {
	$.ajax({
		type:"POST",
		dataType:"JSON",
        url:"classManage/queryLoginInfo",
        success:function(data) {
        	$(".hidden-xs:first").append(data.nickName);
        	projectUrl+="/resources/uploadImages/userLogo/";
        	projectUrl+=data.photourl;
        	$(".user-image").attr("src",projectUrl);
        },
        error:function(data) {
        	alert("查询用户信息失败！");
        }
	
	});
}



