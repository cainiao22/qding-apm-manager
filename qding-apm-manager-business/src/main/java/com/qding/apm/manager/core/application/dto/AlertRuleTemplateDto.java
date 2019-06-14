package com.qding.apm.manager.core.application.dto;

import com.qding.platform.framework.base.validation.CreateValidation;
import com.qding.platform.framework.base.validation.UpdateValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 告警规则模版
 *
 * @author weijia
 */
@Getter
@Setter
public class AlertRuleTemplateDto {
    @NotNull(message = "告警规则模版ID不能为空", groups = UpdateValidation.class)
    private Long id;

    /**
     * alert rule name
     */
    @NotBlank(message = "告警规则名称不能为空", groups = CreateValidation.class)
    private String name;

    /**
     * 告警规则表达式
     */
    @NotBlank(message = "告警规则表达式不能为空", groups = CreateValidation.class)
    private String express;

    /**
     * 告警触发延迟
     */
    @NotNull(message = "告警触发延迟不能为空", groups = CreateValidation.class)
    private Integer wait;

    /**
     * 告警等级
     */
    @NotNull(message = "告警等级不能为空", groups = CreateValidation.class)
    private Integer severity;

    /**
     * 告警概述
     */
    @NotBlank(message = "告警概述不能为空", groups = CreateValidation.class)
    private String summary;

    /**
     * 告警详细描述
     */
    @NotBlank(message = "告警详细描述不能为空", groups = CreateValidation.class)
    private String description;
}
