package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除环境事件
 *
 * @author weijia
 */
public class DeleteEnvEvent extends ApplicationEvent {
    public DeleteEnvEvent(Object source) {
        super(source);
    }
}
