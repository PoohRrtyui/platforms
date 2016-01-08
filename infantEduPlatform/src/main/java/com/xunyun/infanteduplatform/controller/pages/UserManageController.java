package com.xunyun.infanteduplatform.controller.pages;

import com.xunyun.infanteduplatform.domain.*;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.CodeMasterService;
import com.xunyun.infanteduplatform.service.DepartmentRelationService;
import com.xunyun.infanteduplatform.service.UserService;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.MD5Util;
import org.apache.poi.hssf.record.chart.NumberFormatIndexRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;


/**
 * 类说明:用户管理处理方法类<br/>
 * 创建人:羊鑫<br/>
 * 创建日期:2015年11月30日<br/>
 */
@Controller @RequestMapping("user") public class UserManageController {

    @Autowired private DepartmentRelationService departmentRelationService;
    @Autowired private UserService userService;
    @Autowired private CodeMasterService codeMasterService;
    @Autowired private BulletinService bulletinService;

    /**
     * 访问用户列表页面 方法描述:<br>
     * 创建日期:2015年11月30日<br>
     */
    @RequestMapping("/index") public ModelAndView user_index(String appCode,
        @RequestParam(value = "schoolId", required = false) Integer schoolId, String type,
        HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("pages/userManage/index");
        if (schoolId == null) {
            HttpSession session = request.getSession();
            LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
            schoolId = loginUserInfo.getOrganizationid();
            type = loginUserInfo.getOrganizationtype();
        }
        mav.addObject("schoolId", schoolId);
        mav.addObject("type", type);
        return mav;
    }

    /**
     * 用户机构树加载 方法描述:<br>
     * 创建日期:2015年11月30日<br>
     */
    // 查询一级栏目
    @ResponseBody @RequestMapping(value = "/select") public List<TreeEntity> queryChannel(
        Integer schoolId, @RequestParam(defaultValue = "0") Integer id) {
        // 获取该学校ID下部门
        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("pId", id);
        dataMap.put("sId", schoolId);
        List<SysDepartmentInfo> departmentInfos =
            departmentRelationService.getClassAndDepList(dataMap);
        List<TreeEntity> treeEntities = new ArrayList<TreeEntity>();
        for (SysDepartmentInfo departmentInfo : departmentInfos) {
            TreeEntity entity;
            entity = new TreeEntity();
            entity.setId(departmentInfo.getDepartmentId());
            entity.setName(departmentInfo.getDepartmentName());
            entity.setpId(departmentInfo.getParentDepartmentId());
            entity.setIsParent(departmentInfo.getIsChild() == 1);
            entity.setIsClass(false);
            treeEntities.add(entity);
        }
        // 获取该学校ID下的班级
        if (id == 0) {
            List<ClassManageEntity> classInfos = departmentRelationService.getClassList(schoolId);
            for (ClassManageEntity classInfo : classInfos) {
                TreeEntity entity;
                entity = new TreeEntity();
                entity.setId(classInfo.getClassId());
                entity.setName(classInfo.getClassName());
                // 设置为0是为了防止，查询班级时传的schoolId恰好是部门Id,就会出现班级成为部门的子节点
                entity.setpId(0);
                entity.setIsParent(false);
                entity.setIsClass(true);
                treeEntities.add(entity);
            }
        }
        return treeEntities;
    }

