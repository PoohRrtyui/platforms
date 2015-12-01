package com.xunyun.infanteduplatform.controller.Organization;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.service.BureauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value="queryBureauInfo",method = RequestMethod.POST)
    public @ResponseBody
    List<BureauInfo> queryBureauInfo(@RequestParam int bureauId){
        return this.bureauService.queryBureauInfo(bureauId);
    }
}
