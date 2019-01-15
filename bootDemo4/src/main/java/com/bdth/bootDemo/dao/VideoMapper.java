package com.bdth.bootDemo.dao;

import com.bdth.bootDemo.entity.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weiming.zhu
 * @date 2019/1/7 16:36
 */
@Mapper
@Component
public interface VideoMapper {

    @Insert({"INSERT INTO VIDEO(VideoName,VideoUrl) VALUES(#{VideoName},#{VideoUrl})"})
    public int insert(@Param("VideoName") String VideoName,@Param("VideoUrl") String VideoUrl);

    @Select({"SELECT VideoName,VideoUrl FROM VIDEO"})
    List<Video> getVideos();
}
