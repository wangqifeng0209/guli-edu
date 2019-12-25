package com.guli.edu.controller.front;

import com.guli.edu.common.R;
import com.guli.edu.entity.dto.ChapterDto;
import com.guli.edu.entity.dto.CourseWebVo;
import com.guli.edu.service.ChapterService;
import com.guli.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @create 2019-12-24-14:14
 */

@RestController
@CrossOrigin
@RequestMapping("/edu/frontCourse")
public class FrontCourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ChapterService chapterService;

    @GetMapping("/pageListCourse/{page}/{limit}")
    public R pageListCourse(@PathVariable Long page,
                            @PathVariable Long limit){

        Map<String,Object> map = courseService.frontPageList(page, limit);
        return R.ok().data(map);
    }

    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id){

        //获取所有课程相关的信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(id);

        //获取当前课程的章节信息
        List<ChapterDto> chapterVideo = chapterService.getChapterVideo(id);

        return R.ok().data("courseInfo", courseWebVo).data("chapter",chapterVideo);
    }
}
