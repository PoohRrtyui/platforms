package com.xunyun.infanteduplatform.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.interaction.Image;
import com.xunyun.infanteduplatform.domain.interaction.ImageRelation;
import com.xunyun.infanteduplatform.persistent.ImageMapper;

@Service("image")
@Transactional
public class ImageService {
  @Resource
  private ImageMapper imageMapper;

  // 图片信息查询
  public List<Image> queryImageList(Integer bulletinId) {
    return this.imageMapper.queryImageList(bulletinId);
  }

  // 图片信息保存
  public Integer addImageList(Image image) {
    return this.imageMapper.addImageList(image);
  }

  // 图片关联表保存
  public Integer addImageRelation(ImageRelation imageRelation) {
    return this.imageMapper.addImageRelation(imageRelation);
  }

}
