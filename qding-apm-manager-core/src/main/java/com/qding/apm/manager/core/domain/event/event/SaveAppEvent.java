package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除应用程序事件
 *
 * @author weijia
 */
public class SaveAppEvent extends ApplicationEvent {
    public SaveAppEvent(Object source) {
        super(source);
    }
}
