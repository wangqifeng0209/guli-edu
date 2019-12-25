package com.guli.edu.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jason
 * @create 2019-12-08-22:10
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException {

    private Integer code;

    private String message;


}