    /**
     * 用户列表页面 方法描述:<br>
     * 创建日期:2015年12月2日<br>
     */
    @ResponseBody @RequestMapping(value = "/list") public Map<Object, Object> user_list(
        @RequestParam(value = "keyValue", required = false) String keyValue, @RequestParam(
        value = "draw", required = false) Integer draw, @RequestParam(value = "start",
        required = false) Integer start,
        @RequestParam(value = "length", required = false) Integer length, Integer schoolId,
        Integer treeId, Boolean isClass) {
        // 开始条数
        int startNumber = start + 1;
        // 结束条数
        int endNumber = start + length;
        // 总数目
        int countNumber = 0;

        // 声明对象
        // SysUserInorg item = new SysUserInorg();
        // // 查询条件
        // item.setKeyValue(keyValue);
        // // 开始条数
        // item.setStartNumber(startNumber);
        // // 结束条数
        // item.setEndNumber(endNumber);

        Map<Object, Object> userList = new HashMap<Object, Object>();
        Map<Object, Object> dataMap = new HashMap<>();
        dataMap.put("startNumber", startNumber);
        dataMap.put("keyValue", keyValue);
        dataMap.put("endNumber", endNumber);
        dataMap.put("schoolId", schoolId);
        dataMap.put("treeId", treeId);
        dataMap.put("isClass", isClass);
        List<SysUserInorg> users = userService.queryUserBySId(dataMap);
        userList.put("data", users);
    /*
     * if(treeId==null){ users = userService.queryUserBySId(schoolId); // userList.put("data",
     * users); }else{ if(isClass=true){ List<SysUserInfo> techs =
     * userService.queryUserByCId(treeId); List<SysUserInfo> stus =
     * userService.queryStuByCId(treeId); techs.addAll(stus); users = techs; userList.put("data",
     * users); }else{ users = userService.queryUserByDId(treeId); userList.put("data", users); } }
     */
        // 数据不为空，取总数
        if (users != null && users.size() > 0) {
            countNumber = users.get(0).getCountNumber();
        }
        // 总条数
        userList.put("recordsTotal", countNumber);
        // 过滤条数
        userList.put("recordsFiltered", countNumber);
        // 当前页数
        userList.put("draw", draw);
        return userList;
    }

    /**
     * 进入用户编辑页面 方法描述:<br>
     * 创建日期:2015年12月3日<br>
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST) @ResponseBody
    public ModelAndView user_add(Integer userId, Integer schoolId, Integer treeId, Boolean isClass,
        String type, Integer userType) {
        ModelAndView mav = new ModelAndView("pages/userManage/edit");
        // List<SysCodeMaster> opitons = codeMasterService.findOrg("机构身份");
        // mav.addObject("opitons", opitons);
        SysUserInfo users = new SysUserInfo();
        String pwd = null;
        // userId = users.getUserid();
        if (userId != null && userId != ' ') {
            users = userService.findUserById(userId);
            SysUserLogin logins = userService.findPwdById(userId);
            pwd = logins.getPassword();
        }
        mav.addObject("schoolId", schoolId);
        mav.addObject("treeId", treeId);
        mav.addObject("isClass", isClass);
        mav.addObject("type", type);
        mav.addObject("userType", userType);
        mav.addObject("users", users);
        mav.addObject("passWord", pwd);
        mav.addObject("userId", userId);
        return mav;
    }

    /**
     * 用户逻辑删除 方法描述:<br>
     * 创建日期:2015年12月3日<br>
     */
    @RequestMapping(value = "/delUser") @ResponseBody public Integer user_del(Integer userId,
        Integer schoolId, Integer treeId, Boolean isClass, Integer userType) {
        Integer status;
        status = userService.delUserById(userId, schoolId, treeId, isClass, userType);
        return status;
    }

    /**
     * 用户保存 方法描述:<br>
     * 创建日期:2015年12月7日<br>
     */
    @ResponseBody @RequestMapping(value = "/saveUserInfo") public Integer save_user(
        HttpServletRequest request, SysUserInfo userinfo, Integer schoolId, Integer treeId,
        Boolean isClass, Integer userType, String type, String pwd, Integer userId) {
        Integer status;

        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        String userInfos = loginUserInfo.getName();
        // String lei = "chenlei";
        String password = MD5Util.MD5(pwd);

        SysUserLogin loginUser = new SysUserLogin();

        SysUserInorg userInorg = new SysUserInorg();

        TeacherInClassEntity teach = new TeacherInClassEntity();

        UserInClassEntity stu = new UserInClassEntity();

        SysUserInDepartment dept = new SysUserInDepartment();

        loginUser.setEmail(userinfo.getEmail());
        loginUser.setMobile(userinfo.getMobile());
        userType = 0;
        if (userId != null) {
            userinfo.setUserid(userId);
            loginUser.setId(userId);
            status = userService.updateUserInfo(userinfo, loginUser);

        } else {
            userinfo.setCreationtime(Calendar.getInstance().getTime());
            userinfo.setIdtype(1);
            userinfo.setCreatedby(userInfos);
            userinfo.setDeleteflg(0);

            loginUser.setName(userinfo.getUsername());
            loginUser.setPassword(password);

            userInorg.setCreatedby(userInfos);
            userInorg.setStartTime(Calendar.getInstance().getTime());
            userInorg.setOrganizationType(type);
            userInorg.setOrganizationId(schoolId);
            userInorg.setIsAdmin(0);
            userInorg.setUserType(0);
            userInorg.setIsAuthentication(1);

            teach.setOrganizationId(schoolId);
            teach.setType(0);

            stu.setOrganizationId(schoolId);

            dept.setOrganizationId(schoolId);
            status = userService
                .saveUserInfo(userinfo, loginUser, userInorg, teach, stu, dept, treeId, isClass,
                    userType);
        }
        return status;
    }

