package com.qding.apm.manager.web;

import com.qding.apm.service.init.ApmService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * APM 管理控制台入口
 *
 * @author weijia
 */
@SpringBootApplication
@ComponentScan({"com.qding.apm"})
@ServletComponentScan("com.qding.apm")
@MapperScan("com.qding.apm.manager.core.infrastructure.persistence.dao")
@EnableFeignClients
public class Application implements ApplicationRunner {
    @Autowired
    private ApmService apmService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 初始化APM
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<String> eventList = new ArrayList<String>();

        apmService.initialize(eventList);
    }
}
