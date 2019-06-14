package com.qding.apm.manager.web.interfaces.controller;

import com.qding.apm.manager.core.application.dto.EnvDto;
import com.qding.apm.manager.core.application.service.EnvService;
import com.qding.platform.framework.base.validation.CreateValidation;
import com.qding.platform.framework.base.validation.UpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 维护环境变量
 *
 * @author weijia
 */
@RestController
public class EnvController {
    /**
     * 环境变量管理服务
     */
    @Autowired
    private EnvService envService;

    /**
     * 查找全部环境变量设置
     *
     * @return 返回全部环境变量设置
     */
    @GetMapping("env")
    public List<EnvDto> findEnv() {
        final List<EnvDto> envDtoList = envService.findAllEnv();

        return envDtoList;
    }

    /**
     * 新建环境变量配置
     *
     * @param envDto        环境变量
     * @param bindingResult 输入校验
     * @return 返回环境变量id
     */
    @PostMapping("env")
    public long createEnv(@RequestBody @Validated({CreateValidation.class}) final EnvDto envDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        return envService.saveEnv(envDto);
    }

    /**
     * 修改环境变量配置
     *
     * @param envDto        环境变量
     * @param bindingResult 输入校验
     * @return 返回环境变量id
     */
    @PutMapping("env")
    public long updateEnv(@RequestBody @Validated({UpdateValidation.class}) final EnvDto envDto, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        }

        return envService.saveEnv(envDto);
    }

    /**
     * 删除环境变量
     *
     * @param id 环境变量id
     */
    @DeleteMapping(value = "env/{id}")
    public void deleteEnvById(@PathVariable("id") final long id) {
        envService.deleteEnvById(id);
    }
}
