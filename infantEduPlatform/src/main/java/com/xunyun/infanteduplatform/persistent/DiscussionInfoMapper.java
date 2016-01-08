package com.xunyun.infanteduplatform.persistent;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Discussion;
import com.xunyun.infanteduplatform.domain.interaction.Dynamic;

@Repository
public interface DiscussionInfoMapper {
	// 交流列表获取
	List<Discussion> queryDiscussionInfo(Discussion discussion);

	// 交流列表获取
	List<Discussion> selectDiscussionInfo(Discussion discussion);

	// 点赞用户头像获取
	List<Dynamic> queryPhotoList(Dynamic dynamic);

	// 交流信息保存
	int publishMessage(Discussion discussion);

	// 我的社区（该用户发表的交流信息）
	List<Discussion> mypublishCollect(Discussion discussion);

	// 我的社区（该用户发表的交流信息）
	List<Discussion> selectMypublishCollect(Discussion discussion);

	// 交流信息批量删除
	int deleteList(Map<String, Object> map);
}
