package com.guli.edu.controller;


import com.guli.edu.common.R;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.dto.ChapterDto;
import com.guli.edu.handler.ConstantPropertiesUtils;
import com.guli.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-17
 */
@CrossOrigin
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {

        List<ChapterDto> chapters = chapterService.getChapterVideo(courseId);
        return R.ok().data("items", chapters);
    }

    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){

        boolean save = chapterService.save(chapter);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }

    }

    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){

        boolean b = chapterService.updateById(chapter);
        if (b) {
            return R.ok();
        }else {
            return R.error();
        }

    }

    @DeleteMapping("/deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id) {

        chapterService.removeChapter(id);
        return R.ok();
    }

    @GetMapping("/getChapter/{id}")
    public R getChapterById(@PathVariable String id) {

        Chapter chapter = chapterService.getById(id);
        return R.ok().data("item", chapter);
    }
}

