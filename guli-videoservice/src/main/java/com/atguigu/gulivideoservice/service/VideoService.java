package com.atguigu.gulivideoservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Jason
 * @create 2019-12-20-14:02
 */


public interface VideoService {
    String uploadVideo(MultipartFile file);

    void deleteVideo(String videoId);

    void deleteMoreVideos(List<String> ids);
}
