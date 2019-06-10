package com.qding.apm.manager.core.domain.event.handler;

import com.qding.apm.manager.core.domain.event.event.DeleteAppEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAppEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 处理应用程序变更相关事件
 *
 * @author weijia
 */
@Component
public class AppEventHandler {
    @EventListener
    public void handler(SaveAppEvent event) {
        //
    }

    @EventListener
    public void handler(DeleteAppEvent event) {
        //
    }
}
