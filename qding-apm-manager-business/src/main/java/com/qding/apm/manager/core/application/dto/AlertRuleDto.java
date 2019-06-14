package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 告警规则
 *
 * @author weijia
 */
@Getter
@Setter
public class AlertRuleDto {
    /**
     * 告警规则id
     */
    @Null(message = "新增告警规则不需要输入ID")
    private Long id;

    /**
     * alert rule name
     */
    @NotBlank(message = "告警规则名称不能为空")
    private String name;

    /**
     * 告警规则表达式
     */
    @NotBlank(message = "告警规则表达式不能为空")
    private String express;

    /**
     * 告警触发延迟
     */
    @NotNull(message = "告警触发延迟不能为空")
    private Integer wait;

    /**
     * 告警等级
     */
    @NotNull(message = "告警等级不能为空")
    private Integer severity;

    /**
     * 告警概述
     */
    @NotBlank(message = "告警概述不能为空")
    private String summary;

    /**
     * 告警详细描述
     */
    @NotBlank(message = "告警详细描述不能为空")
    private String description;
}
