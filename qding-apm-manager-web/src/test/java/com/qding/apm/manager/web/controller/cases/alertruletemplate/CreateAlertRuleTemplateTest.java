package com.qding.apm.manager.web.controller.cases.alertruletemplate;

import com.alibaba.fastjson.JSON;
import com.qding.apm.manager.core.application.dto.AlertRuleTemplateDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAlertRuleTemplateTest {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateAlertRuleTemplate01() throws Exception {
        final AlertRuleTemplateDto alertRuleDto = new AlertRuleTemplateDto();
        alertRuleDto.setName("cpu alert");
        alertRuleDto.setExpress("avg_over_time( process_cpu_usage{job=\"springboot-metrics\"}[5m] ) * 100 >= 50");
        alertRuleDto.setWait(30);
        alertRuleDto.setSeverity(1);
        alertRuleDto.setSummary("cpu usage > 50%");
        alertRuleDto.setDescription("cpu usage > 50%");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/alertrule-template").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(alertRuleDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
