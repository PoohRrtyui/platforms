package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.MorningCheck;

@Repository
public interface MorningCheckMapper {
  
  /**
   *   app查询班级学生列表
   */
  List<MorningCheck> queryStudentInClasssList(MorningCheck morningcheck);
  
  /**
   *   后台查询班级学生列表
   */
  List<MorningCheck> queryStudentInClasssListInfo(MorningCheck morningcheck);
  
  Integer deleteMorningCheck(int checkId);
  
  /**
   *   取当前时间和userid查看此当前学生是否晨检过
   */
  Integer queryMorningCheckNumber(MorningCheck morningcheck);
  
  /**
   * 添加学生当天晨检情况
   */
  Integer insertCheckMoring (MorningCheck morningcheck);
}
