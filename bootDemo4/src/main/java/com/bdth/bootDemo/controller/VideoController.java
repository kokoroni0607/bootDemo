package com.bdth.bootDemo.controller;

import com.bdth.bootDemo.entity.Video;
import com.bdth.bootDemo.service.VideoService;
import com.bdth.bootDemo.util.ReponseUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author weiming.zhu
 * @date 2019/1/7 16:37
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VideoService videoService;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping("/insert")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {
        logger.debug("======上传文件======");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        String url = "";
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");

        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: " + fileName + "\n");

        //加个时间戳，尽量避免文件名称重复
        String path = "E:/fileUpload/" + fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径" + path + "\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8888/images/" + fileName;//本地运行项目
            int jieguo = videoService.insert(fileName, url);
            System.out.print("插入结果" + jieguo + "\n");
            System.out.print("保存的完整url====" + "\n");

        } catch (IOException e) {
            return "上传失败";
        }

        return "上传成功,文件url==" + url;
    }

    /**
     * 获取视频列表
     *
     * @param response
     */
    @RequestMapping("/list")
    public void hello(HttpServletResponse response) {
        logger.debug("====== list success ======");
        Object temp = redisTemplate.opsForValue().get("videoList");
        List<Video> result = (List<Video>)(temp);
        if (result == null || result.size() <= 0) {
            result = videoService.getVideos();
            if (result != null && result.size() > 0) {
                setVideo("videoList", result);
            } else {
                result = null;
            }
        }
        ReponseUtil.response(response, "200", "获取成功", result);
    }

    /**
     * String类型缓存保存
     *
     * @param key   键
     * @param value 值
     * @return true：成功；false：失败
     */
    public boolean setVideo(String key, List<Video> value) {
        try {
            if (StringUtils.isNotEmpty(key) && null != value) {
                redisTemplate.opsForValue().set(key, value);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
