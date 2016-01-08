package com.xunyun.infanteduplatform.utils;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.Properties;

public class SysConfigUtils {
  
  private static String common;
  
  private static String realPath;
  
  private static String remind;
  
  private static String getConfig(String str){
    try {
      Properties pro=new Properties();
      String path=URLDecoder.decode(SysConfigUtils.class.getResource("/sysconfig.properties").getPath(), "UTF-8");
      pro.load(new FileInputStream(path));
      common=pro.getProperty(str);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return common;
  }

  public static String getRealPath() {
    realPath=getConfig("realPath");
    return realPath;
  }

  public static void setRealPath(String realPath) {
    SysConfigUtils.realPath = realPath;
  }

  public static String getRemind() {
    remind=getConfig("remind");
    return remind;
  }

  public static void setRemind(String remind) {
    SysConfigUtils.remind = remind;
  }
}
