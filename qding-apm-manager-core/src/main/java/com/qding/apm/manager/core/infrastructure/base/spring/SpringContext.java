package com.qding.apm.manager.core.infrastructure.base.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 非注入方式获取spring application context
 *
 * @author weijia
 */
@Component
public class SpringContext implements ApplicationContextAware {
    /**
     * spring application context
     */
    private static ApplicationContext applicationContext;

    /**
     * @param applicationContext spring application context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    /**
     * 获取spring application context
     *
     * @return spring application context
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过bean name获取spring bean
     *
     * @param beanName bean name
     * @return spring bean
     */
    public static Object getBean(final String beanName) {
        return applicationContext.getBean(beanName);
    }
}