    /**
     * 设置用户管理员 方法描述:<br>
     * 创建日期:2015年12月17日<br>
     */
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST) @ResponseBody
    public Integer set_admin(String ids, Integer schoolId) {
        String[] array = ids.split(",");
        List<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        Integer userid = Integer.valueOf(array[0]);
        map.put("idList", list);
        Integer count = userService.updateAdmin(map, userid, schoolId);
        return count;
    }

    /**
     * 设置用户管理员 方法描述:<br>
     * 创建日期:2015年12月17日<br>
     */
    @RequestMapping(value = "/closeAdmin", method = RequestMethod.POST) @ResponseBody
    public Integer close_admin(String ids) {
        String[] array = ids.split(",");
        List<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        map.put("idList", list);
        Integer count = userService.closeAdmin(map);
        return count;
    }

    /**
     * 用户认证方法描述:<br>
     * 创建日期:2015年12月17日<br>
     */
    @RequestMapping(value = "/auditUser", method = RequestMethod.POST) @ResponseBody
    public Integer audit_user(String ids) {
        String[] array = ids.split(",");
        List<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        map.put("idList", list);
        Integer count = userService.auditUser(map);
        return count;
    }

    /**
     * 用户修改单位页面加载 方法描述:<br>
     * 创建日期:2015年12月17日<br>
     */
    @RequestMapping(value = "/editUnit") public ModelAndView edit_unit(String ids, Integer schoolId,
        Integer treeId, Boolean isClass) {
        ModelAndView mav = new ModelAndView("pages/userManage/editUnit");
        mav.addObject("ids", ids);
        mav.addObject("schoolId", schoolId);
        return mav;
    }

    /**
     * 修改用户所属单位 方法描述:<br>
     * 创建日期:2015年12月21日<br>
     */
    @ResponseBody @RequestMapping(value = "/saveUnit", method = RequestMethod.POST)
    public Integer save_Unit(String ids, Integer treeId, Integer tId, Boolean isClas) {
        String[] array = ids.split(",");
        List<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        map.put("idList", list);
        map.put("tId", tId);
        Integer count = userService.saveUnitById(map, isClas);
        return count;
    }

    /**
     * 登录用户详细信息页面:<br>
     * 创建日期:2015年12月17日<br>
     *
     * @throws IOException
     */
    @RequestMapping(value = "/detail") public ModelAndView user_detail(HttpServletRequest request)
        throws IOException {
        ModelAndView mav = new ModelAndView("pages/userManage/userDetail");
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        Integer userId = loginUserInfo.getUserid();
        String photoUrl = loginUserInfo.getPhotourl();
        String image = InteractionUtils.getBlob(request, photoUrl);
        mav.addObject("userinfo", loginUserInfo);
        mav.addObject("image", image);
        return mav;
    }

    @ResponseBody @RequestMapping(value = "/saveUserDetail", method = RequestMethod.POST)
    public Integer save_detail(HttpServletRequest request, SysUserInfo userinfo, Integer userId) {
        Integer status;
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        String names = loginUserInfo.getName();
        userinfo.setLastupdatedby(names);
        userinfo.setLastupdatetime(Calendar.getInstance().getTime());

        SysUserLogin loginUser = new SysUserLogin();
        loginUser.setEmail(userinfo.getEmail());
        loginUser.setMobile(userinfo.getMobile());
        loginUser.setId(userinfo.getUserid());
        status = this.userService.saveUserDetail(userinfo, loginUser, userId);
        return status;
    }

    /**
     * 用户导出 方法描述:<br>
     * 创建日期:2015年12月29日<br>
     *
     * @throws IOException
     */
    @RequestMapping(value = "/userExport") public void user_Export(String ids,
        @RequestParam(defaultValue = "0") Integer treeId, Integer schoolId,
        @RequestParam(defaultValue = " ") Boolean isClass, String type,
        HttpServletResponse response, HttpServletRequest request) throws IOException {
        String[] array = ids.split(",");
        List<String> list = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        map.put("idList", list);
        List<LoginUserInfo> users = userService.queryByIds(map);
        // 创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览

        HSSFWorkbook wk = new HSSFWorkbook();
        ;
        HSSFSheet sheet = wk.createSheet("用户表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wk.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 构造一个数组设置第一行之后的单元格
        HSSFCell cell[] = new HSSFCell[10];
        for (int i = 0; i < 10; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("用户名");
        cell[1].setCellValue("昵称");
        cell[2].setCellValue("真实姓名");
        cell[3].setCellValue("用户身份");
        cell[4].setCellValue("性别");
        cell[5].setCellValue("手机");
        cell[6].setCellValue("邮箱");
        cell[7].setCellValue("管理员");
        cell[8].setCellValue("所属机构");
        cell[9].setCellValue("所属班级/部门");
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 1);
            LoginUserInfo userinfo = users.get(i);
            Integer userType = userinfo.getUsertype();
            long gender = userinfo.getGendercode();
            Integer isAdmin = userinfo.getIsadmin();
            String userTypeStr;
            String genderStr;
            String adminStr;
            row.createCell(0).setCellValue(userinfo.getUsername());
            row.createCell(1).setCellValue(userinfo.getNickname());
            row.createCell(2).setCellValue(userinfo.getName());
            //根据查询到数据返回excel表格中显示详细的信息
            if (userType == 1) {
                userTypeStr = "单位/教工";
            } else if (userType == 4) {
                userTypeStr = "教师";
            } else if (userType == 3) {
                userTypeStr = "学生";
            } else if (userType == 16) {
                userTypeStr = "家长";
            } else if (userType == 0) {
                userTypeStr = " ";
            } else {
                userTypeStr = "其他";
            }

            if (gender == 1) {
                genderStr = "男";
            } else if (gender == 2) {
                genderStr = "女";
            } else {
                genderStr = "保密";
            }

            if (isAdmin == 1) {
                adminStr = "管理员";
            } else if (isAdmin == 2) {
                adminStr = "默认管理员";
            } else {
                adminStr = "非管理员";
            }
            row.createCell(3).setCellValue(userTypeStr);
            row.createCell(4).setCellValue(genderStr);
            row.createCell(5).setCellValue(userinfo.getMobile());
            row.createCell(6).setCellValue(userinfo.getEmail());
            row.createCell(7).setCellValue(adminStr);
            //通过当前所选择机构树下保存机构信息
            String orginName = " ";
            String className = " ";
            if (type.equals("S")) {
                orginName = userService.queryNameBySchoolId(schoolId);
                if (treeId != 0) {
                    if (isClass) {
                        className = userService.queryNameByClassId(treeId);
                    } else {
                        className = userService.querydepNameByClassId(treeId);
                    }
                }
            } else {
                orginName = userService.querybureaNameById(schoolId);
            }
            row.createCell(8).setCellValue(orginName);
            row.createCell(9).setCellValue(className);
        }
        // 获得输出流，该输出流的输出介质是客户端浏览器
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=users.xls");
        OutputStream ouputStream = response.getOutputStream();
        wk.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    // 用户导入
    @RequestMapping(value = "/userImport", method = RequestMethod.POST) @ResponseBody
    public void user_import(HttpServletRequest request, HttpServletResponse response,
        Integer schoolId, Integer tId, Boolean isClas, String type,
        @RequestParam("excelFile") MultipartFile file) throws IOException {
        //    String excelFilePath = "C:\\Users\\Administrator\\Desktop\\users.xls";
        //    FileInputStream is = new FileInputStream(new File(excelFilePath));
        InputStream excel = file.getInputStream(); //获取form的excel文件
        //    InputStream myxls = formFile.getInputStream();

        HSSFWorkbook wb = new HSSFWorkbook(excel);
        int sheetNum = wb.getNumberOfSheets();

        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        String userInfos = loginUserInfo.getName();
        // String lei = "chenlei";
        String password = MD5Util.MD5("123456");

        SysUserInfo userinfo = new SysUserInfo();

        SysUserLogin loginUser = new SysUserLogin();

        SysUserInorg userInorg = new SysUserInorg();

        TeacherInClassEntity teach = new TeacherInClassEntity();

        UserInClassEntity stu = new UserInClassEntity();

        SysUserInDepartment dept = new SysUserInDepartment();

        for (int i = 0; i < sheetNum; i++) {
            HSSFSheet childSheet = wb.getSheetAt(i);
            int rowNum = childSheet.getLastRowNum(); // 行数-1
            // j=1 以第二行开始遍历 第一行为标题栏
            for (int j = 1; j <= rowNum; j++) {
                HSSFRow row = childSheet.getRow(j);
                String userName = getStringCellValue(row.getCell(0));
                String nickName = getStringCellValue(row.getCell(1));
                String name = getStringCellValue(row.getCell(2));
                String userTypeStr = getStringCellValue(row.getCell(3));
                String genderStr = getStringCellValue(row.getCell(4));
                //poi读取长度较长的解决方法
                //        row.getCell(5).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
//                double cellValue = row.getCell(5).getNumericCellValue();
//                String mobile = new DecimalFormat("#").format(cellValue);
                        String mobile = NumberToTextConverter.toText(row.getCell(5).getNumericCellValue());
                //        DecimalFormat df = new DecimalFormat("#");
                //        String mobile = df.format(getStringCellValue(row.getCell(5)));
                String email = " ";
                if (row.getCell(6) != null) {
                    email = getStringCellValue(row.getCell(6));
                } else {
                }
                String adminStr = getStringCellValue(row.getCell(7));
                Integer userType;
                Integer gender;
                Integer isAdmin;
                if (userTypeStr.equals("单位/教工")) {
                    userType = 1;
                } else if (userTypeStr.equals("教师")) {
                    userType = 4;
                } else if (userTypeStr.equals("学生")) {
                    userType = 3;
                } else if (userTypeStr.equals("家长")) {
                    userType = 16;
                } else if (userTypeStr.equals(" ")) {
                    userType = 0;
                } else {
                    userType = 32;
                }
                if (genderStr.equals("男")) {
                    gender = 1;
                } else if (genderStr.equals("女")) {
                    gender = 2;
                } else {
                    gender = 0;
                }
                if (adminStr.equals("管理员")) {
                    isAdmin = 1;
                } else if (adminStr.equals("默认管理员")) {
                    isAdmin = 2;
                } else {
                    isAdmin = 0;
                }
                //        register.userManageValidate();
                loginUser.setName(userName);
                loginUser.setPassword(password);
                loginUser.setEmail(email);
                loginUser.setMobile(mobile);
                userInorg.setIsAdmin(isAdmin);
                userInorg.setUserType(userType);
                userInorg.setOrganizationId(schoolId);
                userInorg.setOrganizationType("S");
                userInorg.setIsAuthentication(1);
                userInorg.setStartTime(Calendar.getInstance().getTime());
                teach.setOrganizationId(schoolId);
                teach.setType(0);
                stu.setOrganizationId(schoolId);
                dept.setOrganizationId(schoolId);
                userinfo.setCreationtime(Calendar.getInstance().getTime());
                userinfo.setCreatedby(userInfos);
                userinfo.setDeleteflg(0);
                userinfo.setEmail(email);
                userinfo.setGendercode(gender);
                userinfo.setMobile(mobile);
                userinfo.setNAME(name);
                userinfo.setNickname(nickName);
                userinfo.setUsername(userName);
                userinfo.setIdno(" ");
                userinfo.setIdtype(' ');
                userService
                    .saveUserInfo(userinfo, loginUser, userInorg, teach, stu, dept, tId, isClas,
                        userType);
                // 重定向刷新

            }

        }
        response.sendRedirect("index?type=" + type + "&schoolId=" + schoolId);
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
}
