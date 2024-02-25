package com.jens.handler;


import com.jens.common.R;
import com.jens.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {


    /**
     * 统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public R validException(BindException e) {
        return new R(400L, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 自定义业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public R validException(BusinessException e) {
        return new R(400L, e.getCode().getDesc());
    }
}
