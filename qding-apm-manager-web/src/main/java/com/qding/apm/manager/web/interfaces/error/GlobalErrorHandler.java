package com.qding.apm.manager.web.interfaces.error;

import com.qding.apm.manager.core.infrastructure.base.exception.BizException;
import com.qding.apm.manager.core.infrastructure.base.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<String> handler(HttpServletRequest req, BizException ex) throws Exception {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public ResponseEntity<String> handler(HttpServletRequest req, SystemException ex) throws Exception {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<String> handler(HttpServletRequest req, IllegalArgumentException ex) throws Exception {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> handler(HttpServletRequest req, Exception ex) throws Exception {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
