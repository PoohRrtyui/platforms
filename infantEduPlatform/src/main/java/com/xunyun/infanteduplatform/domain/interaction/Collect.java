package com.xunyun.infanteduplatform.domain.interaction;

import java.util.Date;

public class Collect {
	// 收藏Id
	Integer collectId;
	// 模块Id
	Integer moduleId;
	// 用户Id
	Integer userId;
	// 收藏I时间
	Date collectTime;
	// 类型
	Integer collectType;
	// 开始数目
	private Integer startNumber;
	// 结束数目
	private Integer endNumber;
	// 收藏总数
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

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public Integer getCollectType() {
		return collectType;
	}

	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}

}
