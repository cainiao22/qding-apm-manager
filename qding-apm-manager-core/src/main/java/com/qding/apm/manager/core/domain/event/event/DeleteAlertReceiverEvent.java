package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除告警接收人事件
 *
 * @author weijia
 */
public class DeleteAlertReceiverEvent extends ApplicationEvent {
    public DeleteAlertReceiverEvent(Object source) {
        super(source);
    }
}
