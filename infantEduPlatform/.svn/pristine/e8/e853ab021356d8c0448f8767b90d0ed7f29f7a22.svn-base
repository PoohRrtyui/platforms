package com.xunyun.infanteduplatform.controller.appinfo;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @description 上传帮助类
 * 
 */
public class UploadHelper
{
    
    /**
     * @descrption 根据HttpServletRequest对象获取MultipartFile集合
     * @return MultipartFile集合
     */
    public static List<MultipartFile> getFileSet(HttpServletRequest request, long maxLength,
            String[] allowExtName)
    {
        MultipartHttpServletRequest multipartRequest = null;
        try
        {
            multipartRequest = (MultipartHttpServletRequest) request;
        }
        catch (Exception e)
        {
            return new LinkedList<MultipartFile>();
        }
        
        List<MultipartFile> files = new LinkedList<MultipartFile>();
        files = multipartRequest.getFiles("upload");
        // 移除不符合条件的
        for (int i = 0; i < files.size(); i++)
        {
            if (!validateFile(files.get(i), maxLength, allowExtName))
            {
                files.remove(files.get(i));
                if (files.size() == 0)
                {
                    return files;
                }
            }
        }
        return files;
    }
    
    /**
     * @param path
     *            保存路径
     * @return 保存的全路径 如“D:\\upload\\2345678.txt”
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file, String path) throws Exception
    {
        
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        String lastFileName = UUID.randomUUID().toString() + extName;
        if (!path.endsWith(File.separator))
        {
            path = path + File.separator;
        }
        File temp = new File(path);
        if (!temp.isDirectory())
        {
            temp.mkdir();
        }
        // 图片存储的全路径
        String fileFullPath = path + lastFileName;
        FileCopyUtils.copy(file.getBytes(), new File(fileFullPath));
        return fileFullPath;
    }
    
    private static boolean validateFile(MultipartFile file, long maxLength, String[] allowExtName)
    {
        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();
        // 处理不选择<strong>文件</strong>点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename == "")
        {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * @descrption 根据HttpServletRequest对象获取MultipartFile集合
     * 筛选不同类型的不符合大小的文件
     * @param HttpServeletRequest 
     * @param Long[] 第一个为图片大小限制
     * 第二个为音频视频大小
     * 第三个为其余文件大小
     * @return MultipartFile集合
     */
    public static List<MultipartFile> getFilteredFileSet(HttpServletRequest request, Long[] maxLength,
            String[] allowExtName)
    {
        MultipartHttpServletRequest multipartRequest = null;
        try
        {
            multipartRequest = (MultipartHttpServletRequest) request;
        }
        catch (Exception e)
        {
            return new LinkedList<MultipartFile>();
        }
        
        List<MultipartFile> files = new LinkedList<MultipartFile>();
        files = multipartRequest.getFiles("upload");
        Long imgMaxLength = maxLength[0];
        Long videoMaxLength = maxLength[1];
        Long fileMaxLength = maxLength[2];
        
        // 移除不符合条件的
        for (int i = 0; i < files.size(); i++)
        {
            //判断图片类型并且进行筛选
            if(files.get(i).getContentType().equals("image/bmp") ||files.get(i).getContentType() .equals("image/x-icon")
                    ||files.get(i).getContentType() .equals("image/jpeg")||files.get(i).getContentType() .equals( "image/png")
                    ||files.get(i).getContentType() .equals( "image/gif")){
                 if (!validateFile(files.get(i), imgMaxLength, allowExtName))
                 {
                     files.remove(files.get(i));
                     if (files.size() == 0)
                     {
                         return files;
                     }
                 }
            }
            if(files.get(i).getContentType() .equals( "audio/mpeg") || files.get(i).getContentType().equals("audio/mpeg")
                    ||files.get(i).getContentType() .equals("video/vnd.rn-realvideo")||files.get(i).getContentType() .equals("video/mp4")
                    ||files.get(i).getContentType() .equals( "flv-application/octet-stream")
                    ||files.get(i).getContentType() .equals( "flv-application/octet-stream")){
                if (!validateFile(files.get(i), videoMaxLength, allowExtName))
                {
                    files.remove(files.get(i));
                    if (files.size() == 0)
                    {
                        return files;
                    }
                }
            }else{
                if (!validateFile(files.get(i), fileMaxLength, allowExtName))
                {
                    files.remove(files.get(i));
                    if (files.size() == 0)
                    {
                        return files;
                    }
                }
            }
            
        }
        return files;
    }
    /**
     * 判断文件类型
     * @param file
     * @return 0为图片，1为音频视频，2为其余文件类型
     */
    public static int getContentType(MultipartFile file){
        if (file.getContentType().equals("image/bmp") ||file.getContentType() .equals("image/x-icon")
                ||file.getContentType() .equals("image/jpeg")||file.getContentType() .equals( "image/png")
                ||file.getContentType() .equals( "image/gif")){
            return 0;
        }if(file.getContentType() .equals( "audio/mpeg") || file.getContentType().equals("audio/mpeg")
                ||file.getContentType() .equals("video/vnd.rn-realvideo")||file.getContentType() .equals("video/mp4")
                ||file.getContentType() .equals( "flv-application/octet-stream")
                ||file.getContentType() .equals( "flv-application/octet-stream")
                ){
            return 1;
        }else{ 
            return 2;
        }
    }
    
}

