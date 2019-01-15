package com.bdth.bootDemo.entity;

import java.io.Serializable;

/**
 * @author weiming.zhu
 * @date 2019/1/7 16:29
 */
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    // 名称
    private String VideoName;
    // 地址
    private String VideoUrl;

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }
}
