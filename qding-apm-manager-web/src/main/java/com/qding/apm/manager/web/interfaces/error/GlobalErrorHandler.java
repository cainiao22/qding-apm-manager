package com.qding.apm.manager.web.interfaces.error;

import com.qding.platform.framework.base.exception.BizException;
import com.qding.platform.framework.base.http.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 *
 * @author weijia
 */
@ControllerAdvice
@ResponseBody
public class GlobalErrorHandler {
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public String handler(HttpServletRequest request, HttpServletResponse response, BizException ex) throws Exception {
        response.setStatus(StatusCode.BUSINESS_ERROR);
        return ex.getMessage();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public String handler(HttpServletRequest request, HttpServletResponse response, IllegalArgumentException ex) throws Exception {
        response.setStatus(StatusCode.ILLEGAL_ARGUMENT_ERROR);
        return ex.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> handler(HttpServletRequest req, Exception ex) throws Exception {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
