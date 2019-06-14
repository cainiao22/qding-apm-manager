package com.qding.apm.manager.core.application.dto;

import com.qding.platform.framework.base.validation.CreateValidation;
import com.qding.platform.framework.base.validation.UpdateValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 保存告警接收人
 *
 * @author weijia
 */
@Getter
@Setter
public class SaveAlertReceiverDto {
    /**
     * pk
     */
    @NotNull(message = "告警接收人ID不能为空", groups = UpdateValidation.class)
    private Long id;

    /**
     * 企业微信姓名
     */
    @NotBlank(message = "企业微信姓名不能为空", groups = CreateValidation.class)
    private String name;

    /**
     * 企业微信ID
     */
    @NotBlank(message = "企业微信ID不能为空", groups = CreateValidation.class)
    private String wechatId;

    /**
     * 新增应用列表
     */
    private List<@NotNull(message = "新增应用列表不能有NULL元素") Long> addAppIdList;

    /**
     * 移除应用列表
     */
    private List<@NotNull(message = "移除应用列表不能有NULL元素") Long> removeAppIdList;
}
