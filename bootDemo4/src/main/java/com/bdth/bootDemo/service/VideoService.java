package com.bdth.bootDemo.service;

import com.bdth.bootDemo.dao.VideoMapper;
import com.bdth.bootDemo.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weiming.zhu
 * @date 2019/1/7 16:36
 */
@Service
@Component
public class VideoService {
    @Autowired
    private VideoMapper videoMapper;

    public int insert(String VideoName, String VideoUrl){
        return videoMapper.insert(VideoName,VideoUrl);
    }

    public List<Video> getVideos() {
        return videoMapper.getVideos();
    }
}
