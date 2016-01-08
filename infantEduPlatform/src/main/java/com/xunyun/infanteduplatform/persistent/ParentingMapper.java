package com.xunyun.infanteduplatform.persistent;

import java.util.List;
import java.util.Map;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Image;

public interface ParentingMapper {

  List<String> findByFinished(Integer bulletinId);

  List<Bulletin> findByUnfinished(Map<String, Object> names);

}
