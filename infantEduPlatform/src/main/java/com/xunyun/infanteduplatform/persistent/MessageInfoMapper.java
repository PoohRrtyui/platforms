package com.xunyun.infanteduplatform.persistent;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xunyun.infanteduplatform.domain.interaction.Message;

@Repository
public interface MessageInfoMapper {
	// 消息列表获取（自用）
	List<Message> messageList(Message message);

	// 消息列表获取
	List<Message> selectMessageList(Message message);

	// 消息信息获取（自用）
	List<Message> querymessageInfo(Message message);

	// 消息信息获取
	List<Message> selectmessageInfo(Message message);

	// 消息信息删除
	int deleteMessage(Message message);

	// 消息信息增加
	int insertMessage(Message message);

}
