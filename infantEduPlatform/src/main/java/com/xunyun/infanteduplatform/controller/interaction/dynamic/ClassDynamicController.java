package com.xunyun.infanteduplatform.controller.interaction.dynamic;

import com.xunyun.infanteduplatform.domain.interaction.*;
import com.xunyun.infanteduplatform.service.AppInfoService;
import com.xunyun.infanteduplatform.service.BulletinService;
import com.xunyun.infanteduplatform.service.DynamicService;
import com.xunyun.infanteduplatform.service.ImageService;
import com.xunyun.infanteduplatform.utils.DateUtils;
import com.xunyun.infanteduplatform.utils.InteractionUtils;
import com.xunyun.infanteduplatform.utils.ReadInputStreamUtils;
import com.xunyun.infanteduplatform.utils.ValueChangeUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("dynamic")
public class ClassDynamicController {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping("/test")
    public ModelAndView Test() {
        ModelAndView mView = new ModelAndView("pages/headimage/test");
        return mView;
    }

    /**
     * 查询班级动态列表
     *
     * @param request
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/queryMessageList")
    public Map<String, Object> queryMessageList(HttpServletRequest request) throws ParseException, IOException {

        // 字节流
        BufferedReader reader = request.getReader();

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
        //机构代码
        Integer organizationId = ValueChangeUtils.changeValue(jsonobject.get("organizationId"), null);
        //班级id
        Integer classId = ValueChangeUtils.changeValue(jsonobject.get("classId"), null);
        // 公告Id(默认为0)
        Integer bulletinId = ValueChangeUtils.changeValue(jsonobject.get("classId"), 0);
        // 显示数(默认为10)
        Integer count = ValueChangeUtils.changeValue(jsonobject.get("classId"), 10);
        // 数据方向(默认为0)
        Integer direction = ValueChangeUtils.changeValue(jsonobject.get("classId"), 0);

        HashMap<String, Object> map = new HashMap<>();

        if (appCode == null || "".equals(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码为空，查询失败!");
            map.put("data", "");
        } else if (organizationId == null) {
            map.put("state", "error");
            map.put("message", "机构代码为空，传入参数有误！");
            map.put("data", "");

        } else if (classId == null) {
            map.put("state", "error");
            map.put("message", "班级id为空，传入参数有误！");
            map.put("data", "");

        } else if (!appInfoService.findByAppCode(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码未开启，查询失败!");
            map.put("data", "");
        } else {
            // 声明对象
            Bulletin item = new Bulletin();
            // 条数
            item.setCount(count);
            // 数据方向
            item.setDirection(direction);
            // 机构代码
            item.setOrganizationId(organizationId);
            // 公告Id
            item.setBulletinId(bulletinId);
            // 公告Id
            item.setClassId(classId);
            // 类型
            item.setBulletinType(3);
            // 获取数据
            List<SelectDynamic> listData = bulletinService.queryBulletinPage(item);
            List<ClassDynamicModel> cdmList = new ArrayList<>();

            byte[] photo;

            for (SelectDynamic sd : listData) {

                bulletinId = sd.getBulletinId();

                //列表Model类
                ClassDynamicModel cdm = new ClassDynamicModel();
                cdm.setBulletinContent(sd.getBulletincontent());//1
                cdm.setBulletinId(bulletinId);//2
                cdm.setCreationTime(DateUtils.getTimeString(sd.getCreationtime()));//3
                cdm.setName(sd.getName());//4

                String bulletinphotourl = sd.getPhotourl();
                try {
                    photo = InteractionUtils.getPhoto(bulletinphotourl);
                    cdm.setPhoto(photo);//5
                } catch (IOException | SQLException e1) {
                    e1.printStackTrace();
                }
                // 点赞头像列表
                Dynamic dynamic = new Dynamic();
                dynamic.setModuleId(bulletinId);
                dynamic.setDynamicType(3);
                List<Dynamic> listDynamic = bulletinService.queryPhotoList(dynamic);
                cdm.setPraiseCount(listDynamic.size());//6
                List<byte[]> praisePhotoList = new ArrayList<>();
                for (Dynamic d : listDynamic) {
                    String photourl = d.getDynamicPhotoUrl();
                    try {
                        byte[] dynamicPhoto = InteractionUtils.getPhoto(photourl);
                        praisePhotoList.add(dynamicPhoto);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                cdm.setPraisePhotoList(praisePhotoList);//7
                // 回复列表
                Replay replay = new Replay();
                replay.setModuleId(bulletinId);
                List<Replay> listReplay = bulletinService.queryReplayList(replay);
                cdm.setReplayCount(listReplay.size());//8
                List<ReplayModel> listRM = new ArrayList<>();
                for (Replay re : listReplay) {
                    ReplayModel rModel = new ReplayModel();
                    rModel.setName(re.getName());
                    rModel.setReplayContent(re.getReplayContent());
                    rModel.setReplayTime(DateUtils.getTimeString(re.getReplayTime()));
                    listRM.add(rModel);
                }
                cdm.setReplayList(listRM);//9
                cdmList.add(cdm);
            }
            //  状态
            map.put("state", "success");
            // 提示信息
            map.put("message", "");
            // 数据列表
            map.put("data", cdmList);
        }
        return map;
    }

    /**
     * 插入班级动态
     *
     * @param req
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/insertMessage")
    public Map<String, Object> insertMessage(HttpServletRequest req) throws Exception {

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
        Integer organizationId = ValueChangeUtils.changeValue(jsonobject.get("organizationId"), null);
        Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
        Integer classId = ValueChangeUtils.changeValue(jsonobject.get("classId"), null);
        String bulletinContent = (String) jsonobject.get("bulletinContent");
        // 图片内容
        List<Blob> imageContent = (List<Blob>) jsonobject.get("imageContent");

        HashMap<String, Object> map = new HashMap<>();

        if (appCode == null || "".equals(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码为空，无权插入班级动态!");
            map.put("data", "");
        } else if (organizationId == null) {
            map.put("state", "error");
            map.put("message", "机构代码参数错误!");
            map.put("data", "");
        } else if ("".equals(bulletinContent) || bulletinContent == null) {
            map.put("state", "error");
            map.put("message", "动态内容参数错误!");
            map.put("data", "");
        } else if (userId == null) {
            map.put("state", "error");
            map.put("message", "用户id参数错误!");
            map.put("data", "");
        } else if (!appInfoService.findByAppCode(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码未开启，无权插入班级动态!");
            map.put("data", "");
        } else {
            Bulletin bulletin = new Bulletin();
            bulletin.setOrganizationId(organizationId);
            bulletin.setBulletinContent(bulletinContent);
            bulletin.setCreatedBy(String.valueOf(userId));
            bulletin.setCreationTime(new Date());
            bulletin.setBulletinType(3);
            bulletin.setClassId(classId);
            bulletin.setTitle("");
            bulletin.setLastUpdatedBy(String.valueOf(userId));
            bulletin.setLastUpdateTime(new Date());
            //插入班级动态
            bulletinService.insertMessage(bulletin);
            int bulletinId = bulletin.getBulletinId();
            try {
                for (Blob item : imageContent) {
                    // 声明对象
                    Image image = new Image();
                    // TODO :文件流获取
                    File file = InteractionUtils.blob2File(item);
                    // 图片名称
                    String fileName = file.getName();
                    // 名称（没有扩展名）
                    String name = fileName.substring(0, fileName.lastIndexOf('.'));
                    // 扩展名
                    String extension = fileName
                            .substring(fileName.lastIndexOf('.') + 1);
                    // 获取图片内容
                    image.setImageContent(item);
                    // 机构Id
                    image.setOrganizationId(organizationId);
                    // 文件名称
                    image.setImageName(name);
                    // 大小
                    image.setImageSize(file.length());
                    // 扩展名
                    image.setExtension(extension);
                    // 创建人
                    image.setCreatedBy(userId.toString());
                    // 创建时间
                    image.setCreationTime(new Date());
                    // 最终修改人
                    image.setLastUpdatedBy(userId.toString());
                    // 最终修改时间
                    image.setLastUpdateTime(new Date());
                    // 添加图片表
                    imageService.addImageList(image);

                    // 返回添加图片的图片Id
                    Integer imageId = image.getImageId();
                    // 声明对象
                    ImageRelation imageRelation = new ImageRelation();
                    // 模块Id
                    imageRelation.setModuleId(bulletinId);
                    // 图片Id
                    imageRelation.setImageId(imageId);
                    // 机构Id
                    imageRelation.setOrganizationId(organizationId);
                    // 添加图片关联表
                    imageService.addImageRelation(imageRelation);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            map.put("state", "success");
            map.put("message", "插入数据成功!");
            map.put("data", "");

        }
        return map;

    }

    /**
     * 插入点赞信息
     *
     * @param req
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/insertPraise")
    public Map<String, Object> insertPraise(HttpServletRequest req) throws IOException {

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
        Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
        Integer moduleId = ValueChangeUtils.changeValue(jsonobject.get("moduleId"), null);

        HashMap<String, Object> map = new HashMap<>();

        if (appCode == null || "".equals(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码为空，无权插入点赞信息!");
            map.put("data", "");
        } else if (userId == null) {
            map.put("state", "error");
            map.put("message", "用户id有误!");
            map.put("data", "");
        } else if (moduleId == null) {
            map.put("state", "error");
            map.put("message", "模块id有误!");
            map.put("data", "");
        } else if (!appInfoService.findByAppCode(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码未开启，无权插入点赞信息!");
            map.put("data", "");
        } else {
            Dynamic dynamic = new Dynamic();
            dynamic.setModuleId(moduleId);
            dynamic.setUserId(userId);
            dynamic.setDynamicType(3);
            int count = dynamicService.queryDynamic(dynamic);

            if (count != 0) {
                map.put("state", "success");
                map.put("message", "已点赞成功!");
                map.put("data", "");
            } else {
                dynamic.setPraiseTime(new Date());
                dynamic.setDynamicType(3);
                bulletinService.insertDynamic(dynamic);

                map.put("state", "success");
                map.put("message", "点赞成功!");
                map.put("data", "");
            }
        }
        return map;
    }

    /**
     * 插入回复信息
     *
     * @param req
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/insertReply")
    public Map<String, Object> insertReply(HttpServletRequest req) throws IOException {

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
        Integer userId = ValueChangeUtils.changeValue(jsonobject.get("userId"), null);
        Integer moduleId = ValueChangeUtils.changeValue(jsonobject.get("moduleId"), null);
        Integer targetId = ValueChangeUtils.changeValue(jsonobject.get("targetId"), null);
        String content = (String) jsonobject.get("content");

        HashMap<String, Object> map = new HashMap<>();

        if (appCode == null || "".equals(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码为空，无权插入回复信息!");
            map.put("data", "");
        } else if (userId == null) {
            map.put("state", "error");
            map.put("message", "用户id有误!");
            map.put("data", "");
        } else if (moduleId == null) {
            map.put("state", "error");
            map.put("message", "模块id有误!");
            map.put("data", "");
        } else if (targetId == 0) {
            map.put("state", "error");
            map.put("message", "回复对象id有误!");
            map.put("data", "");
        } else if ("".equals(content) || content == null) {
            map.put("state", "error");
            map.put("message", "内容为空，插入数据失败!");
            map.put("data", "");
        } else if (!appInfoService.findByAppCode(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码未开启，无权插入回复信息!");
            map.put("data", "");
        } else {
            Replay replay = new Replay();
            replay.setModuleId(moduleId);
            replay.setTargetId(targetId);
            replay.setUserId(userId);
            replay.setReplayContent(content);
            replay.setReplayTime(new Date());
            bulletinService.insertReplay(replay);

            map.put("state", "success");
            map.put("message", "回复成功!");
            map.put("data", "");
        }
        return map;
    }

    /**
     * 查询班级动态详情
     *
     * @param req
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/queryMessageDetail")
    public Map<String, Object> queryMessageDetail(HttpServletRequest req) throws Exception {

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
        Integer bulletinId = ValueChangeUtils.changeValue(jsonobject.get("bulletinId"), null);

        HashMap<String, Object> map = new HashMap<>();

        if (appCode == null || "".equals(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码为空，无权查询动态详情!");
            map.put("data", "");
        } else if (bulletinId == null) {
            map.put("state", "error");
            map.put("message", "通知公告id有误!");
            map.put("data", "");
        } else if (!appInfoService.findByAppCode(appCode)) {
            map.put("state", "error");
            map.put("message", "应用系统代码未开启，无权查询动态详情!");
            map.put("data", "");
        } else {
            //动态详情model类
            DynamicDetail dd = new DynamicDetail();

            Bulletin bulletin = new Bulletin();
            bulletin.setBulletinId(bulletinId);
            //班级动态详情
            SelectDynamic sd;
            sd = bulletinService.queryDetail(bulletin);

            dd.setBulletinContent(sd.getBulletincontent());//1
            dd.setCreationTime(DateUtils.getTimeString(sd.getCreationtime()));//2
            dd.setName(sd.getName());//3
            //动态发布者头像
            dd.setPhoto(InteractionUtils.getPhoto(sd.getPhotourl()));//4
            //动态图片列表
            List<Image> listImages = imageService.queryImageList(bulletinId);
            List<ImageModel> list = new ArrayList<>();
            // 查询图片内容
            for (Image image : listImages) {

                if (image.getStrImageContent() != null) {
                    // 创建ImageModel类实例
                    ImageModel imageModel = new ImageModel();
                    // 创建InputStream类实例
                    InputStream inStream = new ByteArrayInputStream(image.getStrImageContent().getBytes());
                    // 读取输入流
                    byte data[] = ReadInputStreamUtils.readInputStream(inStream);
                    inStream.read(data);
                    // 关闭输入流
                    inStream.close();
                    // 获取图片内容
                    imageModel.setImageContent(data);
                    list.add(imageModel);
                }
            }
            dd.setPictureList(list);//5
            // 点赞头像列表
            Dynamic dynamic = new Dynamic();
            dynamic.setModuleId(bulletinId);
            List<Dynamic> listDynamic = bulletinService.queryPhotoList(dynamic);
            dd.setPraiseCount(listDynamic.size());//6
            List<byte[]> praisePhotoList = new ArrayList<>();
            for (Dynamic d : listDynamic) {
                String photourl = d.getDynamicPhotoUrl();
                try {
                    byte[] dynamicPhoto = InteractionUtils.getPhoto(photourl);
                    praisePhotoList.add(dynamicPhoto);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
            dd.setPraisePhotoList(praisePhotoList);//7
            // 回复列表
            Replay replay = new Replay();
            replay.setModuleId(bulletinId);
            List<Replay> listReplay = bulletinService.queryReplayList(replay);
            dd.setReplayCount(listReplay.size());//8
            List<ReplayModel> listRM = new ArrayList<>();
            for (Replay re : listReplay) {
                ReplayModel rModel = new ReplayModel();
                rModel.setName(re.getName());
                rModel.setReplayContent(re.getReplayContent());
                rModel.setReplayTime(DateUtils.getTimeString(re.getReplayTime()));
                listRM.add(rModel);
            }
            dd.setReplayList(listRM);//9
            // 状态
            map.put("state", "success");
            // 提示信息
            map.put("message", "");
            // 返回数据
            map.put("data", dd);
        }
        return map;
    }

}
