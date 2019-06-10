package com.qding.apm.manager.core.infrastructure.base.context;

import com.qding.apm.manager.core.infrastructure.base.vo.OperInfoVo;

/**
 * 获取操作信息
 *
 * @author weijia
 */
public class OperContext {
    private static ThreadLocal<OperInfoVo> context = new ThreadLocal<>();

    public static void set(final OperInfoVo operInfo) {
        context.set(operInfo);
    }

    public static OperInfoVo get() {
        return context.get();
    }
}
