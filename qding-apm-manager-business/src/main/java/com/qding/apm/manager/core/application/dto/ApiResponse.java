package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Rest API返回结果包装类
 *
 * @param <T> 返回结果数据类型
 * @author weijia
 */
@Getter
@Setter
public class ApiResponse<T> {
    /**
     * 成功
     */
    public static final int SUCCESS = 0;

    /**
     * 业务异常
     */
    public static final int BUSINESS_ERROR = -1;

    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = -2;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 移除时返回错误消息
     */
    private String message;

    public static <T> ApiResponse<T> success(final T data) {
        final ApiResponse<T> result = new ApiResponse<>();
        result.setData(data);
        result.setCode(SUCCESS);
        result.setMessage("");

        return result;
    }

    public static <T> ApiResponse<T> success() {
        final ApiResponse<T> result = new ApiResponse<>();
        result.setData(null);
        result.setCode(SUCCESS);
        result.setMessage("");

        return result;
    }

    public static <T> ApiResponse<T> failed(final int code, final String message) {
        final ApiResponse<T> result = new ApiResponse<>();
        result.setData(null);
        result.setCode(code);
        result.setMessage(message);

        return result;
    }
}
