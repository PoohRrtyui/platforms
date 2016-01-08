package com.xunyun.infanteduplatform.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

  public class InteractionUtils{
     
    // 获取用户头像地址(项目)
    public static String getBlob(HttpServletRequest request, String photourl) throws IOException {

      String scheme = request.getScheme();
      String serverName = request.getServerName();
      int serverPort = request.getServerPort();
      String contextPath = request.getContextPath();
      String resourcePath = "/static/images/";

      String str = "";

      try {
        // 初始加载路径
        String loadPath =
          scheme + "://" + serverName + ":" + serverPort + contextPath
              + resourcePath.replaceAll("\\|", "/");
        if (!"".equals(photourl) && photourl != null) {
          // 文件夹存在时候的路径
          String subPath = "/static/images/uploadImages/userLogo/";
          String existPath =
                  scheme + "://" + serverName + ":" + serverPort + contextPath + subPath
                          + photourl + "/";
          str = existPath + "180x180.jpg";
        } else {
          str = loadPath + "180x180.jpg";
        }
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
      return str;
    }
    
    //根据photourl获取二进制图片流
    @SuppressWarnings("resource")
    public static byte[] getPhoto(String photourl) throws IOException, SQLException{

      File file;
      byte[] bytes = new byte[10240000];
      //用户没有上传头像
      if("".equals(photourl) || photourl == null){
       bytes = null;
      }else{
        try {
          //TODO 这里是地址
          file = new File(SysConfigUtils.getRealPath() + photourl + "\\50x50.jpg");

          long len = file.length();

          BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
          int r = bufferedInputStream.read(bytes);
          if (r != len)
            throw new IOException("读取文件不正确");
          bufferedInputStream.close();
        }catch (Exception e){
          System.out.println(e.getMessage());
        }
      }
      return bytes;
    }
    
  //根据blob返回文件流
  public static File blob2File(Blob blob) throws Exception {
    
    File file = new File("d:\\diaoge.jpg");
    OutputStream os = new FileOutputStream(file);
    byte[] buffer = new byte[8192000];
    int bytesRead = 0;
    InputStream inputStream;
    try {
        inputStream = blob.getBinaryStream();
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
          os.write(buffer, 0, bytesRead);
          }
        os.close();
        inputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
        throw new Exception("fault");
    }
  
    return file;
  }

}