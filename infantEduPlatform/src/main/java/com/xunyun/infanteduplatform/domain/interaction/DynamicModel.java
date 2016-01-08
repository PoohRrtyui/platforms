package com.xunyun.infanteduplatform.domain.interaction;

public class DynamicModel {
		//用户Id
		private Integer userId;
		//点赞用户头像
		private  byte[] dynamicPhotoUrl;
		
		
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public byte[] getDynamicPhotoUrl() {
			return dynamicPhotoUrl;
		}
		public void setDynamicPhotoUrl(byte[] dynamicPhotoUrl) {
			this.dynamicPhotoUrl = dynamicPhotoUrl;
		}
		
}
