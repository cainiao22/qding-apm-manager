package com.qding.apm.manager.core.application.dto;

import com.qding.apm.manager.core.infrastructure.base.validation.CreateValidation;
import com.qding.apm.manager.core.infrastructure.base.validation.UpdateValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 环境属性
 *
 * @author weijia
 */
@Getter
@Setter
public class EnvDto {
    @NotNull(message = "环境ID不能为空", groups = UpdateValidation.class)
    private Long id;

    /**
     * 环境属性名称
     */
    @NotBlank(message = "环境属性名称", groups = CreateValidation.class)
    private String attribute;

    /**
     * 环境属性值
     */
    @NotBlank(message = "环境属性值", groups = CreateValidation.class)
    private String attributeValue;
}
