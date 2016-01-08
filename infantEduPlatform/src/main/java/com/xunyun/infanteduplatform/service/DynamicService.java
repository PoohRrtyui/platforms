package com.xunyun.infanteduplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.Dynamic;
import com.xunyun.infanteduplatform.persistent.DynamicMapper;

@Service("dynamicService")
public class DynamicService {
	 @Autowired
	 private DynamicMapper dynamicMapper;
	 
	/*通过交流id获取点赞用户的头像*/ 
	 public List<Dynamic> queryPhotoList(Dynamic dynamic){
		return this.dynamicMapper.queryPhotoList(dynamic);
	 }
	 
	 /*点赞信息查重*/
	 public int queryDynamic(Dynamic dynamic) {
		 return dynamicMapper.queryDynamic(dynamic);
	 }
	 
	 /*信息点赞*/
	 public int insertDynamic(Dynamic dynamic) {
		 return dynamicMapper.insertDynamic(dynamic);
	}
}
