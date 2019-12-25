package com.guli.edu.service;

import com.guli.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jason
 * @since 2019-12-13
 */
public interface SubjectService extends IService<Subject> {

    List<String> importSubject(MultipartFile file);

    List<SubjectNestedVo> nestedList();

    boolean saveSubject(Subject subject);
}
