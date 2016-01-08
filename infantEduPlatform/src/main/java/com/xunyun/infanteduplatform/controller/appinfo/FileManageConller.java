package com.xunyun.infanteduplatform.controller.appinfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.xunyun.infanteduplatform.domain.AppInfo;

@Controller
@RequestMapping("filemanage")
public class FileManageConller {

  @RequestMapping("/uploadFiles")
  @ResponseBody
  public AppInfo IterateFoldarAndFile(
          @RequestParam(value = "upload", required = false) MultipartFile file,HttpServletResponse response,
          HttpServletRequest request,@ModelAttribute(value = "fileManageEntity") AppInfo appInfo)
          {
      String fileName = "";
      String realPath = "";
      String message = "";
      Long imageMaxLength = (long) (1024 * 1024 * 5);// 上传图片最大5M;
      Long videoMaxLength = (long) (1024 * 1024 * 20);// 上传音频视频最大20M;
      Long fileMaxLength = (long) (1024 * 1024 * 10);// 上传音频视频最大20M;
      Long maxLength[] = {imageMaxLength,videoMaxLength,fileMaxLength};//注意顺序 图片大小，音频视频大小，其余文件大小
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);
      if(isMultipart){
          try {
              List<MultipartFile> multipartFiles = UploadHelper.getFilteredFileSet(request, maxLength, null);
              for (MultipartFile multipartFile : multipartFiles) {
                  if (multipartFile.isEmpty()) {
                      message= "文件未选择或文件内容为空！";
                  }else{
                      realPath = request.getSession().getServletContext()
                              .getRealPath(appInfo.getAllParentNames().replaceAll("\\|", "\\\\"));
                      //判断文件类型为附件（非图片或音频视频）
                      if(UploadHelper.getContentType(multipartFile) == 2){
                          fileName = multipartFile.getOriginalFilename();
                          FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,fileName));
                      }else{
                          fileName = (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(Calendar.getInstance().getTime())
                                  +multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                          /*判断文件为图片--开始上传*/
                          if(UploadHelper.getContentType(multipartFile) == 0){
                              FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,fileName));
                              System.out.println("开始："+ Calendar.getInstance().getTime());
                              ImgCompress imgCom = new ImgCompress(realPath
                                      + "\\" + fileName);
                              // TODO 图片压缩尺寸
                              imgCom.resizeFix(1000,1000,realPath + "\\"+ fileName);
                              System.out.println("结束："+ Calendar.getInstance().getTime());
                          }/*判断文件为图片--结束上传*/
                          else{/*判断文件为音频视频--开始上传*/
                              FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath,fileName));
                          }/*判断文件为音频视频--结束上传*/
                      }/*文件上传结束*/
                      //保存上传的文件信息到文件表
                      String scheme = request.getScheme();
                      String serverName = request.getServerName();
                      int serverPort = request.getServerPort();
                      String contextPath = request.getContextPath();
                      String uploadPath = scheme+"://"+serverName+":"+serverPort+contextPath+appInfo.getAllParentNames().replaceAll("\\|", "/");   
                      message= fileName;
                      appInfo.setIconUrl(uploadPath);
                      appInfo.setMessage(message);
                  }
              }
          } catch (Exception e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
          }
      }   
      return appInfo;
  }
}
