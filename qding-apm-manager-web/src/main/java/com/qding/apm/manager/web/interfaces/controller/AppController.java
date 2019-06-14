package com.qding.apm.manager.web.interfaces.controller;

import com.qding.apm.manager.core.application.dto.AppDto;
import com.qding.apm.manager.core.application.dto.AppModelDto;
import com.qding.apm.manager.core.application.dto.CreateAppDto;
import com.qding.apm.manager.core.application.dto.UpdateAppDto;
import com.qding.apm.manager.core.application.service.AppService;
import com.qding.platform.framework.base.validation.CreateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 维护应用程序、告警规则、告警通知人等相关设置
 *
 * @author weijia
 */
@RestController
public class AppController {
    @Autowired
    private AppService appService;

    /**
     * 获取全部应用程序列表
     *
     * @return 返回全部应用程序列表
     */
    @GetMapping(value = "app")
    public List<AppDto> findAllApp() {
        return appService.findAllApp();
    }

    /**
     * 通过应用程序id查询应用配置
     *
     * @param id 应用程序id
     * @return 返回查询到的应用配置
     */
    @GetMapping(value = "app/{id}")
    public AppModelDto findAppById(@PathVariable("id") final long id) {
        return appService.findAppById(id);
    }

    /**
     * 创建应用程序及所属监控配置
     *
     * @param createAppDto  应用的apm配置
     * @param bindingResult 输入校验结果
     * @return 返回应用程序id
     */
    @PostMapping("/app")
    public long createApp(@RequestBody @Validated({CreateValidation.class}) final CreateAppDto createAppDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        return appService.createApp(createAppDto);
    }

    /**
     * 保存应用程序及所属监控配置
     *
     * @param updateAppDto  应用的apm配置
     * @param bindingResult 输入校验结果
     * @return 返回应用程序id
     */
    @PutMapping("/app")
    public void updateApp(@RequestBody @Validated final UpdateAppDto updateAppDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        if (updateAppDto.getAddAlertRuleList() != null) {
            Assert.isTrue(!updateAppDto.getAddAlertRuleList().stream().anyMatch(elem -> elem.getId() != null), "新增告警规则不需要输入ID");
        }

        if (updateAppDto.getUpdateAlertRuleList() != null) {
            Assert.isTrue(!updateAppDto.getUpdateAlertRuleList().stream().anyMatch(elem -> elem.getId() == null), "更新告警规则ID不能为空");
        }

        appService.updateApp(updateAppDto);
    }

    /**
     * 删除应用程序所属监控配置
     *
     * @param id 应用程序idl
     */
    @DeleteMapping(value = "app/{id}")
    public void deleteAppById(@PathVariable("id") final long id) {
        // 删除应用程序apm配置
        appService.deleteAppById(id);
    }
}
