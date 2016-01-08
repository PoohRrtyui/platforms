/**
 * 用户对象
 * Created by Pooh on 2015/12/11.
 */
function userType(isAdmin,orgType)  {
    this.isAdmin = isAdmin;
    this.orgType = orgType;
    if(isAdmin == 1 || isAdmin ==2){//单位/学校管理员(2为默认管理员，在此无异)
        if(this.orgType == 'S'){//学校管理员
            return menus(0,0,0,1,0);
        }else if(this.orgType == 'B'){//单位管理员
            return menus();
        }else{
            return false;
        }
    }else if(isAdmin == 3){//超级管理员

    }else if(isAdmin == 0){//普通用户

    }else{
        return false;
    }
}

var menus = {
    bureauUrl : "/page/bureau",
    headImgUrl : "/page/headImage",
    codeMasterUrl : "/page/codemaster",
    department : "/departmentInfo/show",
    pwdUrl : "/page/passwordmanage"
};
/**
 * @param _a 单位管理
 * @param _b 头像设置
 * @param _c 字典关联
 * @param _d 部门管理
 * @param _e 密码管理
 */
function menus(_a,_b,_c,_d,_e){
    if(_a!=0){
        this._a = menus.bureauUrl;
    }else if(_b!=0){
        this._b = menus.headImgUrl;
    }else if(_c!=0){
        this._c = menus.codeMasterUrl;
    }else if(_d!=0){
        this._d = menus.department;
    }else if(_e!=0){
        this._e = menus.pwdUrl;
    }
}
