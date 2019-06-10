package com.qding.apm.manager.web.interfaces.controller;

import com.qding.apm.manager.core.application.dto.AlertReceiverDto;
import com.qding.apm.manager.core.application.dto.AlertReceiverModelDto;
import com.qding.apm.manager.core.application.dto.SaveAlertReceiverDto;
import com.qding.apm.manager.core.application.service.AlertReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理告警接收人
 *
 * @author weijia
 */
@RestController
public class AlertReceiverController {
    @Autowired
    private AlertReceiverService alertReceiverService;

    /**
     * 查找全部告警接收人
     *
     * @return 返回全部告警接收人
     */
    @GetMapping(value = "alertreceiver")
    public List<AlertReceiverDto> findAllAlertReceiver() {
        return alertReceiverService.findAllAlertReceiver();
    }

    /**
     * 通过告警接收人id查询告警接收人及关联的应用程序
     *
     * @param id 告警接收人id
     * @return 返回告警接收人及关联的应用程序
     */
    @GetMapping(value = "alertreceiver/{id}")
    public AlertReceiverModelDto findAlertReceiverById(@PathVariable("id") final long id) {
        return alertReceiverService.findAlertReceiverById(id);
    }

    /**
     *  创建告警接收人
     *
     * @param saveAlertReceiverDto 告警接收人
     * @param bindingResult        输入校验结果
     * @return 告警接收人id
     */
    @PostMapping("/alertreceiver")
    public long createAlertReceiver(@RequestBody @Valid final SaveAlertReceiverDto saveAlertReceiverDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        return alertReceiverService.saveAlertReceiver(saveAlertReceiverDto);
    }

    /**
     * 修改告警接收人
     *
     * @param saveAlertReceiverDto 告警接收人
     * @param bindingResult        输入校验结果
     * @return 告警接收人id
     */
    @PutMapping("/alertreceiver")
    public void updateAlertReceiver(@RequestBody @Valid final SaveAlertReceiverDto saveAlertReceiverDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        alertReceiverService.saveAlertReceiver(saveAlertReceiverDto);
    }

    /**
     * 删除告警接收人
     *
     * @param id 告警接收人id
     * @return
     */
    @DeleteMapping(value = "alertreceiver/{id}")
    public void deleteAlertReceiverById(@PathVariable("id") final long id) {
        // 删除应用程序apm配置
        alertReceiverService.deleteAlertReceiverById(id);
    }
}
