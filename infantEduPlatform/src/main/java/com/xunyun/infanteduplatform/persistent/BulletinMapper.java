package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.SelectDynamic;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BulletinMapper {
  // 公告列表获取(后台管理)
  List<Bulletin> queryBulletinList(Bulletin bulletin);

  // 公告列表获取
  List<Bulletin> selectBulletinList(Bulletin bulletin);
  
  // 公告信息批量删除
  int deleteList(Map<String, Object> map);

  // 公告信息查询
  Bulletin queryByBulletinId(Integer bulletinId);

  // 公告信息保存
  Integer addBulletinInfo(Bulletin bulletin);

  // 班级动态列表
  List<SelectDynamic> queryBulletinPage(Bulletin bulletin);

  int insertMessage(Bulletin bulletin);

  SelectDynamic queryDetail(Bulletin bulletin);

}
