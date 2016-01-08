package com.xunyun.infanteduplatform.controller.classmanage;

import com.xunyun.infanteduplatform.domain.ClassManageEntity;
import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.TeacherInClassEntity;
import com.xunyun.infanteduplatform.domain.UserInClassEntity;
import com.xunyun.infanteduplatform.service.ClassManageService;

import com.xunyun.infanteduplatform.service.TeacherInClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller @RequestMapping("classManage") public class ClassManageController {

    // 注入
    @Autowired private ClassManageService classmanageservice;

    @Autowired private TeacherInClassService teacherInClassService;
    // 初始查询班级
    @RequestMapping("/queryClassList") @ResponseBody public Map<Object, Object> queryClassList(
        @RequestParam(value = "schoolId", required = false) Integer schoolId,
        @RequestParam(value = "draw", required = false) Integer draw,
        @RequestParam(value = "start", required = false) Integer start,
        @RequestParam(value = "length", required = false) Integer length) {

        // ClassManageEntity classmanageentity = new ClassManageEntity();
        // 开始条数
        int startNumber = start + 1;
        // 结束条数
        int endNumber = start + length;
        // 总数目
        int countNumber = 0;

        // 声明对象
        ClassManageEntity classmanageentity = new ClassManageEntity();

        // 开始条数
        classmanageentity.setStartNumber(startNumber);
        // 结束条数
        classmanageentity.setEndNumber(endNumber);

        classmanageentity.setSchoolId(schoolId);

        // 获取数据
        List<ClassManageEntity> listData = classmanageservice.queryClass(classmanageentity);

        int j = listData.size();
        for (int i = 0; i < j; i++) {
            String stringEstablishmentDate =
                new SimpleDateFormat("yyyy年MM月").format(listData.get(i).getEstablishmentDate());
            listData.get(i).setStringEstablishmentDate(stringEstablishmentDate);
        }
        // 数据不为空，取总数
        if (listData != null && listData.size() > 0) {
            countNumber = listData.get(0).getCountNumber();
        }

        // 返回对象
        Map<Object, Object> info = new HashMap<Object, Object>();

        // 数据列表
        info.put("data", listData);
        // 总条数
        info.put("recordsTotal", countNumber);
        // 过滤条数
        info.put("recordsFiltered", countNumber);
        // 当前页数
        info.put("draw", draw);

        return info;
    }



    // 跳转增加班级页面
    @RequestMapping("/skipclassadd") @ResponseBody public ModelAndView skipClassAdd(
        @RequestParam(value = "schoolId", required = false) Integer schoolId) {
        ModelAndView modelAndView = new ModelAndView("pages/classmanage/addclass");
        modelAndView.addObject("schoolId", schoolId);
        return modelAndView;
    }

    //跳转修改班级页面
    @RequestMapping("/skipclassupdate") @ResponseBody public ModelAndView skipclassupdate(
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        ModelAndView modelAndView = new ModelAndView("pages/classmanage/updateclass");

        ClassManageEntity classmanageentity1 = new ClassManageEntity();
        ClassManageEntity classmanageentity2 = new ClassManageEntity();
        ClassManageEntity classmanageentity3 = new ClassManageEntity();
        ClassManageEntity classmanageentity4 = new ClassManageEntity();
        // 获取班级名称等信息
        classmanageentity1 = classmanageservice.queryUpdateClassInfo(classmanageentity);
        // 获取班级主班信息
        classmanageentity2 = classmanageservice.queryUpdateClassMainTeacher(classmanageentity);
        // 获取班级配班信息
        classmanageentity3 = classmanageservice.queryUpdateClassWithTeacher(classmanageentity);
        // 获取班级保育员信息
        classmanageentity4 = classmanageservice.queryUpdatenurserygoverness(classmanageentity);

        // modelAndView.addObject("classId",classId);
        modelAndView.addObject("className", classmanageentity1.getClassName());
        modelAndView.addObject("classStyle", classmanageentity1.getClassStyle());
        modelAndView.addObject("classId", classmanageentity1.getClassId());
        modelAndView.addObject("schoolId", classmanageentity.getSchoolId());
        if (classmanageentity2 == null) {
            modelAndView.addObject("mainTeacherName", "");
            modelAndView.addObject("mainTeacherId", "");
        } else {
            modelAndView.addObject("mainTeacherName", classmanageentity2.getName());
            modelAndView.addObject("mainTeacherId", classmanageentity2.getUserId());
        }
        if (classmanageentity3 == null) {
            modelAndView.addObject("withTeacherName", "");
            modelAndView.addObject("withTeacherId", "");
        } else {
            modelAndView.addObject("withTeacherName", classmanageentity3.getName());
            modelAndView.addObject("withTeacherId", classmanageentity3.getUserId());
        }
        if (classmanageentity4 == null) {
            modelAndView.addObject("nurserygovernessName", "");
            modelAndView.addObject("nurserygovernessId", "");
        } else {
            modelAndView.addObject("nurserygovernessName", classmanageentity4.getName());
            modelAndView.addObject("nurserygovernessId", classmanageentity4.getUserId());
        }
        return modelAndView;
    }

    // 删除班级时查询该学校该班级下是否有学生存在
    @RequestMapping("/queryClassStudents") @ResponseBody public List<?> queryClassStudents(
        @RequestParam(value = "classId", required = false) Integer classId, @RequestParam(
        value = "organizationId", required = false) Integer organizationId) {
        UserInClassEntity userinclassentity = new UserInClassEntity();
        userinclassentity.setClassId(classId);
        userinclassentity.setOrganizationId(organizationId);
        return classmanageservice.queryclassstudents(userinclassentity);
    }

    // 删除班级时查询该学校该班级下是否有普通教师存在
    @RequestMapping("/queryClassTeachers") @ResponseBody public List<?> queryClassTeachers(
        @RequestParam(value = "classId", required = false) Integer classId, @RequestParam(
        value = "organizationId", required = false) Integer organizationId) {
        TeacherInClassEntity teacherinclassentity = new TeacherInClassEntity();
        teacherinclassentity.setClassId(classId);
        teacherinclassentity.setOrganizationId(organizationId);
        return classmanageservice.queryClassTeachers(teacherinclassentity);
    }


    // 删除班级
    @RequestMapping("/deleteClass") @ResponseBody public int deleteClass(
        @RequestParam(value = "classId", required = false) Integer classId,
        @RequestParam(value = "schoolId", required = false) Integer schoolId) {
        ClassManageEntity classmanageentity = new ClassManageEntity();
        TeacherInClassEntity teacherinclassentity = new TeacherInClassEntity();
        teacherinclassentity.setClassId(classId);
        classmanageentity.setClassId(classId);
        //删除teacherinclass中该班级下的主班，配班，保育员，老师
        classmanageservice.deleteSpecialTeacherInClass(teacherinclassentity);
        // 逻辑删除classinfo信息
        classmanageservice.updateClassDeleteFlg(classmanageentity);
        // 删除classinforelation信息
        return classmanageservice.deleteClassinforelation(classmanageentity);
    }

    // 添加班级时查询教师信息
    @RequestMapping("/queryClassTeacher") @ResponseBody public List<?> queryClassTeacher(
        @RequestParam(value = "mainTeacherId", required = false) String mainTeacherId,
        @RequestParam(value = "schoolId", required = false) Integer schoolId,
        @RequestParam(value = "nurserygovernessId", required = false) String nurserygovernessId,
        @RequestParam(value = "withTeacherId", required = false) String withTeacherId) {
        SysUserInorg sysuserinorg = new SysUserInorg();
        sysuserinorg.setOrganizationId(schoolId);
        sysuserinorg.setMainTeacherId(mainTeacherId);
        sysuserinorg.setWithTeacherId(withTeacherId);
        sysuserinorg.setNurserygovernessId(nurserygovernessId);
        return classmanageservice.queryClassTeacher(sysuserinorg);
    }

    //添加班级时班级名称查重
    @RequestMapping("/queryRepeatClassName") @ResponseBody
    public ClassManageEntity queryRepeatClassName(
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        return classmanageservice.queryRepeatClassName(classmanageentity);
    }

    //添加班级时班级代码查重
    @RequestMapping("/queryRepeatClassCode") @ResponseBody
    public ClassManageEntity queryRepeatClassCode(
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        return classmanageservice.queryRepeatClassCode(classmanageentity);
    }

    //修改时班级名称查重
    @RequestMapping("/queryUpdateRepeatClassName") @ResponseBody
    public ClassManageEntity queryUpdateRepeatClassName(
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        return classmanageservice.queryUpdateRepeatClassName(classmanageentity);
    }

    // 添加班级
    @RequestMapping("/insertClass") @ResponseBody public int insertClass(HttpServletRequest request,
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity

        ) {
        // 获取服务器时间
        classmanageentity.setCreationTime(Calendar.getInstance().getTime());
        classmanageentity.setLastupdateTime(Calendar.getInstance().getTime());
        classmanageentity.setEstablishmentDate(Calendar.getInstance().getTime());
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        Integer userId = loginUserInfo.getUserid();
        classmanageentity.setCreatedBy(userId.toString());
        classmanageentity.setLastupdatedBy(userId.toString());
        classmanageservice.insertClass(classmanageentity);
        // 获取添加class的classId
        classmanageentity.getClassId();
        // 添加classinforelation数据
        classmanageservice.insertClassInfoRelation(classmanageentity);
        // 教师任教表中添加主班老师信息
        TeacherInClassEntity teacherInClass = new TeacherInClassEntity();
        teacherInClass.setOrganizationId(classmanageentity.getSchoolId());
        teacherInClass.setClassId(classmanageentity.getClassId());
        teacherInClass.setType(1);
        teacherInClass.setUserId(classmanageentity.getMainTeacherId());
        teacherInClassService.saveTeaInClass(teacherInClass);

        teacherInClass.setType(2);
        teacherInClass.setUserId(classmanageentity.getWithTeacherId());
        teacherInClassService.saveTeaInClass(teacherInClass);

        teacherInClass.setType(3);
        teacherInClass.setUserId(classmanageentity.getNurserygovernessId());
        teacherInClassService.saveTeaInClass(teacherInClass);

        return 0;
    }

    // 修改班级时获取该班级的信息
    @RequestMapping("/queryUpdateClassInfo") @ResponseBody
    public ClassManageEntity queryUpdateClassInfo(
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        ClassManageEntity classmanageentity1 = new ClassManageEntity();
        ClassManageEntity classmanageentity2 = new ClassManageEntity();
        ClassManageEntity classmanageentity3 = new ClassManageEntity();
        ClassManageEntity classmanageentity4 = new ClassManageEntity();
        ClassManageEntity classmanageentity5 = new ClassManageEntity();
        // 获取班级名称等信息
        classmanageentity1 = classmanageservice.queryUpdateClassInfo(classmanageentity);
        classmanageentity5.setClassName(classmanageentity1.getClassName());
        classmanageentity5.setClassStyle(classmanageentity1.getClassStyle());
        classmanageentity5.setClassId(classmanageentity1.getClassId());
        // 获取班级主班信息
        classmanageentity2 = classmanageservice.queryUpdateClassMainTeacher(classmanageentity);
        classmanageentity5.setMainTeacherName(classmanageentity2.getName());
        classmanageentity5.setMainTeacherId(classmanageentity2.getUserId());
        // 获取班级配班信息
        classmanageentity3 = classmanageservice.queryUpdateClassWithTeacher(classmanageentity);
        classmanageentity5.setWithTeacherName(classmanageentity3.getName());
        classmanageentity5.setWithTeacherId(classmanageentity3.getUserId());
        // 获取班级保育员信息
        classmanageentity4 = classmanageservice.queryUpdatenurserygoverness(classmanageentity);
        classmanageentity5.setNurserygovernessName(classmanageentity4.getName());
        classmanageentity5.setNurserygovernessId(classmanageentity4.getUserId());
        return classmanageentity5;
    }


    // 修改班级时将教师任教表中被替换的教师信息删除     修改班级教师时将新选择的老师在教师任教表增加数据
    @RequestMapping("/UpdateClassMainTeacherInClass") @ResponseBody
    public int UpdateClassMainTeacherInClass(HttpServletRequest request,
        @ModelAttribute(value = "teacherinclassentity") TeacherInClassEntity teacherinclassentity,
        @RequestParam(value = "newMainTeacherId", required = false) Integer newMainTeacherId,
        @RequestParam(value = "oldMainTeacherId", required = false) Integer oldMainTeacherId) {
        ClassManageEntity classmanageentity = new ClassManageEntity();
        if (oldMainTeacherId != null) {
            teacherinclassentity.setUserId(oldMainTeacherId);
            // 修改班级时将教师任教表中被替换的教师信息删除
            classmanageservice.deleteMainteacherInClass(teacherinclassentity);
        }
        //修改班级时修改最终修改人与最终修改时间
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        classmanageentity.setLastupdatedBy(loginUserInfo.getUserid().toString());
        classmanageentity.setLastupdateTime(Calendar.getInstance().getTime());
        classmanageentity.setClassId(teacherinclassentity.getClassId());
        classmanageservice.updateClassUpdateInfo(classmanageentity);
        // 修改班级教师时将新选择的主班老师在教师任教表增加数据
        teacherinclassentity.setUserId(newMainTeacherId);
        return teacherInClassService.saveTeaInClass(teacherinclassentity);
    }

    // 修改班级时，在classinfo中修改班级名称及班级类型
    @RequestMapping("/updateClassInfo") @ResponseBody public int updateClassInfo(
        HttpServletRequest request,
        @ModelAttribute(value = "classmanageentity") ClassManageEntity classmanageentity) {
        //修改班级时修改最终修改人与最终修改时间
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        Integer lastUpdateById = loginUserInfo.getUserid();
        String lastupdatedBy = lastUpdateById.toString();
        classmanageentity.setLastupdatedBy(lastupdatedBy);
        classmanageentity.setLastupdateTime(Calendar.getInstance().getTime());
        classmanageservice.updateClassUpdateInfo(classmanageentity);
        return classmanageservice.updateClassInfo(classmanageentity);
    }

    //进入首页时查询的登陆用户信息，判断其权限进行左侧菜单显示
    @RequestMapping("/queryLoginInfo") @ResponseBody public SysUserInorg queryLoginInfo(
        HttpServletRequest request) {
        SysUserInorg sysuserinorg = new SysUserInorg();
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        Integer userId = loginUserInfo.getUserid();
        sysuserinorg.setUserId(userId);
        return classmanageservice.queryLoginInfo(sysuserinorg);
    }

    @RequestMapping(value = "/queryClassBySchoolId",method = RequestMethod.POST)
    @ResponseBody
    public List<ClassManageEntity> queryClassBySchoolId(ClassManageEntity classManage){
        return classmanageservice.queryClassBySchoolId(classManage);
    }
}
