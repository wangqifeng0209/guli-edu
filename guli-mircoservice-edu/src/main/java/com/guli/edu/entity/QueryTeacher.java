package com.guli.edu.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Jason
 * @create 2019-12-08-18:45
 */

@ApiModel("分页条件查找封装的一个查询讲师类")
@Data
public class QueryTeacher {

    @ApiModelProperty("讲师姓名")
    private String name;

    @ApiModelProperty("讲师级别，1高级讲师，2首席讲师")
    private Integer level;

    @ApiModelProperty("条件查询中的起始时间")
    private String gmtCreateStart;

    @ApiModelProperty("条件查询中的结束时间")
    private String gmtCreateEnd;
}
