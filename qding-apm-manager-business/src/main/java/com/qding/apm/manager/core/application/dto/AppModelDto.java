package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 应用程序完整数据，包括关联告警规则及告警接收人
 *
 * @author weijia
 */
@Getter
@Setter
public class AppModelDto {
    /**
     * appId
     */
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 告警规则
     */
    private List<AlertRuleDto> alertRuleList;

    /**
     * 告警接收人
     */
    private List<AlertReceiverDto> alertReceiverList;
}
