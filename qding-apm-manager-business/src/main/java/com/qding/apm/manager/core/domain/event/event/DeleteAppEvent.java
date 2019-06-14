package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除应用事件
 *
 * @author weijia
 */
public class DeleteAppEvent extends ApplicationEvent {
    public DeleteAppEvent(Object source) {
        super(source);
    }
}
