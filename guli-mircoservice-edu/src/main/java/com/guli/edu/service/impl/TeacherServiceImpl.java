package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.QueryTeacher;
import com.guli.edu.entity.Teacher;
import com.guli.edu.mapper.TeacherMapper;
import com.guli.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-08
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {



    @Override
    public IPage<Teacher> condPageList(Page<Teacher> teacherPage, QueryTeacher queryTeacher) {

        if (queryTeacher == null) {
            IPage<Teacher> iPage = baseMapper.selectPage(teacherPage, null);
            return iPage;
        }

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq(queryTeacher.getLevel() != null,"level", queryTeacher.getLevel())
                .like(queryTeacher.getName() != null,"name", queryTeacher.getName())
                .ge(queryTeacher.getGmtCreateStart() != null,"gmt_create", queryTeacher.getGmtCreateStart())
                .le(queryTeacher.getGmtCreateEnd() != null,"gmt_create",queryTeacher.getGmtCreateEnd());
        IPage<Teacher> iPage = baseMapper.selectPage(teacherPage, wrapper);
        return iPage;
    }

    @Override
    public Map<String, Object> pageListFront(Long page, Long limit) {

        Page<Teacher> teacherPage = new Page<>(page, limit);
        IPage<Teacher> iPage = baseMapper.selectPage(teacherPage, null);

        //当前页数据，总记录数，当前页码，总共几页，当前页记录数
        List<Teacher> records = iPage.getRecords();
        long total = iPage.getTotal();
        long current = iPage.getCurrent();
        long pages = iPage.getPages();
        long size = iPage.getSize();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();

        Map<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("current", current);
        map.put("total", total);
        map.put("pages", pages);
        map.put("size", size);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
