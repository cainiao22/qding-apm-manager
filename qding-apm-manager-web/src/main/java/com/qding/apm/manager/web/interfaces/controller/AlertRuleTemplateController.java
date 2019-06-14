package com.qding.apm.manager.web.interfaces.controller;

import com.qding.apm.manager.core.application.dto.AlertRuleTemplateDto;
import com.qding.apm.manager.core.application.service.AlertRuleTemplateService;
import com.qding.platform.framework.base.validation.CreateValidation;
import com.qding.platform.framework.base.validation.UpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理告警规则模版
 *
 * @author weijia
 */
@RestController
public class AlertRuleTemplateController {
    @Autowired
    private AlertRuleTemplateService alertRuleTemplateService;

    /**
     * 查询全部告警规则模版
     *
     * @return 返回全部告警规则模版
     */
    @GetMapping(value = "/alertrule-template")
    public List<AlertRuleTemplateDto> findAllAlertRuleTemplate() {
        return alertRuleTemplateService.findAllAlertRuleTemplate();
    }

    /**
     * 新建告警规则模版
     *
     * @param alertRuleTemplateDto 告警规则模版
     * @param bindingResult        输入校验结果
     * @return 告警规则模版id
     */
    @PostMapping("/alertrule-template")
    public long createAlertRuleTemplate(@RequestBody @Validated({CreateValidation.class}) final AlertRuleTemplateDto alertRuleTemplateDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        return alertRuleTemplateService.saveAlertRuleTemplate(alertRuleTemplateDto);
    }

    /**
     * 新建告警规则模版
     *
     * @param alertRuleTemplateDto 告警规则模版
     * @param bindingResult        输入校验结果
     * @return 告警规则模版id
     */
    @PutMapping("/alertrule-template")
    public void updateAlertRuleTemplate(@RequestBody @Validated({UpdateValidation.class}) final AlertRuleTemplateDto alertRuleTemplateDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        alertRuleTemplateService.saveAlertRuleTemplate(alertRuleTemplateDto);
    }

    /**
     * 删除告警规则模版
     *
     * @param id 告警规则模版id
     */
    @DeleteMapping(value = "/alertrule-template/{id}")
    public void deleteAlertRuleTemplateById(@PathVariable("id") final long id) {
        alertRuleTemplateService.deleteAlertRuleTemplateById(id);
    }
}