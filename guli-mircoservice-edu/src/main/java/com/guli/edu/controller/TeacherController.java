package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.common.R;
import com.guli.edu.entity.QueryTeacher;
import com.guli.edu.entity.Teacher;
import com.guli.edu.handler.EduException;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //{"code":20000,"data":{"token":"admin"}}
    @PostMapping("/login")
    public R login() {

        return R.ok().data("token", "admin");
    }

    //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
    @GetMapping("/info")
    public R info() {

        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @ApiOperation("获取所有的讲师")
    @GetMapping("/list")
    public R getAllTeachers() {
        List<Teacher> teachers = teacherService.list(null);

        return R.ok().data("teachers",teachers).message("获取讲师信息成功");
    }

    @ApiOperation("根据ID删除指定讲师")
    @DeleteMapping("/delete/{id}")
    public R removeById(@PathVariable String id) {
        boolean isDeleted = teacherService.removeById(id);
        return R.ok().message("删除讲师成功");
    }

    @ApiOperation("简单的分页查询")
    @GetMapping("pageList/{page}/{limit}")
    public R pageList(@PathVariable Long page,@PathVariable Long limit) {
        Page<Teacher> pageTeacher = new Page<>(page,limit);
        IPage<Teacher> iPage = teacherService.page(pageTeacher, null);
        List<Teacher> teachers = iPage.getRecords();
        long total = iPage.getTotal();

        return R.ok().data("total", total).data("items", teachers);
    }

    @ApiOperation("多条件组合的分页查询")
    @PostMapping("condPageList/{page}/{limit}")
    public R moreConditionPageList(@PathVariable Long page, @PathVariable Long limit,
                                   @RequestBody(required = false) QueryTeacher queryTeacher) {
        Page<Teacher> teacherPage = new Page<>(page,limit);

        IPage<Teacher> iPage = teacherService.condPageList(teacherPage,queryTeacher);
        List<Teacher> teachers = iPage.getRecords();
        long total = iPage.getTotal();
        long current = iPage.getCurrent();
        return R.ok().data("total", total).data("items", teachers).data("current", current);
    }

    @ApiOperation("新增一位讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody(required = false) Teacher teacher) {
        boolean save = teacherService.save(teacher);

        if (save) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    @ApiOperation("根据ID修改讲师信息")
    @PostMapping("/update")
    public R updateById(@RequestBody(required = false) Teacher teacher) {

        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok();
        }

        return R.error();
    }

    @ApiOperation("根据ID获取一位讲师的信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);

        System.out.println("访问了。。。");
        return R.ok().data("item", teacher);
    }
}

