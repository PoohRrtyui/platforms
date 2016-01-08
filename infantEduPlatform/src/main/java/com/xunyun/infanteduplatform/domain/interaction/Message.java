package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;

public class Message {
	// 消息id
	private Integer messageId;
	// 发件用户Id
	private Integer sendOutUserId;
	// 发件用户姓名
	private String sendOutName;
	// 收件用户Id
	private Integer receiveUserId;
	// 收件人姓名
	private String receiveName;
	// 内容
	private String content;
	// 发送时间
	private Date sendOutTime;
	// 阅读状态
	private Integer messageType;
	// 开始条数
	private Integer startNumber;
	// 结束条数
	private Integer endNumber;
	// 总条数
	private Integer dataCount;
	// 数据条数
	private Integer count;
	// 数据方向
	private Integer direction;
	
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	public Integer getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(Integer endNumber) {
		this.endNumber = endNumber;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public String getSendOutName() {
		return sendOutName;
	}

	public void setSendOutName(String sendOutName) {
		this.sendOutName = sendOutName;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getSendOutUserId() {
		return sendOutUserId;
	}

	public void setSendOutUserId(Integer sendOutUserId) {
		this.sendOutUserId = sendOutUserId;
	}

	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendOutTime() {
		return sendOutTime;
	}

	public void setSendOutTime(Date sendOutTime) {
		this.sendOutTime = sendOutTime;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

}
