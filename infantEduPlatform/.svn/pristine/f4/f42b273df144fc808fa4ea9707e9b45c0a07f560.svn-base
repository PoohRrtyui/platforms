package com.xunyun.infanteduplatform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.interaction.Directory;
import com.xunyun.infanteduplatform.persistent.DirectoryMapper;

@Service("directory")
@Transactional
public class DirectoryService {
  @Resource
  private DirectoryMapper directoryMapper;
  
  //联系人列表获取
  public List<Directory> queryDirectoryList(Directory directory){
    return this.directoryMapper.queryDirectoryList(directory);
    
  }
}
