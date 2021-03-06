package com.xunyun.infanteduplatform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.persistent.ParentingMapper;

@Service("parenting")
@Transactional
public class ParentingService {

  @Resource
  private ParentingMapper parentingMapper;
  
  public List<String> findByFinished(Integer bulletinId) {
    
    return parentingMapper.findByFinished(bulletinId);
  }

  public List<Bulletin> findByUnfinished(Map<String, Object> names) {
    
    return parentingMapper.findByUnfinished(names);
  }

}
