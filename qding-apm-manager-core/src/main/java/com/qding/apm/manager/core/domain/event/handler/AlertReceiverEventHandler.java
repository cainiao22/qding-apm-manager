package com.qding.apm.manager.core.domain.event.handler;

import com.qding.apm.manager.core.domain.event.event.DeleteAlertReceiverEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAlertReceiverEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 处理告警接收人变更相关事件
 *
 * @author weijia
 */
@Component
public class AlertReceiverEventHandler {
    @EventListener
    public void handler(SaveAlertReceiverEvent event) {
        //
    }

    @EventListener
    public void handler(DeleteAlertReceiverEvent event) {
        //
    }
}
