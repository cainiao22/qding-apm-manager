package com.qding.apm.manager.core.infrastructure.base.ddd;

/**
 * 聚合根定义
 *
 * @author weijia
 */
public interface AggreagteRoot {
    /**
     * 校验整个模型合法性
     */
    void validate();
}
