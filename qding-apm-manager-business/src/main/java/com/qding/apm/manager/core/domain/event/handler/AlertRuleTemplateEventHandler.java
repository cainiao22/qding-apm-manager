package com.qding.apm.manager.core.domain.event.handler;

import com.qding.apm.manager.core.domain.event.event.DeleteAlertRuleTemplateEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAlertRuleTemplateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 处理告警规则模版变更相关事件
 *
 * @author weijia
 */
@Component
public class AlertRuleTemplateEventHandler {
    @EventListener
    public void handler(SaveAlertRuleTemplateEvent event) {
        //
    }

    @EventListener
    public void handler(DeleteAlertRuleTemplateEvent event) {
        //
    }
}
