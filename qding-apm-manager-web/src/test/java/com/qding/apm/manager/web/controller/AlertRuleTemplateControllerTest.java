package com.qding.apm.manager.web.controller;

import com.qding.apm.manager.web.controller.cases.alertruletemplate.CreateAlertRuleTemplateTest;
import com.qding.apm.manager.web.controller.cases.alertruletemplate.DeleteAlertRuleTemplateTest;
import com.qding.apm.manager.web.controller.cases.alertruletemplate.UpdateAlertRuleTemplateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreateAlertRuleTemplateTest.class, UpdateAlertRuleTemplateTest.class, DeleteAlertRuleTemplateTest.class})
public class AlertRuleTemplateControllerTest {
    //
}

