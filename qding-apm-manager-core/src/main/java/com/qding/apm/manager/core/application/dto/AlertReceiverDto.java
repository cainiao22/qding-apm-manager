package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 告警接收人及关联应用程序
 *
 * @author weijia
 */
@Getter
@Setter
public class AlertReceiverDto {
    /**
     * 告警接收人id
     */
    private Long id;

    /**
     * 企业微信姓名
     */
    @NotBlank(message = "企业微信姓名不能为空")
    private String name;

    /**
     * 企业微信ID
     */
    @NotBlank(message = "企业微信Id不能为空")
    private String wechatId;
}
