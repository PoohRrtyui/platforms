package com.xunyun.infanteduplatform.controller.interaction.exchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.utils.DateUtils;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;
import com.xunyun.infanteduplatform.domain.SysCodeMaster;
import com.xunyun.infanteduplatform.domain.interaction.Collect;
import com.xunyun.infanteduplatform.domain.interaction.Discussion;
import com.xunyun.infanteduplatform.domain.interaction.DiscussionReplay;
import com.xunyun.infanteduplatform.domain.interaction.DiscussionReplayModel;
import com.xunyun.infanteduplatform.domain.interaction.DiscussionView;
import com.xunyun.infanteduplatform.domain.interaction.Dynamic;
import com.xunyun.infanteduplatform.domain.interaction.DiscussionModel;
import com.xunyun.infanteduplatform.domain.interaction.Share;
import com.xunyun.infanteduplatform.domain.interaction.SysCodeMasterModel;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.CodeMasterService;
import com.xunyun.infanteduplatform.service.CollectService;
import com.xunyun.infanteduplatform.service.DiscussionInfoService;
import com.xunyun.infanteduplatform.service.DiscussionReplayService;
import com.xunyun.infanteduplatform.service.DynamicService;
import com.xunyun.infanteduplatform.service.ShareService;

@Controller
@RequestMapping("discussionInfo")
public class DiscussionInfoController {
	@Resource
	private CodeMasterService codeMasterService;
	@Resource
	private AppInfoService appInfoService;
	@Resource
	private DiscussionInfoService discussionInfoService;
	@Resource
	private DynamicService dynamicService;
	@Resource
	private CollectService collectService;
	@Resource
	private DiscussionReplayService discussionReplayService;
	@Resource
	private BulletinService bulletinService;
	@Resource
	private ShareService shareService;

	// 专题分类信息获取
	@RequestMapping(value = "/subjectList")
	@ResponseBody
	public Map<String, Object> subjectList(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			String largeCategoryCd = "discussionSubject";
			List<SysCodeMaster> subject = codeMasterService
					.findMessage(largeCategoryCd);
			List<SysCodeMasterModel> dataList = new ArrayList<SysCodeMasterModel>();
			for (SysCodeMaster disc : subject) {
				// 声明对象
				SysCodeMasterModel sysCodeMasterModel = new SysCodeMasterModel();
				// 小分类编号
				sysCodeMasterModel
						.setSmallCategoryCd(disc.getSmallCategoryCd());
				// 小分类名称
				sysCodeMasterModel.setSmallCategoryName(disc
						.getSmallCategoryName());
				dataList.add(sysCodeMasterModel);
			}
			// 返回数据
			map.put("state", "success");
			map.put("message", "");
			map.put("data", dataList);
		}

