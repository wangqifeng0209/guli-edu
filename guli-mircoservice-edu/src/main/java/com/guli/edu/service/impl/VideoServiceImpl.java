package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.client.VodClient;
import com.guli.edu.entity.Video;
import com.guli.edu.mapper.VideoMapper;
import com.guli.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-17
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    VodClient vodClient;

    @Override
    public void removeByCourseId(String id) {




        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);

        List<Video> videos = baseMapper.selectList(wrapper);
        List<String> sourceIds = new ArrayList<>();
        for (Video video : videos) {
            sourceIds.add(video.getVideoSourceId());
        }

        //删除所有的小节之前，先删除掉所有小节中的视频
        vodClient.removeMoreVideos(sourceIds);

        baseMapper.delete(wrapper);
    }
}
