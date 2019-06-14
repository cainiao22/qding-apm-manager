package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 告警接收人及关联告警规则
 *
 * @author weijia
 */
@Getter
@Setter
public class AlertReceiverModelDto {
    /**
     * pk
     */
    private Long id;

    /**
     * 企业微信姓名
     */
    private String name;

    /**
     * 企业微信ID
     */
    private String wechatId;

    /**
     * 告警接收人所关联的应用
     */
    private List<AppDto> appDtoList;
}
