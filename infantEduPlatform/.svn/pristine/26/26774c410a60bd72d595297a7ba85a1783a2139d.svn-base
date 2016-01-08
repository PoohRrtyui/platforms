package com.xunyun.infanteduplatform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.domain.interaction.Dynamic;
import com.xunyun.infanteduplatform.domain.interaction.Replay;
import com.xunyun.infanteduplatform.domain.interaction.SelectDynamic;
import com.xunyun.infanteduplatform.persistent.BulletinMapper;
import com.xunyun.infanteduplatform.persistent.DynamicMapper;
import com.xunyun.infanteduplatform.persistent.ReplayMapper;

@Service("bulletin")
@Transactional
public class BulletinService {

  @Resource
  private BulletinMapper bulletinMapper;

  @Resource
  private ReplayMapper replayMapper;

  @Resource
  private DynamicMapper dynamicMapper;

  // 查询班级动态列表
  public List<SelectDynamic> queryBulletinPage(Bulletin bulletin) {
    return bulletinMapper.queryBulletinPage(bulletin);
  }

  // 查询点赞头像列表
  public List<Dynamic> queryPhotoList(Dynamic dynamic) {
    return dynamicMapper.queryPhotoList(dynamic);
  }

  // 回复列表
  public List<Replay> queryReplayList(Replay replay) {
    return replayMapper.queryReplayList(replay);
  }

  public int insertMessage(Bulletin bulletin) {

    return bulletinMapper.insertMessage(bulletin);
  }

  public int queryDynamic(Dynamic dynamic) {
    return dynamicMapper.queryDynamic(dynamic);
  }

  public int insertDynamic(Dynamic dynamic) {
    return dynamicMapper.insertDynamic(dynamic);
  }

  public int insertReplay(Replay replay) {
    return replayMapper.insertReplay(replay);
  }

  public SelectDynamic queryDetail(Bulletin bulletin) {
    return bulletinMapper.queryDetail(bulletin);
  }

  // 公告列表获取(后台管理)
  public List<Bulletin> queryBulletinList(Bulletin sysBulletin) {
    return this.bulletinMapper.queryBulletinList(sysBulletin);
  }
  
  // 公告信息批量删除
  public int deleteList(Map<String, Object> map) {
    return this.bulletinMapper.deleteList(map);
  }
  
  // 公告列表获取
  public List<Bulletin> selectBulletinList(Bulletin sysBulletin) {
    return this.bulletinMapper.selectBulletinList(sysBulletin);
  }

  // 公告信息查询
  public Bulletin queryByBulletinId(Integer bulletinId) {
    return this.bulletinMapper.queryByBulletinId(bulletinId);
  }

  // 公告信息保存
  public Integer addBulletinInfo(Bulletin sysBulletin) {
    return this.bulletinMapper.addBulletinInfo(sysBulletin);
  }
}
