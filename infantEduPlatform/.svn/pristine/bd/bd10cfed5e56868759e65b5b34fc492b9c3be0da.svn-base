package com.xunyun.infanteduplatform.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.interaction.MorningCheck;
import com.xunyun.infanteduplatform.persistent.MorningCheckMapper;


@Service("morningcheckservice")
@Transactional
public class MorningCheckService {
  
  @Resource
  private MorningCheckMapper morningcheckmapper;
 
  /**
   *   app查询班级学生列表
   */
  public List<MorningCheck> queryStudentInClasssList(MorningCheck morningcheck) throws Exception {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    List<MorningCheck> dataList = morningcheckmapper.queryStudentInClasssList(morningcheck);

    for (int i = 0; i < dataList.size(); i++) {
      if(dataList.get(i).getCheckTime()!=null) {
        dataList.get(i).setStrCheckTime(df.format(dataList.get(i).getCheckTime()));
      }
    }
    return dataList;
  }
  
  /**
   *   后台管理查询班级学生列表
   */
  public List<MorningCheck> queryStudentInClasssListInfo(MorningCheck morningcheck) throws Exception {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    List<MorningCheck> dataList = morningcheckmapper.queryStudentInClasssListInfo(morningcheck);

    for (int i = 0; i < dataList.size(); i++) {
        dataList.get(i).setStrCheckTime(df.format(dataList.get(i).getCheckTime()));
        String strState="";
        if(dataList.get(i).getState()==0){
          strState="异常";
        }else{
          strState="正常";
        }
        dataList.get(i).setStrState(strState);
    }
    return dataList;
  }
  
  //删除晨检表数据
  public Integer deleteMorningCheck(int checkId) {
    return morningcheckmapper.deleteMorningCheck(checkId);
  }

  /**
   *   取当前时间和userid查看此当前学生是否晨检过
  */
  public Integer queryMorningCheckNumber(MorningCheck morningcheck
      ) throws Exception {
   
   return this.morningcheckmapper.queryMorningCheckNumber(morningcheck);
  }

  /**
   * 添加学生当天晨检情况
   * @return 
   */
  
  public Integer insertCheckMoring (MorningCheck morningcheck)throws Exception {
     return this.morningcheckmapper.insertCheckMoring(morningcheck);
  }
  
}
