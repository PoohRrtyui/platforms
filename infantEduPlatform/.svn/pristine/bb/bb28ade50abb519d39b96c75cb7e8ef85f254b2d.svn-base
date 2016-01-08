package com.xunyun.infanteduplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.Message;
import com.xunyun.infanteduplatform.persistent.MessageInfoMapper;

@Service("messageService")
public class MessageService {
	@Autowired
	private MessageInfoMapper messageInfoMapper;

	/* 消息列表获取 （自用）*/
	public List<Message> messageList(Message message) {
		return this.messageInfoMapper.messageList(message);
	}
	
	/* 消息列表获取 */
	public List<Message> selectMessageList(Message message) {
		return this.messageInfoMapper.selectMessageList(message);
	}
	
	/*消息信息获取*/
	public List<Message> selectmessageInfo(Message message) {
		return this.messageInfoMapper.selectmessageInfo(message);
	}
	
	/*消息信息获取（自用）*/
	public List<Message> querymessageInfo(Message message) {
		return this.messageInfoMapper.querymessageInfo(message);
	}
	
	/*消息信息删除*/
	public int deleteMessage(Message message) {
		return this.messageInfoMapper.deleteMessage(message);
	}
	
	/*消息信息增加*/
	public int insertMessage(Message message) {
      return this.messageInfoMapper.insertMessage(message);
    }
}
