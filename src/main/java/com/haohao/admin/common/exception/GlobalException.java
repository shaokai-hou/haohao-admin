package com.haohao.admin.common.exception;

import com.haohao.admin.common.result.ResultCodeEnum;
import com.haohao.admin.common.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常配置类
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    /**
     * 空指针异常处理方法
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultData<String> error(NullPointerException e) {
        log.error("空指针异常信息 \n", e);
        return ResultData.error(ResultCodeEnum.NULL_POINT);
    }

    /**
     * 参数验证异常处理方法
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultData<Map<String, String>> error(MethodArgumentNotValidException e) {
        log.error("参数验证异常信息 \n", e);
        Map<String, String> errors = new HashMap<>(8);
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResultData.error(ResultCodeEnum.PARAM_ERROR, errors);
    }

    /**
     * 通用异常处理方法
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData<String> error(Exception e) throws Exception {
        // 将springSecurity的异常交给对应处理器去处理
        if (e instanceof AccessDeniedException || e instanceof AuthenticationException) {
            throw e;
        }
        log.error("全局异常信息 \n", e);
        return ResultData.error(e.getMessage());
    }
}
