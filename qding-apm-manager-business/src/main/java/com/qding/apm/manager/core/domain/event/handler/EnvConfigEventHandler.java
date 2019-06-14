package com.qding.apm.manager.core.domain.event.handler;

import com.qding.apm.manager.core.domain.event.event.DeleteEnvEvent;
import com.qding.apm.manager.core.domain.event.event.SaveEnvEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 处理环境变更相关事件
 *
 * @author weijia
 */
@Component
public class EnvConfigEventHandler {
    @EventListener
    public void handler(SaveEnvEvent event) {
        //
    }

    @EventListener
    public void handler(DeleteEnvEvent event) {
        //
    }
}
