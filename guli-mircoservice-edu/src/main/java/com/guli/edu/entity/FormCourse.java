package com.guli.edu.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason
 * @create 2019-12-15-13:31
 */

@Data
public class FormCourse {

    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String cover;

    private String title;

    private Integer lessonNum;

    private String description;

    private BigDecimal price;
}
