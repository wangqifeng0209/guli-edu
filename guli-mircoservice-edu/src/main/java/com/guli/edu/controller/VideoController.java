package com.guli.edu.controller;


import com.guli.edu.client.VodClient;
import com.guli.edu.common.R;
import com.guli.edu.entity.Video;
import com.guli.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-17
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Autowired
    VodClient vodClient;

    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){

        //同时删除课程视频
        String videoSourceId = videoService.getById(id).getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }

        boolean b = videoService.removeById(id);

        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error();
        }


    }

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video) {
        boolean save = videoService.save(video);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }

    }

    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody Video video) {

        boolean b = videoService.updateById(video);

        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/getVideo/{id}")
    public R getVideo(@PathVariable String id) {

        Video video = videoService.getById(id);
        return R.ok().data("item", video);
    }
}

