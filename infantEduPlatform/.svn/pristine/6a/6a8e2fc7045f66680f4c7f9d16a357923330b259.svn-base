package com.xunyun.infanteduplatform.utils;

/*import java.io.File;*/
//import java.io.IOException;
//import java.util.UUID;
//import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


  public class FileUploadUtil {
      public static final List<String> ALLOW_TYPES = Arrays.asList(
              "image/jpg","image/jpeg","image/png","image/gif"
      );
      //文件重命名
      public static String rename(String fileName){
          int i = fileName.lastIndexOf(".");
          String str = fileName.substring(i);
          return new Date().getTime()+""+ new Random().nextInt(99999999) +str;
      }
      //校验文件类型是否是被允许的
      public static boolean allowUpload(String postfix){
          return ALLOW_TYPES.contains(postfix);
      }
     
      /*private void uploadGoodsImg(
          MultipartFile[] imageFiles,
          String goodsId,
          String realPath,
          String resourcePath,
          String type
          ) throws IOException {
            if (imageFiles != null) {
              for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile imageFile = imageFiles[i];
                //先检验文件类型是否被允许
                if (FileUploadUtil.allowUpload(imageFile.getContentType())) {
                  //获得文件名称后重命名
                  String fileName = goodsId + FileUploadUtil.rename(imageFile.getOriginalFilename());
                  File file = new File(realPath + resourcePath + fileName);
                  imageFile.transferTo(file);
                  //缩略图
                  Thumbnails.of(file).size(160, 160).keepAspectRatio(false).outputFormat("jpg").toFile(new File(realPath + resourcePath, fileName + "_160x160.jpg"));
                  String imgUrl = resourcePath + fileName;
                  GoodsImg goodsImg = new GoodsImg();
                  goodsImg.setId(UUID.randomUUID().toString());
                  goodsImg.setGoodsId(goodsId);
                  goodsImg.setUrl(imgUrl);
                  goodsImg.setSort(i + 1);
                  goodsImgDao.insert(goodsImg);
                }
                else {
                  throw new RuntimeException("文件类型不合法,只能是 jpg、gif、png、jpeg 类型！");
                }
              }
            } else if(type.equals("add")) {
              GoodsImg goodsImg = new GoodsImg();
              goodsImg.setId(UUID.randomUUID().toString());
              goodsImg.setGoodsId(goodsId);
              goodsImg.setUrl(null);
              goodsImg.setSort(1);
              goodsImgDao.insert(goodsImg);
            }
      }*/
  }