package com.xunyun.infanteduplatform.controller.Organization;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.service.BureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 * 单位管理Controller
 */
@Controller
@RequestMapping("bureau")
public class BureauController {
    @Autowired
    private BureauService bureauService;

    /**
     * 查询单位列表树
     * @param id 单位id
     * @return List<TreeEntity> 树List
     */
    @RequestMapping(value = "/queryBureauList", method = RequestMethod.POST)
    public @ResponseBody
    List<TreeEntity> queryBureauList(@RequestParam(defaultValue="0") int id){

        return this.bureauService.queryBureauList(id);
    }

    /**
     * 查询单位基本信息
     * @param bureauId 单位id
     * @return List<BureauInfo>
     */
    @RequestMapping(value="queryBureauInfo",method = RequestMethod.POST)
    public @ResponseBody
    List<BureauInfo> queryBureauInfo(@RequestParam int bureauId){
        return this.bureauService.queryBureauInfo(bureauId);
    }

    /**
     * 添加修改单位信息
     * @param bureauInfo 单位基本信息
     * @param parentBureauId 上级单位id
     * @return 保存状态
     */
    @RequestMapping(value="saveBureauInfo",method = RequestMethod.POST)
    public @ResponseBody
    Integer saveBureauInfo(@ModelAttribute(value="bureau")BureauInfo bureauInfo,
        @RequestParam(value="parentBureauId")Integer parentBureauId){
        int saveOrUpdate ;
        String c = "Pooh";
        bureauInfo.setCreatedBy(c);
        bureauInfo.setLastUpdatedBy(c);
        bureauInfo.setCreationTime(Calendar.getInstance().getTime());
        bureauInfo.setLastUpdateTime(Calendar.getInstance().getTime());
        if(bureauInfo.getBureauId()!=null){
            saveOrUpdate = this.bureauService.updateBureauInfo(bureauInfo,parentBureauId);
        }else{

            saveOrUpdate=this.bureauService.saveBureauInfo(bureauInfo,parentBureauId);
        }
        return saveOrUpdate;
    }

    @RequestMapping(value="deleteBureauInfo",method = RequestMethod.POST)
    public @ResponseBody
    Integer deleteBureauInfo(@RequestParam(value="bureauId") Integer bureauId){
        return this.bureauService.deleteBureauInfo(bureauId);
    }
}
