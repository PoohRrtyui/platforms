package com.xunyun.infanteduplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyun.infanteduplatform.domain.interaction.Share;
import com.xunyun.infanteduplatform.persistent.ShareMapper;

@Service("shareService")
public class ShareService {
	@Autowired
	private ShareMapper shareMapper;

	/* 交流信息分享*/
	public int shareDiscussion(Share share) {
		return this.shareMapper.shareDiscussion(share);
	}
}
