package com.guli.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.common.R;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.SubjectNestedVo;
import com.guli.edu.service.SubjectService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-13
 */
@ApiModel("课程科目接口")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/getSubject/{id}")
    public R getSubjectById(@PathVariable String id) {
        Subject subject = subjectService.getById(id);

        return R.ok().data("item", subject);
    }

    @GetMapping("/subSubjectList/{parentId}")
    public R getSubSubjectList(@PathVariable String parentId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Subject> subjects = subjectService.list(wrapper);

        return R.ok().data("items", subjects);
    }

    @PostMapping("/add")
    public R addSubject(@RequestBody Subject subject) {

        boolean save = subjectService.saveSubject(subject);

        if (save){
            return R.ok().message("添加课程成功");
        }else {
            return R.error().message("添加课程失败");
        }

    }

    @DeleteMapping("/delete/{id}")
    public R removeSubjectById(@PathVariable String id) {

        boolean b = subjectService.removeById(id);

        if (b) {
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }

    }

    @GetMapping("/nestedList")
    public R nestedList() {

        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();

        return R.ok().data("items", subjectNestedVoList);
    }


    @PostMapping("/import")
    public R importSubject(@RequestParam("file") MultipartFile file) {

        List<String> msg = subjectService.importSubject(file);

        if (msg.size() == 0) {
            return R.ok();
        } else {
            return R.error().data("improtResult",msg);
        }

    }
}

