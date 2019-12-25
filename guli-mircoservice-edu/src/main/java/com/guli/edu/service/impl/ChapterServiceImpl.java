package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.guli.edu.entity.dto.ChapterDto;
import com.guli.edu.entity.dto.VideoDto;
import com.guli.edu.handler.EduException;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import org.apache.tomcat.util.security.Escape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-17
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoService videoService;


    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<ChapterDto> getChapterVideo(String courseId) {
        List<ChapterDto> list = new ArrayList<>();

        //1.根据courseId查出所有的章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(chapterQueryWrapper);


        for (Chapter chapter : chapters) {
            ChapterDto chapterDto = new ChapterDto();
            chapterDto.setId(chapter.getId());
            chapterDto.setTitle(chapter.getTitle());

            //2.根据chapter_id查出所有的小节
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("chapter_id", chapter.getId());

            List<Video> videos = videoService.list(videoQueryWrapper);

            List<VideoDto> videoDtos = new ArrayList<>();
            for (Video video : videos) {
                VideoDto videoDto = new VideoDto();
                videoDto.setId(video.getId());
                videoDto.setTitle(video.getTitle());
                videoDto.setVideoSourceId(video.getVideoSourceId());

                videoDtos.add(videoDto);
            }

            chapterDto.setChildren(videoDtos);

            list.add(chapterDto);

        }




        return list;
    }

    @Override
    public void removeChapter(String id) {

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);
        int count = videoService.count(videoQueryWrapper);

        if (count > 0){
            throw new EduException(20001,"章节下面还有小节，无法直接删除");
        }else {
            baseMapper.deleteById(id);
        }

    }
}
