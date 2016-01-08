package com.xunyun.infanteduplatform.controller.interaction.message;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.domain.interaction.Message;
import com.xunyun.infanteduplatform.domain.interaction.MessageInfo;
import com.xunyun.infanteduplatform.domain.interaction.MessageModel;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.MessageService;
import com.xunyun.infanteduplatform.utils.DateUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;

@Controller
@RequestMapping("messageInfo")
public class MessageInfoController {

	@Resource
	private AppInfoService appInfoService;
	@Resource
	private MessageService messageService;

	// 消息列表获取
	@RequestMapping(value = "/messageList")
	@ResponseBody
	public Map<String, Object> messageList(HttpServletRequest req)
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

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (userId == null) {
			map.put("state", "error");
			map.put("message", "用户id为空，获取信息失败");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Message message = new Message();
			// 发件人id
			message.setSendOutUserId(userId);
			// 获取数据
			List<Message> listData = messageService.selectMessageList(message);

			List<MessageModel> list = new ArrayList<MessageModel>();

			// 查询数据
			for (Message mes : listData) {
				// 声明对象
				MessageModel messageModel = new MessageModel();
				// 收件人姓名
				messageModel.setReceiveName(mes.getReceiveName());
				// 收件人id
				messageModel.setReceiveUserId(mes.getReceiveUserId());
				// 发件人id
				messageModel.setSendOutUserId(mes.getSendOutUserId());

				list.add(messageModel);
			}

			// 状态
			map.put("state", "success");
			// 提示信息
			map.put("message", "");
			// 数据列表
			map.put("data", list);
		}
		return map;
	}

	// 消息信息获取
	@RequestMapping(value = "/messageInfo")
	@ResponseBody
	public Map<String, Object> messageInfo(HttpServletRequest req)
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
		// 发件人id
		Integer sendOutUserId = ValueChangeUtils.changeValue(
				jsonobject.get("sendOutUserId"), null);

		// 收件人id
		Integer receiveUserId = ValueChangeUtils.changeValue(
				jsonobject.get("receiveUserId"), null);

		// 消息Id(默认为0)
		Integer messageId = ValueChangeUtils.changeValue(
				jsonobject.get("messageId"), 0);

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
		} else if (sendOutUserId == null) {
			map.put("state", "error");
			map.put("message", "发件人id为空，获取信息失败");
			map.put("data", "");
		} else if (receiveUserId == null) {
			map.put("state", "error");
			map.put("message", "收件人id为空，获取信息失败");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {

			// 声明对象
			Message message = new Message();
			// 发件人id
			message.setSendOutUserId(sendOutUserId);
			// 收件人id
			message.setReceiveUserId(receiveUserId);
			// 消息id
			message.setMessageId(messageId);
			// 条数
			message.setCount(count);
			// 数据方向
			message.setDirection(direction);

			// 获取数据
			List<Message> listData = messageService.selectmessageInfo(message);

			List<MessageInfo> list = new ArrayList<MessageInfo>();

			// 查询数据
			for (Message mes : listData) {
				// 声明对象
				MessageInfo messageInfo = new MessageInfo();
				// 内容
				messageInfo.setContent(mes.getContent());
				// 收件人姓名
				messageInfo.setReceiveName(mes.getReceiveName());
				// 收件人id
				messageInfo.setReceiveUserId(mes.getReceiveUserId());
				// 发件人姓名
				messageInfo.setSendOutName(mes.getSendOutName());
				// 获取创建时间
				String time = DateUtils.getTimeString(mes.getSendOutTime());
				// 发送时间
				messageInfo.setSendOutTime(time);
				// 发件人id
				messageInfo.setSendOutUserId(mes.getSendOutUserId());

				list.add(messageInfo);

			}

			// 状态
			map.put("state", "success");
			// 提示信息
			map.put("message", "");
			// 数据列表
			map.put("data", list);
		}
		return map;
	}

	// 消息信息删除
	@RequestMapping(value = "/deleteMessage")
	@ResponseBody
	public Map<String, Object> deleteMessage(HttpServletRequest req)
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
		// 发件人id
		Integer sendOutUserId = ValueChangeUtils.changeValue(
				jsonobject.get("sendOutUserId"), null);

		// 收件人id
		Integer receiveUserId = ValueChangeUtils.changeValue(
				jsonobject.get("receiveUserId"), null);

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (sendOutUserId == null) {
			map.put("state", "error");
			map.put("message", "发件人id为空，获取信息失败");
			map.put("data", "");
		} else if (receiveUserId == null) {
			map.put("state", "error");
			map.put("message", "收件人id为空，获取信息失败");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Message message = new Message();
			// 发件人id
			message.setSendOutUserId(sendOutUserId);
			// 收件人id
			message.setReceiveUserId(receiveUserId);

			// 执行删除方法
			messageService.deleteMessage(message);

			map.put("state", "success");
			map.put("message", "删除成功");
			map.put("data", "");

		}
		return map;
	}

	// 消息信息增加
	@RequestMapping(value = "/insertMessage")
	@ResponseBody
	public Map<String, Object> insertMessage(HttpServletRequest req)
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
		// 发件人id
		Integer sendOutUserId = ValueChangeUtils.changeValue(
				jsonobject.get("sendOutUserId"), null);

		// 收件人id
		Integer receiveUserId = ValueChangeUtils.changeValue(
				jsonobject.get("receiveUserId"), null);
		// 内容
		String content = (String) jsonobject.get("content");

		Map<String, Object> map = new HashMap<String, Object>();
		if (appCode == null || "".equals(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为空，获取信息失败");
			map.put("data", "");
		} else if (sendOutUserId == null) {
			map.put("state", "error");
			map.put("message", "发件人id为空，获取信息失败");
			map.put("data", "");
		} else if (receiveUserId == null) {
			map.put("state", "error");
			map.put("message", "收件人id为空，获取信息失败");
			map.put("data", "");
		} else if (content == null || "".equals(content)) {
			map.put("state", "error");
			map.put("message", "内容为空，获取信息失败");
			map.put("data", "");
		} else if (!appInfoService.findByAppCode(appCode)) {
			map.put("state", "error");
			map.put("message", "应用状态为非开启状态，无权获取用户信息");
			map.put("data", "");
		} else {
			// 声明对象
			Message message = new Message();
			// 发件人id
			message.setSendOutUserId(sendOutUserId);
			// 收件人id
			message.setReceiveUserId(receiveUserId);
			// 内容
			message.setContent(content);
			// 时间
			message.setSendOutTime(new Date());
			// 执行删除方法
			messageService.insertMessage(message);

			map.put("state", "success");
			map.put("message", "添加成功");
			map.put("data", "");

		}
		return map;
	}

}
