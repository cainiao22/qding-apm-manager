package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 保存应用及告警规则、告警接收人
 *
 * @author weijia
 */
@Getter
@Setter
public class UpdateAppDto {
    @NotNull(message = "应用id不能为空")
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 新增告警规则列表
     */
    @Valid
    private List<@NotNull(message="新增告警规则列表不能有NULL元素") AlertRuleDto> addAlertRuleList;

    /**
     * 更新告警规则列表
     */
    @Valid
    private List<@NotNull(message="更新告警规则列表不能有NULL元素") AlertRuleDto> updateAlertRuleList;

    /**
     * 移除告警规则列表
     */
    private List<@NotNull(message="移除告警规则列表不能有NULL元素") Long> removeAlertRuleList;

    /**
     * 新增告警接收人列表
     */
    private List<@NotNull(message="新增告警接收人列表不能有NULL元素") Long> addAlertReceiverList;

    /**
     * 移除告警接收人列表
     */
    private List<@NotNull(message="移除告警接收人列表不能有NULL元素") Long> removeAlertReceiverList;
}
