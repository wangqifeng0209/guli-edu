package com.guli.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.FormCourse;
import com.guli.edu.entity.QueryCourse;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.dto.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jason
 * @since 2019-12-15
 */
public interface CourseService extends IService<Course> {

    String addOrUpdateCourse(FormCourse formCourse);

    FormCourse getFormCourse(String id);

    IPage<Course> condPageList(Integer page, Integer limit, QueryCourse queryCourse);

    void removeCourse(String id);

    CourseInfoDto getCourseInfo(String id);

    Map<String, Object> frontPageList(Long page, Long limit);

    CourseWebVo selectInfoWebById(String id);
}
