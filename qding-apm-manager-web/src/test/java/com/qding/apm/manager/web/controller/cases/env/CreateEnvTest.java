package com.qding.apm.manager.web.controller.cases.env;


import com.alibaba.fastjson.JSON;
import com.qding.apm.manager.core.application.dto.EnvDto;
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
public class CreateEnvTest {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateEnv01() throws Exception {
        final EnvDto envDto = new EnvDto();
        envDto.setAttribute("zk");
        envDto.setAttributeValue("192.168.12.11");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/env").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(envDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}