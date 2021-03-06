package com.xunyun.infanteduplatform.domain;

public class TreeEntity {
	private int id;
	private int pId;
	private String name;
	private Boolean isParent;
	// 班级部分获取用户列表标识符，其他不需要引用
	private Boolean isClass;

	private int userid;

	/**
	 * 名称Key
	 */
	private String nameKey;

	public String getNameKey() {
		return nameKey;
	}

	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getIsClass() {
		return isClass;
	}

	public void setIsClass(Boolean isClass) {
		this.isClass = isClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
