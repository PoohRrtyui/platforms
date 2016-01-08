package com.xunyun.infanteduplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.Collect;
import com.xunyun.infanteduplatform.persistent.CollectMapper;

@Service("collectService")
public class CollectService {
	 @Autowired
	 private CollectMapper collectMapper;
	 
	 /*收藏信息查重*/
	 public Integer collectValidate(Collect collect){
		 return this.collectMapper.collectValidate(collect);
	 }
	 
	/* 交流信息收藏*/
	 public int collectSubjects(Collect collect){
		return this.collectMapper.collectSubjects(collect);
	 }
}
