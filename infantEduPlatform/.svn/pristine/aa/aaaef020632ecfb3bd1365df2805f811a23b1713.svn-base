package com.xunyun.infanteduplatform.controller.kindergarten;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyun.infanteduplatform.domain.interaction.Bulletin;
import com.xunyun.infanteduplatform.service.BulletinService;

@Controller
@RequestMapping("bulletin")
public class BulletinInfoController {
  @Autowired
  private BulletinService bulletinService;

  /**
   * @author lpp 方法描述：通知公告列表查询
   */
  @RequestMapping(value = "/queryBulletinList")
  @ResponseBody
  public Map<Object, Object> queryBulletinList(@RequestParam(value = "search[value]",
      required = false) String keyValue,
      @RequestParam(value = "draw", required = false) Integer draw, @RequestParam(value = "start",
          required = false) Integer start,
      @RequestParam(value = "length", required = false) Integer length, @RequestParam(
          value = "bulletinType", required = false) Integer bulletinType) {
    // 开始条数
    int startNumber = start + 1;
    // 结束条数
    int endNumber = start + length;
    // 总数目
    int countNumber = 0;
    // 声明对象
    Bulletin item = new Bulletin();
    // 查询条件
    item.setKeyValue(keyValue);
    // 开始条数
    item.setStartNumber(startNumber);
    // 结束条数
    item.setEndNumber(endNumber);
    // 类型
    item.setBulletinType(bulletinType);

    // 获取数据
    List<Bulletin> listData = bulletinService.queryBulletinList(item);
    // 创建时间
    int j = listData.size();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (int i = 0; i < j; i++) {
      listData.get(i).setStrCreationTime(df.format(listData.get(i).getCreationTime()));
    }

    // 数据不为空，取总数
    if (listData != null && j > 0) {
      countNumber = listData.get(0).getDataCount();
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

  // 批量删除
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
    int count = bulletinService.deleteList(map);
    return count;
  }
}
