package com.guli.edu.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author Jason
 * @create 2019-12-14-14:31
 */

@ApiModel("用户创建前台属性课程列表的课程节点")
@Data
public class SubjectNestedVo {

    private String id;

    private String title;

    private List<SubjectNestedVo> children;

    private String parentId;


}
