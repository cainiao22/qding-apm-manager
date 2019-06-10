package com.qding.apm.manager.web.controller;

import com.qding.apm.manager.web.controller.cases.env.CreateEnvTest;
import com.qding.apm.manager.web.controller.cases.env.DeleteEnvTest;
import com.qding.apm.manager.web.controller.cases.env.UpdateEnvTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreateEnvTest.class, UpdateEnvTest.class, DeleteEnvTest.class})
public class EnvControllerTest {
    //
}
