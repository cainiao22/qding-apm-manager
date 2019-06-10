package com.qding.apm.manager.core.domain.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除告警规则模版事件
 *
 * @author weijia
 */
public class SaveAlertRuleTemplateEvent extends ApplicationEvent {
    public SaveAlertRuleTemplateEvent(Object source) {
        super(source);
    }
}
