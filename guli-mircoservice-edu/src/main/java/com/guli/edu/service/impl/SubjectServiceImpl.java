package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.SubjectNestedVo;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-13
 */

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public List<String> importSubject(MultipartFile file) {

        List<String> msg = new ArrayList<>();

        try {
            //1.创建输入流
            InputStream inputStream = file.getInputStream();

            //2.创建workbook
            Workbook workbook = new HSSFWorkbook(inputStream);

            //3.获取sheet
            Sheet sheet = workbook.getSheetAt(0);

            //4.遍历rows，获取到每一行的数据
            //最后一行的下标
            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);

                //判断row是否为空
                if (row != null){
                    //row不为空则获取cell，为空则跳过改行，继续执行下一行数据

                    //一级课程
                    Cell cell1 = row.getCell(0);

                    //一级课程为空，则跳出改行的循环，执行下一行
                    if (cell1 == null){
                        msg.add("第" + row + "行的一级分类为空！");
                        continue;
                    }

                    //一级课程的名字
                    String title1 = cell1.getStringCellValue();
                    Subject subject1 = this.isExistSubject1(title1);

                    //定义一个parentId
                    String parentId = null;

                    //判断一级课程是否已经存在，如果已经存在则不需要导入
                    if (subject1 == null){
                        //等于空，说明没有该课程，需要导入
                        Subject subject = new Subject();
                        subject.setParentId("0");
                        subject.setSort(10);
                        subject.setTitle(title1);

                        baseMapper.insert(subject);
                        parentId = subject.getId();
                    }else {
                        parentId = subject1.getId();
                    }


                    //二级课程
                    Cell cell2 = row.getCell(1);
                    if (cell2 == null){
                        msg.add("第" + i + "行的二级课程为空！");
                        continue;
                    }

                    String title2 = cell2.getStringCellValue();
                    //判断二级课程是否已经存在
                    Subject subject2 = this.isExistSubject2(title2, parentId);

                    if (subject2 == null){
                        Subject subject = new Subject();
                        subject.setTitle(title2);
                        subject.setSort(20);
                        subject.setParentId(parentId);

                        baseMapper.insert(subject);
                    }

                }

            }


            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return msg;
    }

    @Override
    public List<SubjectNestedVo> nestedList() {

        List<SubjectNestedVo> subjectNestedVos = new ArrayList<>();

        //先获取所有的一级课程类目
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<Subject> subjects = baseMapper.selectList(wrapper);

        //遍历所有的一级课程类目，构建父节点
        for (Subject subject : subjects) {

            //每次遍历构建一个父节点
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();

            String parentId = subject.getId();

            QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", parentId);

            List<Subject> subjectList = baseMapper.selectList(queryWrapper);

            //遍历二级课程，构建一级课程的children
            List<SubjectNestedVo> subjectNestedVoList = new ArrayList<>();

            for (Subject subject1 : subjectList) {

                SubjectNestedVo subjectNestedVoChild = new SubjectNestedVo();
                subjectNestedVoChild.setId(subject1.getId());
                subjectNestedVoChild.setTitle(subject1.getTitle());
                subjectNestedVoChild.setParentId(parentId);

                subjectNestedVoList.add(subjectNestedVoChild);

            }


            subjectNestedVo.setId(parentId);
            subjectNestedVo.setTitle(subject.getTitle());
            subjectNestedVo.setChildren(subjectNestedVoList);
            subjectNestedVo.setParentId("0");

            //把所有的父节点放到父节点集合中
            subjectNestedVos.add(subjectNestedVo);
        }

        return subjectNestedVos;
    }

    @Override
    public boolean saveSubject(Subject subject) {

        String parentId = subject.getParentId();
        String title = subject.getTitle();

        if (parentId == "0"){
            if (this.isExistSubject1(subject.getTitle()) == null){
                int insert = baseMapper.insert(subject);
                return insert>0;
            }else {
                return false;
            }
        }else {
            if (this.isExistSubject2(title,parentId) == null) {
                int insert = baseMapper.insert(subject);
                return insert>0;
            }else {
                return false;
            }
        }

    }

    /**
     * 判断二级课程是否已经存在
     * @param title
     * @param parentId
     * @return
     */
    public Subject isExistSubject2(String title, String parentId){

        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", parentId);
        Subject subject = baseMapper.selectOne(wrapper);
        return subject;
    }

    /**
     * 判断是否已经存在一级目录课程
     * @param title
     * @return
     */
    public Subject isExistSubject1(String title){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        Subject subject = baseMapper.selectOne(wrapper);
        return subject;
    }
}
