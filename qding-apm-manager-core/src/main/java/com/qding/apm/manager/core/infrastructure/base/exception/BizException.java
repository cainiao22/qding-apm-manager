package com.qding.apm.manager.core.infrastructure.base.exception;

/**
 * 业务异常
 *
 * @author weijia
 */
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }
}
