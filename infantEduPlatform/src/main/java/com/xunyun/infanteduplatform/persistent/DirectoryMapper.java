package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Directory;

@Repository
public interface DirectoryMapper {
  // 联系人列表获取
  List<Directory> queryDirectoryList(Directory directory);

}
