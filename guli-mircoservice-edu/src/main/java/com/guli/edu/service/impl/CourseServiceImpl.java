package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.*;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.dto.CourseWebVo;
import com.guli.edu.handler.ConstantPropertiesUtils;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseDescriptionService courseDescriptionService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    VideoService videoService;

    @Autowired
    ChapterService chapterService;

    @Override
    public String addOrUpdateCourse(FormCourse formCourse) {


        String id = null;

        Course course = new Course();
        course.setTitle(formCourse.getTitle());
        course.setLessonNum(formCourse.getLessonNum());
        course.setPrice(formCourse.getPrice());
        course.setTeacherId(formCourse.getTeacherId());
        course.setSubjectId(formCourse.getSubjectId());
        course.setCover(formCourse.getCover());

        //如果formCourse存在id值，那么执行更新，不存在则执行添加
        if (StringUtils.isEmpty(formCourse.getId())){
            System.out.println("前端发送的id值为" + formCourse.getId());
            System.out.println("执行了添加操作");
            baseMapper.insert(course);
            id = course.getId();

            CourseDescription courseDescription = new CourseDescription();
            courseDescription.setId(id);
            courseDescription.setDescription(formCourse.getDescription());

            courseDescriptionService.save(courseDescription);

        }else {
            System.out.println("前端发送的id值为" + formCourse.getId());
            System.out.println("执行了更新操作");
            id = formCourse.getId();
            course.setId(id);
            baseMapper.updateById(course);

            CourseDescription courseDescription = new CourseDescription();
            courseDescription.setId(id);
            courseDescription.setDescription(formCourse.getDescription());

            courseDescriptionService.updateById(courseDescription);
        }

        return id;
    }

    @Override
    public FormCourse getFormCourse(String id) {
        Course course = baseMapper.selectById(id);
        CourseDescription courseDescription = courseDescriptionService.getById(id);

        FormCourse formCourse = new FormCourse();
        formCourse.setTitle(course.getTitle());
        formCourse.setLessonNum(formCourse.getLessonNum());
        formCourse.setPrice(course.getPrice());
        formCourse.setSubjectId(formCourse.getSubjectId());
        formCourse.setTeacherId(formCourse.getTeacherId());
        formCourse.setDescription(courseDescription.getDescription());
        formCourse.setId(course.getId());
        formCourse.setCover(course.getCover());

        Subject subject = subjectService.getById(course.getSubjectId());
        formCourse.setSubjectParentId(subject.getParentId());

        return formCourse;
    }

    @Override
    public IPage<Course> condPageList(Integer page, Integer limit, QueryCourse queryCourse) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        System.out.println(queryCourse);

        if (!StringUtils.isEmpty(queryCourse.getTitle())) {
            wrapper.like("title", queryCourse.getTitle());
        }

        if (!StringUtils.isEmpty(queryCourse.getTeacherId())) {
            wrapper.eq("teacher_id", queryCourse.getTeacherId());
        }

        if (!StringUtils.isEmpty(queryCourse.getSubjectId())) {
            wrapper.eq("subject_id", queryCourse.getSubjectId());
        }else {
            if (!StringUtils.isEmpty(queryCourse.getSubjectParentId())){
                String parentId = queryCourse.getSubjectParentId();
                QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
                subjectQueryWrapper.eq("parent_id", parentId);

                List<Subject> subjects = subjectService.list(subjectQueryWrapper);
                List<String> list = new ArrayList<>();
                for (Subject subject : subjects) {
                    list.add(subject.getId());
                }
                wrapper.in("subject_id",list);
            }
        }

        Page<Course> coursePage = new Page<>(page, limit);
        IPage<Course> courseIPage = baseMapper.selectPage(coursePage, wrapper);
        return courseIPage;
    }

    @Override
    public void removeCourse(String id) {

        //0.删除所有小节下面的

        //1.删除小节
        videoService.removeByCourseId(id);

        //2.删除章节
        chapterService.removeByCourseId(id);

        //3.删除课程描述
        courseDescriptionService.removeById(id);

        //4.删除课程
        baseMapper.deleteById(id);
    }

    @Override
    public CourseInfoDto getCourseInfo(String id) {
        return baseMapper.getCourseInfo(id);
    }

    /**
     * 前台分页课程和课程分类
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Map<String, Object> frontPageList(Long page, Long limit) {
        Page<Course> coursePage = new Page<>(page, limit);
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

//        if (!StringUtils.isEmpty(subjectId)){
//            System.out.println(subjectId);
//            System.out.println("===================执行了吗============================？");
//            wrapper.eq("subject_id", subjectId);
//        }
        baseMapper.selectPage(coursePage, wrapper);

        List<Subject> subjects = subjectService.list(null);

        Map<String, Object> map = new HashMap<>();
        map.put("current", coursePage.getCurrent());
        map.put("courses", coursePage.getRecords());
        map.put("size", coursePage.getSize());
        map.put("total", coursePage.getTotal());
        map.put("pages", coursePage.getPages());
        map.put("hasNext", coursePage.hasNext());
        map.put("hasPrevious", coursePage.hasPrevious());
        map.put("subjects", subjects);

        return map;
    }


    @Override
    public CourseWebVo selectInfoWebById(String id) {
        CourseWebVo courseWebVo = baseMapper.getCourseWebVo(id);
        return courseWebVo;
    }


}
