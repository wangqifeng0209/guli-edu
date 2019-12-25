package com.atguigu.gulivideoservice.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.gulivideoservice.service.VideoService;
import com.atguigu.gulivideoservice.util.AliyunSdkUtil;
import com.atguigu.gulivideoservice.util.ConstantPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author Jason
 * @create 2019-12-20-14:03
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideo(MultipartFile file) {
        String videoId = null;

        try {
            String accessKeyId = ConstantPropertiesUtil.KEYID;
            String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return videoId;
    }

    @Override
    public void deleteVideo(String videoId) {

        try {
            DefaultAcsClient client = AliyunSdkUtil.initVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);

            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void deleteMoreVideos(List<String> ids) {
        System.out.println(ids);

        try {
            DefaultAcsClient client = AliyunSdkUtil.initVodClient(ConstantPropertiesUtil.KEYID, ConstantPropertiesUtil.KEYSECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            String videoIds = StringUtils.join(ids.toArray(),",");
            System.out.println("videoIds = " + videoIds);
            request.setVideoIds(videoIds);

            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
