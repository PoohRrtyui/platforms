package com.xunyun.infanteduplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.SysCodeMaster;
import com.xunyun.infanteduplatform.persistent.CodeMasterMapper;

/**
 * 数据字典
 **/
@Service("codeMasterService")
public class CodeMasterService{
  
  @Autowired
  private CodeMasterMapper codeMasterMapper;

   /*数据字典查询数据*/ 
  public List<SysCodeMaster> queryCodeMasterInfoPage(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.queryCodeMasterInfo(sysCodeMaster);
  }
  
  /*数据字典添加数据*/
  public Integer saveCodeMaster(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.saveCodeMasterInfo(sysCodeMaster);
  }
  
  /*数据字典添加功能查重验证*/
  public SysCodeMaster querySaveRepeat(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.querySaveRepeat(sysCodeMaster);
  }
  
  /*数据字典修改功能*/
  public Integer updateCodeMaster(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.updateCodeMasterInfo(sysCodeMaster);
  }
  
  /*数据字典逻辑删除*/
  public Integer deleteCodeMaster(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.deleteCodeMaster(sysCodeMaster);
  }

  /*通过大分类编号查找小分类编号和名称*/
  public List<SysCodeMaster> findMessage(String largeCategoryCd){
	    return this.codeMasterMapper.findMessage(largeCategoryCd);
  }
  
  public List<SysCodeMaster> findOrg(String string) {
    return this.codeMasterMapper.findOrg(string);
  }

  public List<SysCodeMaster> queryCodeMasterList(SysCodeMaster sysCodeMaster){
    return this.codeMasterMapper.queryCodeMasterList(sysCodeMaster);
  }
  
  //查重
  public Integer queryCoderMasterRepeat(SysCodeMaster sysCodeMaster) {
    return this.codeMasterMapper.querycodermasterrepeat(sysCodeMaster);
  }
  
//根据所在行政区域码让该单位行政区域显示出来
  public SysCodeMaster queryUpdateLocationCode(String locationCode) {
    return this.codeMasterMapper.queryUpdateLocationCode(locationCode);
  }
}
