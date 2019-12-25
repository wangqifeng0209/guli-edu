package com.atguigu.gulivideoservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.gulivideoservice.service.VideoService;
import com.atguigu.gulivideoservice.util.AliyunSdkUtil;
import com.atguigu.gulivideoservice.util.ConstantPropertiesUtil;
import com.guli.edu.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.GET;
import java.util.List;

/**
 * @author Jason
 * @create 2019-12-20-13:54
 */

@RestController
@CrossOrigin
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/getPlayAuth/{vid}")
    public R getPlayAuth(@PathVariable String vid) throws ClientException {

        //初始化
        DefaultAcsClient client = AliyunSdkUtil.initVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(vid);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth", playAuth);
    }

    @PostMapping("/uploadVideo")
    public R uploadAliyunVideo(MultipartFile file){

        String videoId = videoService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("/deleteVideo/{videoId}")
    public R deleteAliyunVideo(@PathVariable String videoId){

        videoService.deleteVideo(videoId);
        return R.ok().message("删除成功");
    }

    @DeleteMapping("/deleteMoreVideos")
    public R deleteMoreVideos(@RequestParam("ids") List<String> ids){

        videoService.deleteMoreVideos(ids);
        return R.ok();
    }
}
