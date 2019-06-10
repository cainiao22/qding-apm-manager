package com.qding.apm.manager.web.interfaces.aspect;

import com.qding.apm.aop.ApmRecordMethodAspect;
import com.qding.apm.manager.core.infrastructure.base.context.OperContext;
import com.qding.apm.manager.core.infrastructure.base.vo.OperInfoVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class HttpRequestAspect {
    @Autowired
    private ApmRecordMethodAspect apmRecordMethodAspect;

    @Around(value = "execution(public * com.qding.apm.manager.web.interfaces.controller..*.*(..))")
    public Object aroundProcessClass(ProceedingJoinPoint proceeJoinPoint) throws Throwable {
        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = servletRequestAttributes.getRequest();
        final HttpServletResponse response = servletRequestAttributes.getResponse();

        // todo
        OperContext.set(new OperInfoVo("admin"));

        return apmRecordMethodAspect.aroundProcessClass(proceeJoinPoint);
    }
}
