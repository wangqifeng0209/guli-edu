package com.guli.edu.handler;

import com.guli.edu.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jason
 * @create 2019-12-08-22:00
 */


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有的异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handlerException(Exception e) {
        e.printStackTrace();
        return R.error().message("服务器暂时无法访问");
    }


    /**
     * 处理特定的异常
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R handlerException(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("除数不能为0，请重新计算");
    }

    /**
     * 处理自定义的异常
     * @return
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R handlerException(EduException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

}