		return map;
	}

	// 交流列表获取
	@RequestMapping(value = "/discussionList")
	@ResponseBody
	public Map<String, Object> discussionList(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 机构代码
		Integer organizationId = ValueChangeUtils.changeValue(
				jsonobject.get("subject"), null);
		// 专题信息
		String subject = (String) jsonobject.get("subject");
		// 交流Id(默认为0)
		Integer discussionId = ValueChangeUtils.changeValue(
				jsonobject.get("discussionId"), 0);
		// 显示数(默认为10)
		Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"),
				10);
		// 数据方向(默认为0)
		Integer direction = ValueChangeUtils.changeValue(
				jsonobject.get("direction"), 0);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (organizationId == null) {
			map.put("state", "error");
			map.put("message", "机构信息为空");
			map.put("data", "");
		} else if (subject == null || "".equals(subject)) {
			map.put("state", "error");
			map.put("message", "专题信息为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			List<DiscussionModel> dataList = new ArrayList<DiscussionModel>();
			Discussion discussion = new Discussion();
			discussion.setOrganizationId(organizationId);
			discussion.setSubject(subject);
			discussion.setCount(count);
			discussion.setDirection(direction);
			discussion.setDiscussionId(discussionId);
			// 获取数据
			List<Discussion> listData = discussionInfoService
					.selectDiscussionInfo(discussion);
			// 数据不为空，取总数
			if (listData != null && listData.size() > 0) {
				for (Discussion disc : listData) {
					// 获取交流信息用户头像进行转换
					String photourl = disc.getAuthorPhotoUrl();
					// 声明对象
					DiscussionModel edata = new DiscussionModel();
					// TODO
					try {
						byte[] authorphotoUrl = InteractionUtils
								.getPhoto(photourl);
						// 创建人头像
						edata.setAuthorPhotoUrl(authorphotoUrl);
					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					}

					// 交流id
					edata.setDiscussionId(disc.getDiscussionId());
					// 交流内容
					edata.setDiscussionContent(disc.getDiscussionContent());
					// 创建人
					edata.setCreatedBy(disc.getCreatedBy());
					// 回复总数
					edata.setReplyCount(disc.getReplyCount());
					// 点赞总数
					edata.setDynamicCount(disc.getDynamicCount());
					//收藏总数
					edata.setCollectCount(disc.getCollectCount());
					//分享总数
					edata.setShareCount(disc.getShareCount());
					// 创建时间
					Date time = disc.getCreationTime();
					String creationTime = DateUtils.getTimeString(time);
					edata.setCreationTime(creationTime);
					dataList.add(edata);
				}
			}
			map.put("state", "success");
			map.put("message", "");
			map.put("data", dataList);
		}

		return map;
	}

	// 交流信息收藏
	@RequestMapping(value = "/collectSubjects")
	@ResponseBody
	public Map<String, Object> collectSubjects(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 模块id
		Integer moduleId = ValueChangeUtils.changeValue(
				jsonobject.get("moduleId"), null);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户信息为空");
			map.put("data", "");
		} else if (moduleId == null) {
			map.put("state", "error");
			map.put("message", "模块信息为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Collect collect = new Collect();
			Integer collectType = 7;
			// 收藏时间
			collect.setCollectTime(new Date());
			// 收藏者id
			collect.setUserId(userId);
			// 模块id
			collect.setModuleId(moduleId);
			// 收藏类型
			collect.setCollectType(collectType);
			Integer collectCounts = collectService.collectValidate(collect);
			if (collectCounts == null) {
				collectService.collectSubjects(collect);
			} else {
				map.put("state", "success");
				map.put("message", "收藏成功");
				map.put("data", "");
			}
		}

		return map;
	}

	/* 交流信息保存 */
	@RequestMapping(value = "/publishMessage")
	@ResponseBody
	public Map<String, Object> publishMessage(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 机构代码
		Integer organizationId = ValueChangeUtils.changeValue(
				jsonobject.get("organizationId"), null);
		// 专题信息
		String subject = (String) jsonobject.get("subject");
		// 交流内容
		String discussionContent = (String) jsonobject.get("discussionContent");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (organizationId == null) {
			map.put("state", "error");
			map.put("message", "机构代码为空");
			map.put("data", "");
		} else if (subject == null || "".equals(subject)) {
			map.put("state", "error");
			map.put("message", "专题信息为空");
			map.put("data", "");
		} else if (discussionContent == null || "".equals(discussionContent)) {
			map.put("state", "error");
			map.put("message", "发表内容为空");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "创建者为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Discussion discussion = new Discussion();
			// 创建时间
			discussion.setCreationTime(new Date());
			// 最终修改时间
			discussion.setLastUpdateTime(new Date());
			// 创建人
			String createdBy = userId.toString();
			discussion.setCreatedBy(createdBy);
			// 最终修改人
			discussion.setLastUpdatedBy(createdBy);
			// 机构代码
			discussion.setOrganizationId(organizationId);
			// 专题
			discussion.setSubject(subject);
			// 交流内容
			discussion.setDiscussionContent(discussionContent);
			discussionInfoService.publishMessage(discussion);
			map.put("state", "success");
			map.put("message", "发表成功");
			map.put("data", "");
		}

		return map;
	}

	// 我的社区（该用户发表的交流信息）
	@RequestMapping(value = "/mypublishCollect")
	@ResponseBody
	public Map<String, Object> mypublishCollect(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 交流Id(默认为0)
		Integer discussionId = ValueChangeUtils.changeValue(
				jsonobject.get("discussionId"), 0);
		// 显示数(默认为10)
		Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"),
				10);
		// 数据方向(默认为0)
		Integer direction = ValueChangeUtils.changeValue(
				jsonobject.get("direction"), 0);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "作者为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			List<DiscussionView> dataList = new ArrayList<DiscussionView>();
			// 声明对象
			Discussion discussion = new Discussion();
			// 创建人
			String createdBy = userId.toString();
			discussion.setCreatedBy(createdBy);
			discussion.setCount(count);
			discussion.setDiscussionId(discussionId);
			discussion.setDirection(direction);
			// 获取数据
			List<Discussion> listData = discussionInfoService
					.selectMypublishCollect(discussion);
			for (Discussion disc : listData) {
				// 将对象disc里面的数据存到edata里面
				DiscussionView edata = new DiscussionView();
				edata.setAuthorName(disc.getAuthorName());
				edata.setDiscussionContent(disc.getDiscussionContent());
				String creationTime = DateUtils.getTimeString(disc
						.getCreationTime());
				edata.setCreationTime(creationTime);
				dataList.add(edata);
			}
			map.put("state", "success");
			map.put("message", "");
			map.put("data", dataList);
		}

		return map;
	}

	// 我的社区（该用户评论的信息)
	@RequestMapping(value = "/myCommentCollect")
	@ResponseBody
	public Map<String, Object> myCommentCollect(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 回复Id(默认为0)
		Integer replayId = ValueChangeUtils.changeValue(
				jsonobject.get("replayId"), 0);
		// 显示数(默认为10)
		Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"),
				10);
		// 数据方向(默认为0)
		Integer direction = ValueChangeUtils.changeValue(
				jsonobject.get("direction"), 0);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户信息为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			List<DiscussionReplayModel> dataList = new ArrayList<DiscussionReplayModel>();
			DiscussionReplay discussionReplay = new DiscussionReplay();
			discussionReplay.setUserId(userId);
			discussionReplay.setCount(count);
			discussionReplay.setDirection(direction);
			discussionReplay.setReplayId(replayId);
			// 获取数据
			List<DiscussionReplay> listData = discussionReplayService
					.selectMyCommentCollect(discussionReplay);
			for (DiscussionReplay disc : listData) {
				// 将对象disc里面的数据存到edata里面
				DiscussionReplayModel edata = new DiscussionReplayModel();
				edata.setReplayContent(disc.getReplayContent());
				edata.setReplyName(disc.getReplyName());
				String replayTime = DateUtils.getTimeString(disc
						.getReplayTime());
				edata.setReplayTime(replayTime);
				dataList.add(edata);
			}
			map.put("state", "success");
			map.put("message", "");
			map.put("data", dataList);
		}

		return map;
	}

	// 我的社区（该用户收藏的信息）
	@RequestMapping(value = "/myCollect")
	@ResponseBody
	public Map<String, Object> myCollect(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 收藏Id(默认为0)
		Integer collectId = ValueChangeUtils.changeValue(
				jsonobject.get("collectId"), 0);
		// 显示数(默认为10)
		Integer count = ValueChangeUtils.changeValue(jsonobject.get("count"),
				10);
		// 数据方向(默认为0)
		Integer direction = ValueChangeUtils.changeValue(
				jsonobject.get("direction"), 0);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户信息为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			List<DiscussionView> dataList = new ArrayList<DiscussionView>();
			Collect collect = new Collect();
			collect.setUserId(userId);
			collect.setCollectType(7);
			collect.setCollectId(collectId);
			collect.setCount(count);
			collect.setDirection(direction);
			// 获取数据
			List<Discussion> listData = discussionInfoService
					.myCollect(collect);
			for (Discussion disc : listData) {
				// 将对象disc里面的数据存到edata里面
				DiscussionView edata = new DiscussionView();
				edata.setAuthorName(disc.getAuthorName());
				edata.setDiscussionContent(disc.getDiscussionContent());
				String time = DateUtils.getTimeString(disc.getCreationTime());
				edata.setCreationTime(time);
				dataList.add(edata);
			}
			map.put("state", "success");
			map.put("message", "");
			map.put("data", dataList);
		}

		return map;
	}

	// 交流信息回复
	@RequestMapping(value = "/replyExchange")
	@ResponseBody
	public Map<String, Object> replyExchange(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 模块id
		Integer moduleId = ValueChangeUtils.changeValue(
				jsonobject.get("moduleId"), null);
		// 回复对象id
		Integer targetId = ValueChangeUtils.changeValue(
				jsonobject.get("targetId"), null);
		// 回复内容
		String replayContent = (String) jsonobject.get("replayContent");

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (moduleId == null) {
			map.put("state", "error");
			map.put("message", "模块id为空");
			map.put("data", "");
		} else if (targetId == null) {
			map.put("state", "error");
			map.put("message", "回复对象id为空");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户id为空");
			map.put("data", "");
		} else if (replayContent == null || "".equals(replayContent)) {
			map.put("state", "error");
			map.put("message", "回复内容为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			DiscussionReplay discussionReplay = new DiscussionReplay();
			discussionReplay.setModuleId(moduleId);
			discussionReplay.setReplayContent(replayContent);
			discussionReplay.setReplayTime(new Date());
			discussionReplay.setTargetId(targetId);
			discussionReplay.setUserId(userId);
			discussionReplayService.replyExchange(discussionReplay);
			map.put("state", "success");
			map.put("message", "回复成功");
			map.put("data", "");
		}

		return map;
	}

	// 交流信息点赞
	@RequestMapping(value = "/praiseExchange")
	@ResponseBody
	public Map<String, Object> praiseExchange(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 交流id
		Integer discussionId = ValueChangeUtils.changeValue(
				jsonobject.get("discussionId"), null);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户信息为空");
			map.put("data", "");
		} else if (discussionId == null) {
			map.put("state", "error");
			map.put("message", "交流id为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 定义对象
			Dynamic dynamic = new Dynamic();
			// 当前时间
			dynamic.setDynamicType(7);
			dynamic.setModuleId(discussionId);
			dynamic.setUserId(userId);
			dynamic.setPraiseTime(new Date());
			int count = dynamicService.queryDynamic(dynamic);
			if (count == 0) {
				dynamicService.insertDynamic(dynamic);
			}
			map.put("state", "success");
			map.put("message", "点赞成功!");
			map.put("data", "");
		}

		return map;
	}

	// 交流信息分享
	@RequestMapping(value = "/shareDiscussion")
	@ResponseBody
	public Map<String, Object> shareDiscussion(HttpServletRequest req)
			throws Exception {
		// 字节流
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();
		char[] buff = new char[1024];
		int len;
		while ((len = reader.read(buff)) != -1) {
			sb.append(buff, 0, len);
		}

		// 字符串转换匿名对象
		JSONObject jsonobject = JSONObject.fromObject(sb.toString());

		// 应用系统代码
		String appCode = (String) jsonobject.get("appCode");
		// 用户id
		Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"),
				null);
		// 模块id
		Integer moduleId = ValueChangeUtils.changeValue(
				jsonobject.get("moduleId"), null);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户信息为空");
			map.put("data", "");
		} else if (moduleId == null) {
			map.put("state", "error");
			map.put("message", "模块信息为空");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Share share = new Share();
			// 分享时间
			share.setShareTime(new Date());
			// 分享者id
			share.setUserId(userId);
			// 模块id
			share.setModuleId(moduleId);
			// 分享类型
			share.setShareType(7);
			// 执行分享操作
			shareService.shareDiscussion(share);

			map.put("state", "success");
			map.put("message", "分享成功");
			map.put("data", "");
		}

		return map;
	}

}
