package com.xunyun.infanteduplatform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.SysUserInfo;
import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Growth;
import com.xunyun.infanteduplatform.persistent.GrowthMapper;

@Service
@Transactional
public class GrowthService {
  
  @Resource
  private GrowthMapper growthMapper;

  public List<SysUserInfo> queryGrowthPage(Bulletin bulletin) {
    
    return growthMapper.queryGrowthPage(bulletin);
  }

  public List<Growth> queryGrowthInfo(Map<String, Object> para) {
    
    return growthMapper.queryGrowthInfo(para);
  }

  public Integer queryParentUserId(Integer userId) {
    
    return growthMapper.queryParentUserId(userId);
  }

}
