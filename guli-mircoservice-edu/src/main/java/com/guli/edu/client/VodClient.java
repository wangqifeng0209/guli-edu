package com.guli.edu.client;

import com.guli.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jason
 * @create 2019-12-21-15:27
 */

@Component
@FeignClient("guli-vidservice")
public interface VodClient {

    /**
     * 远程调用微服务,删除单个视频
     * @param videoId
     * @return
     */
    @DeleteMapping("/video/deleteVideo/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    /**
     * 远程调用微服务,删除多个视频
     * @param ids
     * @return
     */
    @DeleteMapping("/video/deleteMoreVideos")
    R removeMoreVideos(@RequestParam("ids") List<String> ids);
}
