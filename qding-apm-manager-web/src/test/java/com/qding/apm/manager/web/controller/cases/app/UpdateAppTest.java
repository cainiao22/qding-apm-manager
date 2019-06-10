package com.qding.apm.manager.web.controller.cases.app;

import com.alibaba.fastjson.JSON;
import com.qding.apm.manager.core.application.dto.AlertRuleDto;
import com.qding.apm.manager.core.application.dto.AppModelDto;
import com.qding.apm.manager.core.application.dto.CreateAppDto;
import com.qding.apm.manager.core.application.dto.UpdateAppDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAppTest {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 验证输入合法性
     *
     * @throws Exception
     */
    @Test
    public void testUpdateApp01() throws Exception {
        final List<Long> removeAlertRuleList = new ArrayList<>();
        removeAlertRuleList.add(null);

        final UpdateAppDto updateAppDto = new UpdateAppDto();
        updateAppDto.setId(99L);
        updateAppDto.setRemoveAlertRuleList(removeAlertRuleList);

        mockMvc.perform(MockMvcRequestBuilders.put("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updateAppDto)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(e -> {
                    Assert.assertEquals(e.getResponse().getContentAsString(), "移除告警规则列表不能有NULL元素");
                })
                .andDo(print());
    }

    /**
     * 验证输入合法性
     *
     * @throws Exception
     */
    @Test
    public void testUpdateApp02() throws Exception {
        final UpdateAppDto updateAppDto = new UpdateAppDto();

        mockMvc.perform(MockMvcRequestBuilders.put("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updateAppDto)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(e -> {
                    Assert.assertEquals(e.getResponse().getContentAsString(), "应用id不能为空");
                })
                .andDo(print());
    }

    /**
     * 验证输入合法性
     *
     * @throws Exception
     */
    @Test
    public void testUpdateApp03() throws Exception {
        final AlertRuleDto alertRuleDto = new AlertRuleDto();
        alertRuleDto.setId(9L);
        alertRuleDto.setName("cpu alert");
        alertRuleDto.setExpress("avg_over_time( process_cpu_usage{job=\"springboot-metrics\"}[5m] ) * 100 >= 50");
        alertRuleDto.setWait(30);
        alertRuleDto.setSeverity(1);
        alertRuleDto.setSummary("cpu usage > 50%");
        alertRuleDto.setDescription("cpu usage > 50%");

        final UpdateAppDto updateAppDto = new UpdateAppDto();
        updateAppDto.setId(100L);
        updateAppDto.setAddAlertRuleList(Stream.of(alertRuleDto).collect(Collectors.toList()));

        mockMvc.perform(MockMvcRequestBuilders.put("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updateAppDto)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(e -> {
                    Assert.assertEquals(e.getResponse().getContentAsString(), "新增告警规则不需要输入ID");
                })
                .andDo(print());
    }

    @Test
    public void testUpdateApp04() throws Exception {
        // insert receiver todo

        // insert app
        final AlertRuleDto alertRuleDto = new AlertRuleDto();
        alertRuleDto.setName("cpu alert");
        alertRuleDto.setExpress("avg_over_time( process_cpu_usage{job=\"springboot-metrics\"}[5m] ) * 100 >= 50");
        alertRuleDto.setWait(30);
        alertRuleDto.setSeverity(1);
        alertRuleDto.setSummary("cpu usage > 50%");
        alertRuleDto.setDescription("cpu usage > 50%");

        final CreateAppDto createAppDto = new CreateAppDto();
        createAppDto.setName("qds-test");
        createAppDto.setAddAlertRuleList(Stream.of(alertRuleDto).collect(Collectors.toList()));
        createAppDto.setAddAlertReceiverList(Stream.of(1L).collect(Collectors.toList()));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(createAppDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        final Long id = Long.valueOf(result.getResponse().getContentAsString());

        // update
        Thread.sleep(2000);

        final UpdateAppDto updateAppDto = new UpdateAppDto();
        updateAppDto.setId(id);

        mockMvc.perform(MockMvcRequestBuilders.put("/app").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updateAppDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        // query
        result = mockMvc.perform(MockMvcRequestBuilders.get("/app/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

        final AppModelDto appModelDto = JSON.parseObject(result.getResponse().getContentAsString(), AppModelDto.class);

        Assert.assertEquals(createAppDto.getName(), appModelDto.getName());
        Assert.assertEquals(alertRuleDto.getName(), appModelDto.getAlertRuleList().get(0).getName());
        Assert.assertEquals(alertRuleDto.getExpress(), appModelDto.getAlertRuleList().get(0).getExpress());
        Assert.assertEquals(alertRuleDto.getWait(), appModelDto.getAlertRuleList().get(0).getWait());
        Assert.assertEquals(alertRuleDto.getSeverity(), appModelDto.getAlertRuleList().get(0).getSeverity());
        Assert.assertEquals(alertRuleDto.getSummary(), appModelDto.getAlertRuleList().get(0).getSummary());
        Assert.assertEquals(alertRuleDto.getDescription(), appModelDto.getAlertRuleList().get(0).getDescription());
        Assert.assertTrue(appModelDto.getAlertReceiverList().get(0).getId() == 1L); //todo
    }
}
