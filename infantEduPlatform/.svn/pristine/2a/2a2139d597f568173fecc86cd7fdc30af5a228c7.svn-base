package com.xunyun.infanteduplatform.controller.Organization;

import com.xunyun.infanteduplatform.domain.SchoolInfo;
import com.xunyun.infanteduplatform.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 *学校管理Controller
 */
@Controller
@RequestMapping("school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @RequestMapping(value="querySchoolByBureauId",method = RequestMethod.POST)
    public @ResponseBody
    List<SchoolInfo> queryBureauInfo(@RequestParam int bureauId){
        return this.schoolService.querySchoolByBureauId(bureauId);
    }
}
