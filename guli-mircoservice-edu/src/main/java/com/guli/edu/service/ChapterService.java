package com.guli.edu.service;

import com.guli.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.dto.ChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jason
 * @since 2019-12-17
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程Id删除章节
     * @param id
     */
    void removeByCourseId(String id);


    List<ChapterDto> getChapterVideo(String courseId);

    void removeChapter(String id);
}
