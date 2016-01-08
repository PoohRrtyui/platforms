package com.xunyun.infanteduplatform.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.Collect;
import com.xunyun.infanteduplatform.domain.interaction.Discussion;
import com.xunyun.infanteduplatform.persistent.CollectMapper;
import com.xunyun.infanteduplatform.persistent.DiscussionInfoMapper;
import com.xunyun.infanteduplatform.persistent.DiscussionReplayMapper;
import com.xunyun.infanteduplatform.persistent.DynamicMapper;

@Service("discussionInfoService")
public class DiscussionInfoService {
	@Autowired
	private DiscussionInfoMapper discussionInfoMapper;

	@Autowired
	private DiscussionReplayMapper discussionReplayMapper;

	@Autowired
	private CollectMapper collectMapper;

	@Autowired
	private DynamicMapper dynamicMapper;

	/* 交流信息查询 */
	public List<Discussion> queryDiscussionInfo(Discussion discussion) {
		return this.discussionInfoMapper.queryDiscussionInfo(discussion);
	}

	/* 交流信息查询 */
	public List<Discussion> selectDiscussionInfo(Discussion discussion) {
		return this.discussionInfoMapper.selectDiscussionInfo(discussion);
	}

	/* 交流信息保存 */
	public int publishMessage(Discussion discussion) {
		return this.discussionInfoMapper.publishMessage(discussion);
	}

	/* 我的社区（该用户发表的交流信息） */
	public List<Discussion> mypublishCollect(Discussion discussion) {
		return this.discussionInfoMapper.mypublishCollect(discussion);
	}

	/* 我的社区（该用户发表的交流信息） */
	public List<Discussion> selectMypublishCollect(Discussion discussion) {
		return this.discussionInfoMapper.selectMypublishCollect(discussion);
	}

	/* 我的社区（该用户收藏的交流信息）（自用） */
	public List<Discussion> myCollect(Collect collect) {
		return this.collectMapper.myCollect(collect);
	}

	/* 我的社区（该用户收藏的交流信息） */
	public List<Discussion> selectMyCollect(Collect collect) {
		return this.collectMapper.myCollect(collect);
	}

	// 交流信息批量删除
	public int deleteList(Map<String, Object> map) {
		return this.discussionInfoMapper.deleteList(map);
	}

}
