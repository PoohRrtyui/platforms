package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.domain.interaction.ImageRelation;

@Repository
public interface ImageMapper {
  // 图片信息查询
  List<Image> queryImageList(Integer bulletinId);

  // 图片信息保存
  Integer addImageList(Image image);

  // 图片关联表保存
  Integer addImageRelation(ImageRelation imageRelation);

}
