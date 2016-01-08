package com.xunyun.infanteduplatform.utils;

import java.util.Date;

/**
 * 转换参数值
 * 
 * @author 马荷兵
 * 
 */
public class ValueChangeUtils {

  /**
   * 转换参数值为Integer类型
   * 
   * @param key 参数
   * @param number 默认值
   * @return 转换后的值
   */
  public static Integer changeValue(Object key, Integer number) {
    // 判断参数是否为空
    if (key != null && !key.toString().equals("")) {
      try {
        // 转换参数值
        number = Integer.parseInt(key.toString());
      } catch (Exception e) {
        return number;
      }
    }

    return number;
  }

  /**
   * 转换参数值为Date类型
   * 
   * @param key 参数
   * @param date 默认值
   * @return 转换后的值
   */
  public static Date changeDate(Object key, Date date) {
    // 判断参数是否为空
    if (key != null && !key.toString().equals("")) {
      try {
        // 转换参数值
        date = DateUtils.stringToDate(key.toString());
      } catch (Exception e) {
        return date;
      }
    }

    return date;
  }
}
