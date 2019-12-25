package com.guli.edu.entity;

import lombok.Data;

/**
 * @author Jason
 * @create 2019-12-16-20:42
 */

@Data
public class QueryCourse {

    private String subjectParentId;

    private String subjectId;

    private String teacherId;

    private String title;
}
