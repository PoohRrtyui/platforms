package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Replay;

@Repository
public interface ReplayMapper {

  // 回复列表
  List<Replay> queryReplayList(Replay replay);

  int insertReplay(Replay replay);

}
