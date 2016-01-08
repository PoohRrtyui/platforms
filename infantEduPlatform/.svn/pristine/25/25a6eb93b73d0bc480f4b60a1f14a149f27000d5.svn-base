package com.xunyun.infanteduplatform.controller.kindergarten;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.domain.SysCodeMaster;
import com.xunyun.infanteduplatform.domain.TreeEntity;
import com.xunyun.infanteduplatform.domain.interaction.Discussion;
import com.xunyun.infanteduplatform.service.CodeMasterService;
import com.xunyun.infanteduplatform.service.DiscussionInfoService;

@Controller
@RequestMapping("discussion")
public class DiscussionController {
	@Resource
	private CodeMasterService codeMasterService;
	@Resource
	private DiscussionInfoService discussionInfoService;

	// 专题分类信息获取
	@RequestMapping(value = "/subjectList")
	@ResponseBody
	public List<TreeEntity> subjectList() {
		// 树对象
		List<TreeEntity> treeList = new ArrayList<TreeEntity>();

		// 大分类编号
		String largeCategoryCd = "discussionSubject";
		// 查询分类信息
		List<SysCodeMaster> subjectList = codeMasterService
				.findMessage(largeCategoryCd);
		for (int i = 0; i < subjectList.size(); i++) {
			// 分类信息
			SysCodeMaster codeMaster = subjectList.get(i);

			// 树对象
			TreeEntity tree = new TreeEntity();
			// 树Id
			tree.setId(i + 1);
			// 父节点Id
			tree.setpId(0);
			// 节点名称
			tree.setName(codeMaster.getSmallCategoryName());
			// 节点名称
			tree.setNameKey(codeMaster.getSmallCategoryCd());

			treeList.add(tree);
		}

		return treeList;
	}

	// 交流列表获取
	@RequestMapping(value = "/discussionList")
	@ResponseBody
	public Map<Object, Object> discussionList(
			@RequestParam(value = "organizationId", required = false) Integer organizationId,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "draw", required = false) Integer draw,
			@RequestParam(value = "start", required = false) Integer start,
			@RequestParam(value = "length", required = false) Integer length) {
		// 开始条数
		int startNumber = start + 1;
		// 结束条数
		int endNumber = start + length;
		// 总数目
		int countNumber = 0;
		Discussion discussion = new Discussion();
		discussion.setOrganizationId(organizationId);
		discussion.setSubject(subject);
		discussion.setStartNumber(startNumber);
		discussion.setEndNumber(endNumber);
		// 获取数据
		List<Discussion> listData = discussionInfoService
				.queryDiscussionInfo(discussion);
		// 数据不为空，取总数
		if (listData != null && listData.size() > 0) {
			countNumber = listData.get(0).getDataCount();
		}
		// 创建时间
		int j = listData.size();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < j; i++) {
			listData.get(i).setStrCreationTime(
					df.format(listData.get(i).getCreationTime()));
		}
		// 返回对象
		Map<Object, Object> info = new HashMap<Object, Object>();
		// 数据列表
		info.put("data", listData);
		// 总条数
		info.put("recordsTotal", countNumber);
		// 过滤条数
		info.put("recordsFiltered", countNumber);
		// 当前页数
		info.put("draw", draw);

		return info;
	}
	
	// 批量删除交流信息
	  @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
	  @ResponseBody
	  public int deleteList(@RequestParam(value = "ids", required = false) String ids) {
	    String[] array = ids.split(",");
	    List<String> list = new ArrayList<String>();
	    Map<String, Object> map = new HashMap<String, Object>();
	    for (int i = 0; i < array.length; i++) {
	      list.add(array[i]);
	    }
	    map.put("idList", list);
	    int count = discussionInfoService.deleteList(map);
	    return count;
	  }
}
