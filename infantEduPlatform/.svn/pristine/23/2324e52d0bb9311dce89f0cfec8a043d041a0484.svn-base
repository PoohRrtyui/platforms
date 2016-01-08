package com.xunyun.infanteduplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.DiscussionReplay;
import com.xunyun.infanteduplatform.persistent.DiscussionReplayMapper;

@Service("discussionReplayService")
public class DiscussionReplayService {
	 @Autowired
	 private DiscussionReplayMapper discussionReplayMapper;
	 
	 /*我的社区（该用户评论的信息）（自用）*/ 
	 public List<DiscussionReplay> myCommentCollect(DiscussionReplay discussionReplay){
	    return this.discussionReplayMapper.myCommentCollect(discussionReplay);
	 }
	 
	 /*我的社区（该用户评论的信息）*/ 
	 public List<DiscussionReplay> selectMyCommentCollect(DiscussionReplay discussionReplay){
	    return this.discussionReplayMapper.selectMyCommentCollect(discussionReplay);
	 }
	 
	 /*交流信息回复*/
	 public int replyExchange(DiscussionReplay discussionReplay){
			return this.discussionReplayMapper.replyExchange(discussionReplay);
	 }
	 
	 /*获取该交流信息下的评论内容和时间*/
	 public List<DiscussionReplay> querydiscussionReplay(Integer moduleId){
		    return this.discussionReplayMapper.querydiscussionReplay(moduleId);
	 }
}
