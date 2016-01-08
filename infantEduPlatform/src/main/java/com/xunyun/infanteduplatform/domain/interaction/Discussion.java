package com.xunyun.infanteduplatform.domain.interaction;

import java.util.List;
import java.util.Date;

import com.xunyun.infanteduplatform.domain.interaction.Dynamic;

public class Discussion {
	// 交流id
	private Integer discussionId;
	// 机构代码
	private Integer organizationId;
	// 交流内容
	private String discussionContent;
	// 删除标记
	private Integer deleteFlg;
	// 来源
	private Integer sources;
	// 级别
	private Integer discussionLevel;
	// 专题
	private String subject;
	// 创建时间
	private Date creationTime;
	// 创建人
	private String createdBy;
	// 最终修改时间
	private Date lastUpdateTime;
	// 最终修改人
	private String lastUpdatedBy;
	// 开始数目
	private Integer startNumber;
	// 结束数目
	private Integer endNumber;
	// 交流总条数
	private Integer dataCount;
	// 回复总条数
	private Integer replyCount;
	// 点赞总条数
	private Integer dynamicCount;
	// 分享总条数
	private Integer shareCount;
	// 收藏总数
	private Integer collectCount;
	// 作者头像
	private String authorPhotoUrl;
	// 作者姓名
	private String authorName;
	// 时间
	private String strCreationTime;
	// 数据条数
	private Integer count;
	// 数据方向
	private Integer direction;

	private List<Dynamic> dynamicList;

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public String getStrCreationTime() {
		return strCreationTime;
	}

	public void setStrCreationTime(String strCreationTime) {
		this.strCreationTime = strCreationTime;
	}

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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getDiscussionLevel() {
		return discussionLevel;
	}

	public void setDiscussionLevel(Integer discussionLevel) {
		this.discussionLevel = discussionLevel;
	}

	public List<Dynamic> getDynamicList() {
		return dynamicList;
	}

	public void setDynamicList(List<Dynamic> dynamicList) {
		this.dynamicList = dynamicList;
	}

	public String getAuthorPhotoUrl() {
		return authorPhotoUrl;
	}

	public void setAuthorPhotoUrl(String authorPhotoUrl) {
		this.authorPhotoUrl = authorPhotoUrl;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getDynamicCount() {
		return dynamicCount;
	}

	public void setDynamicCount(Integer dynamicCount) {
		this.dynamicCount = dynamicCount;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
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

	public Integer getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Integer discussionId) {
		this.discussionId = discussionId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getDiscussionContent() {
		return discussionContent;
	}

	public void setDiscussionContent(String discussionContent) {
		this.discussionContent = discussionContent;
	}

	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public Integer getSources() {
		return sources;
	}

	public void setSources(Integer sources) {
		this.sources = sources;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date nowDate) {
		this.lastUpdateTime = nowDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

}
