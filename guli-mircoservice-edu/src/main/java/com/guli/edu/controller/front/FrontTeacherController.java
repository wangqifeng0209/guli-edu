package com.guli.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.common.R;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.Teacher;
import com.guli.edu.service.CourseService;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @create 2019-12-23-19:00
 */

@RequestMapping("/edu/frontTeacher")
@CrossOrigin
@RestController
@ApiModel("前台讲师接口")
public class FrontTeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    /**
     * 讲师列表分页显示
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/pageList/{page}/{limit}")
    public R pageListFrontTeacher(@PathVariable Long page,
                                  @PathVariable Long limit){

        Map<String, Object> map = teacherService.pageListFront(page, limit);
        return R.ok().data(map);
    }

    @GetMapping("/getTeacherAndCourse/{id}")
    public R getTeacherAndCourse(@PathVariable String id){

        //先获取讲师信息
        Teacher teacher = teacherService.getById(id);

        //再获取该讲师的所有课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        List<Course> courses = courseService.list(wrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("teacher", teacher);
        map.put("courses", courses);
        return R.ok().data(map);
    }
}
