package com.guli.edu.mapper;

import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.dto.CourseWebVo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author jason
 * @since 2019-12-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    CourseInfoDto getCourseInfo(@RequestParam("id") String id);

    CourseWebVo getCourseWebVo(@RequestParam("id") String id);
}
