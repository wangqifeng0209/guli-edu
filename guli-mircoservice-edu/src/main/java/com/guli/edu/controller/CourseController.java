package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.common.R;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.FormCourse;
import com.guli.edu.entity.QueryCourse;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.service.CourseService;
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
 * @since 2019-12-15
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/updateCourse")
    public R updateCourse(@RequestBody FormCourse formCourse){

        return R.ok();
    }

    @PostMapping("/addOrUpdateCourse")
    public R addOrUpdateCourse(@RequestBody FormCourse formCourse) {

        String id = courseService.addOrUpdateCourse(formCourse);

        if (id != null){
            return R.ok().data("id",id);
        }else {
            return R.error();
        }

    }

    @GetMapping("/getCourse/{id}")
    public R getCourse(@PathVariable String id){

        FormCourse formCourse = courseService.getFormCourse(id);
        return R.ok().data("formCourse", formCourse);
    }

    /**
     * 普通的分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/pageList/{page}/{limit}")
    public R pageListCourse(@PathVariable Integer page, @PathVariable Integer limit) {
        Page<Course> coursePage = new Page<>(page,limit);
        IPage<Course> page1 = courseService.page(coursePage, null);

        return R.ok().data("total",page1.getTotal()).data("items", page1.getRecords())
                .data("current", page1.getCurrent()).data("pages", page1.getPages());
    }

    @PostMapping("/condPageList/{page}/{limit}")
    public R condPageListCourse(@PathVariable Integer page, @PathVariable Integer limit, @RequestBody QueryCourse queryCourse) {
        System.out.println("进controller了");
        IPage<Course> iPage = courseService.condPageList(page, limit, queryCourse);

        return R.ok().data("total",iPage.getTotal()).data("items", iPage.getRecords())
                .data("current", iPage.getCurrent()).data("pages", iPage.getPages());
    }

    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourseById(@PathVariable String id) {

        //同时删除课程，课程描述，章节，小节,视频
        courseService.removeCourse(id);


        return R.ok().message("删除成功");
    }


    @GetMapping("/getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id) {

        CourseInfoDto courseInfo = courseService.getCourseInfo(id);
        return R.ok().data("item", courseInfo);
    }

    @GetMapping("/updateCourseStatus/{id}")
    public R updateCourseStatus(@PathVariable String id) {

        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");

        boolean b = courseService.updateById(course);

        if (b){
            return R.ok();
        }else {
            return R.error();
        }

    }
}

