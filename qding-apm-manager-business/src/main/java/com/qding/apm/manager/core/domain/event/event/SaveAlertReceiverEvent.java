package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 保存告警接收人事件
 *
 * @author weijia
 */
public class SaveAlertReceiverEvent extends ApplicationEvent {
    public SaveAlertReceiverEvent(Object source) {
        super(source);
    }
}
