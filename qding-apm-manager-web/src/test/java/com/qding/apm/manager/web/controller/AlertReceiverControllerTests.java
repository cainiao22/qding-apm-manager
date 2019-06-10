package com.qding.apm.manager.web.controller;

import com.qding.apm.manager.web.controller.cases.alertreceiver.CreateAlertReceiverTest;
import com.qding.apm.manager.web.controller.cases.alertreceiver.DeleteAlertReceiverTest;
import com.qding.apm.manager.web.controller.cases.alertreceiver.UpdateAlertReceiverTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreateAlertReceiverTest.class, UpdateAlertReceiverTest.class, DeleteAlertReceiverTest.class})
public class AlertReceiverControllerTests {
    //
}
