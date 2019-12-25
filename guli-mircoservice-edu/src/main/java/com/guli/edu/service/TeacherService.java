package com.guli.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.QueryTeacher;
import com.guli.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jason
 * @since 2019-12-08
 */
public interface TeacherService extends IService<Teacher> {


    IPage<Teacher> condPageList(Page<Teacher> teacherPage, QueryTeacher queryTeacher);

    Map<String, Object> pageListFront(Long page, Long limit);
}
