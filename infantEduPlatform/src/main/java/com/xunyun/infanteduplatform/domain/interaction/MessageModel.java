package com.xunyun.infanteduplatform.domain.interaction;

public class MessageModel {
	// 收件人姓名
	private String receiveName;
	//收件人id
	private Integer receiveUserId;
	//发件人id
	private Integer sendOutUserId;
	
	
	public Integer getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Integer getSendOutUserId() {
		return sendOutUserId;
	}

	public void setSendOutUserId(Integer sendOutUserId) {
		this.sendOutUserId = sendOutUserId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	
	
}
