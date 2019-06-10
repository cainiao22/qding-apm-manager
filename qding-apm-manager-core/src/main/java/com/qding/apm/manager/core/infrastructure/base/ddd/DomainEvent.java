package com.qding.apm.manager.core.infrastructure.base.ddd;

import org.springframework.context.ApplicationEvent;

/**
 * 领域事件
 *
 * @author weijia
 */
public class DomainEvent extends ApplicationEvent {
    public DomainEvent(Object source) {
        super(source);
    }
}
