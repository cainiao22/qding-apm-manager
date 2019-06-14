package com.qding.apm.manager.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qding.platform.framework.base.AuditFields;
import lombok.Getter;
import lombok.Setter;

/**
 * alert_rule表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "alert_rule")
public class AlertRuleEntity extends AuditFields {
    @TableField(exist = false)
    public static final String ID = "id";

    @TableField(exist = false)
    public static final String NAME = "name";

    @TableField(exist = false)
    public static final String EXPRESS = "express";

    @TableField(exist = false)
    public static final String WAIT = "wait";

    @TableField(exist = false)
    public static final String SEVERITY = "severity";

    @TableField(exist = false)
    public static final String SUMMARY = "summary";

    @TableField(exist = false)
    public static final String DESCRIPTION = "description";

    @TableField(exist = false)
    public static final String APP_ID = "app_id";

    /**
     * pk
     */
    @TableId(value = ID, type = IdType.AUTO)
    @TableField
    private Long id;

    /**
     * alert rule name
     */
    @TableField
    private String name;

    /**
     * 告警规则表达式
     */
    @TableField
    private String express;

    /**
     * 告警触发延迟
     */
    @TableField
    private Integer wait;

    /**
     * 告警等级
     */
    @TableField
    private Integer severity;

    /**
     * 告警概述
     */
    @TableField
    private String summary;

    /**
     * 告警详细描述
     */
    @TableField
    private String description;

    /**
     * 关联App表外键
     */
    @TableField
    private Long appId;
}
