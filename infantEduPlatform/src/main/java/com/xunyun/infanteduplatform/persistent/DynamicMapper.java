package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Dynamic;

@Repository
public interface DynamicMapper {

  // 点赞者头像列表
  List<Dynamic> queryPhotoList(Dynamic dynamic);
  //查询是否已点赞
  int queryDynamic(Dynamic dynamic);
  //插入点赞信息
  int insertDynamic(Dynamic dynamic);

}
