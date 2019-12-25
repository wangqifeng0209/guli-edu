package com.guli.edu.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason
 * @create 2019-12-19-13:54
 */

@ApiModel("多表查询course信息的封装")
@Data
public class CourseInfoDto {

    private String id;

    private String title;

    private BigDecimal price;

    private String cover;

    private String description;

    private String name;

    private String subjectName;
}
