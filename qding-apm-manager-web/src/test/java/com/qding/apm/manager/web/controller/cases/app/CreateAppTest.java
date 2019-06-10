package com.qding.apm.manager.web.controller.cases.app;

import com.alibaba.fastjson.JSON;
import com.qding.apm.manager.core.application.dto.AlertRuleDto;
import com.qding.apm.manager.core.application.dto.CreateAppDto;
import org.junit.Assert;
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

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAppTest {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateApp01() throws Exception {
        final AlertRuleDto alertRule1 = new AlertRuleDto();
        alertRule1.setName("cpu alert");
        alertRule1.setExpress("avg_over_time( process_cpu_usage{job=\"springboot-metrics\"}[5m] ) * 100 >= 50");
        alertRule1.setWait(30);
        alertRule1.setSeverity(1);
        alertRule1.setSummary("cpu usage > 50%");
        alertRule1.setDescription("cpu usage > 50%");

        final CreateAppDto createAppDto = new CreateAppDto();
        createAppDto.setName("qds-order");
        createAppDto.setAddAlertRuleList(Stream.of(alertRule1).collect(Collectors.toList()));
        createAppDto.setAddAlertReceiverList(Stream.of(99999L, 9999000L).collect(Collectors.toList()));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(createAppDto)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(e -> {
                    Assert.assertEquals(e.getResponse().getContentAsString(), "告警接收人不存在");
                })
                .andDo(print());
    }
}
