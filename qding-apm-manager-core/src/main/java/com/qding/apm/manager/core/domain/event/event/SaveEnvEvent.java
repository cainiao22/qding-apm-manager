package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 保存环境设置事件
 *
 * @author weijia
 */
public class SaveEnvEvent extends ApplicationEvent {
    public SaveEnvEvent(Object source) {
        super(source);
    }
}
