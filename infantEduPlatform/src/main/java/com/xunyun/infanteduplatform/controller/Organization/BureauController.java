package com.xunyun.infanteduplatform.controller.organization;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.BureauRelation;
import com.xunyun.infanteduplatform.domain.LoginUserInfo;
import com.xunyun.infanteduplatform.domain.SysCodeMaster;
import com.xunyun.infanteduplatform.domain.SysUserInorg;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.service.BureauService;
import com.xunyun.infanteduplatform.service.ClassManageService;
import com.xunyun.infanteduplatform.service.CodeMasterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/11/30.
 * 单位管理Controller
 */
@Controller @RequestMapping("bureau") public class BureauController {
    @Autowired private BureauService bureauService;
     // 注入
    @Autowired
    private ClassManageService classmanageservice;
    
    @Autowired private CodeMasterService codeMasterService;
    /**
     * 查询单位列表树
     *
     * @param id 单位id
     * @return List<TreeEntity> 树List
     */
    @RequestMapping(value = "/queryBureauList", method = RequestMethod.POST)
    public @ResponseBody
    List<TreeEntity> queryBureauList(HttpServletRequest request,
        @RequestParam(defaultValue = "0") int id,
        @RequestParam(value="oid" , required = false) Integer oid) {
      if(null != oid && -1 == oid){
      //获取登陆用户userId
        SysUserInorg sysuserinorg = new SysUserInorg();
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        Integer userId = loginUserInfo.getUserid();
        sysuserinorg.setUserId(userId);
        //根据登陆用户userId获取登陆用户的userId
        sysuserinorg=classmanageservice.queryLoginInfo(sysuserinorg);
        id=sysuserinorg.getOrganizationId();
      }
      
       Map<String,Integer> map = new HashMap<>();
       map.put("id",id);
       map.put("oid",oid);
       return this.bureauService.queryBureauList(map);
    }

    /**
     * 查询单位基本信息
     *
     * @param bureauId 单位id
     * @return List<BureauInfo>
     */
    @RequestMapping(value = "queryBureauInfo", method = RequestMethod.POST) public @ResponseBody
    BureauInfo queryBureauInfo(@RequestParam int bureauId) {
        return this.bureauService.queryBureauInfo(bureauId);
    }

    /**
     * 添加修改单位信息
     *
     * @param bureauInfo     单位基本信息
     * @param parentBureauId 上级单位id
     * @return 保存状态
     */
    @RequestMapping(value = "saveBureauInfo", method = RequestMethod.POST) public @ResponseBody
    Integer saveBureauInfo(
        @ModelAttribute(value = "bureau") BureauInfo bureauInfo,
        @RequestParam(value = "parentBureauId", required = false) Integer parentBureauId,
        @RequestParam(value = "oldPId", required = false) Integer oldPId,
        HttpServletRequest request ) {
        int saveOrUpdate = 0;
        //取最后一个传递过来的LocationCode
        String[] lc = bureauInfo.getLocationCode().split(",");
        bureauInfo.setLocationCode(lc[lc.length-1]);
        parentBureauId = (parentBureauId == null) ? 0 : parentBureauId;
        oldPId = (oldPId == null) ? 0 : oldPId;
        HttpSession session = request.getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute("loginUserInfo");
        bureauInfo.setLastUpdatedBy(loginUserInfo.getUserid()+"");
        bureauInfo.setLastUpdateTime(Calendar.getInstance().getTime());
        bureauInfo.setDeleteFlg(0);
        if (bureauInfo.getOrganizationId() != null) {
            return this.bureauService.updateBureauInfo(bureauInfo, parentBureauId, oldPId);
        } else {
            bureauInfo.setCreatedBy(loginUserInfo.getUserid()+"");
            bureauInfo.setCreationTime(Calendar.getInstance().getTime());
            this.bureauService.saveBureauInfo(bureauInfo, parentBureauId);
        }
        return saveOrUpdate;
    }

    /**
     * 删除单位
     *
     * @param bureauId 单位Id
     * @return 删除状态
     */
    @RequestMapping(value = "deleteBureauInfo", method = RequestMethod.POST) public @ResponseBody
    Integer deleteBureauInfo(@RequestParam(value = "bureauId") Integer bureauId,
        @RequestParam(value = "parentBureauId") Integer parentBureauId) {
        return this.bureauService.deleteBureauInfo(bureauId, parentBureauId);
    }
    
   //删除时查询该单位下是否有子单位
    @RequestMapping(value = "queryChildBureauId", method = RequestMethod.POST)
    @ResponseBody
    public List<BureauRelation> queryChildBureauId(@RequestParam(value = "bureauId") Integer bureauId) {
        return this.bureauService.queryChildBureauId(bureauId);
    }

    @RequestMapping(value = "queryBureau", method = RequestMethod.POST) public @ResponseBody
    String queryBureau(@ModelAttribute(value = "bureau") BureauInfo bureauInfo) {
        Integer counts = this.bureauService.queryBureau(bureauInfo);
        String message;
        if (counts > 0 && bureauInfo.getBureauName() != null && !""
            .equals(bureauInfo.getBureauName())) {
            message = "此单位下单位名称重复!";
        } else if (counts > 0 && bureauInfo.getOrganizationCode() != null) {
            message = "单位机构代码重复!";
        } else {
            message = "true";
        }
        return message;
    }
    
  //根据所在行政区域码让该单位行政区域显示出来
     @RequestMapping(value = "queryUpdateLocationCode", method = RequestMethod.POST)
     @ResponseBody
     public BureauInfo queryUpdateLocationCode(@RequestParam(value = "locationCode") String locationCode,
         @RequestParam(value = "locationName") String locationName) {
       //只会if else 谁看谁崩溃
       BureauInfo bureauinfo = new BureauInfo();
       SysCodeMaster syscodemaster=new SysCodeMaster();
       syscodemaster=codeMasterService.queryUpdateLocationCode(locationCode);
       String locationCodeOne="";
       String locationNameOne="";
       String locationCodeTwo="";
       String locationNameTwo="";
       //String locationCodeThree="";
       //String locationNameThree="";
       
       locationCodeOne=syscodemaster.getMiddleCategoryCd();
       locationNameOne=syscodemaster.getMiddleCategoryName();
      //   locationCodeOne="Province";
      // locationNameOne="省份";
       
       if("Province".equals(locationCodeOne)) {
         bureauinfo.setLocationProvinceCode(locationCode);
         bureauinfo.setLocationProvinceName(locationName);
       }else {
         syscodemaster=codeMasterService.queryUpdateLocationCode(locationCodeOne);
        
           locationCodeTwo=syscodemaster.getMiddleCategoryCd();
           locationNameTwo=syscodemaster.getMiddleCategoryName();
         
      //     locationCodeTwo="Province";
      //   locationNameTwo="省份";
         
         if("Province".equals(locationCodeTwo)){
           bureauinfo.setLocationProvinceCode(locationCodeOne);
           bureauinfo.setLocationProvinceName(locationNameOne);
           bureauinfo.setLocationCityCode(locationCode);
           bureauinfo.setLocationCityName(locationName);
           }else {
             //syscodemaster=codeMasterService.queryUpdateLocationCode(locationCodeTwo);
             bureauinfo.setLocationAreaCode(locationCode);
             bureauinfo.setLocationAreaName(locationName);
             bureauinfo.setLocationCityCode(locationCodeOne);
             bureauinfo.setLocationCityName(locationNameOne);
             bureauinfo.setLocationProvinceCode(locationCodeTwo);
             bureauinfo.setLocationProvinceName(locationNameTwo);
           }
         }
        
       return bureauinfo;
     }
    
}
