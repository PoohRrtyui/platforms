package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.DiscussionReplay;

@Repository
public interface DiscussionReplayMapper {
	// 我的社区（该用户评论的信息）
	List<DiscussionReplay> myCommentCollect(DiscussionReplay discussionReplay);

	// 我的社区（该用户评论的信息）
	List<DiscussionReplay> selectMyCommentCollect(DiscussionReplay discussionReplay);

	// 交流信息保存
	int replyExchange(DiscussionReplay discussionReplay);

	// 获取该交流信息下的评论内容和时间
	List<DiscussionReplay> querydiscussionReplay(Integer moduleId);
}
