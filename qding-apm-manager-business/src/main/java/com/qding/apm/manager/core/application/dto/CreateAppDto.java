package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateAppDto {
    /**
     * 应用名称
     */
    @NotBlank(message = "应用程序名称不能为空")
    private String name;

    /**
     * 新增告警规则列表
     */
    @Valid
    @NotNull(message = "新增应用必须关联告警规则")
    private List<AlertRuleDto> addAlertRuleList;

    /**
     * 新增告警接收人列表
     */
    @NotNull(message = "新增应用必须关联告警接收人")
    private List<Long> addAlertReceiverList;
}
