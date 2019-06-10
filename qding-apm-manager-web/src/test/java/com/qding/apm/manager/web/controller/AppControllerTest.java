package com.qding.apm.manager.web.controller;

import com.qding.apm.manager.web.controller.cases.app.CreateAppTest;
import com.qding.apm.manager.web.controller.cases.app.DeleteAppTest;
import com.qding.apm.manager.web.controller.cases.app.UpdateAppTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreateAppTest.class, UpdateAppTest.class, DeleteAppTest.class})
public class AppControllerTest {
    //
}
