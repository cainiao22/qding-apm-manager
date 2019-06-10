package com.qding.apm.manager.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qding.apm.manager.core.infrastructure.base.ddd.AggreagteRoot;
import com.qding.apm.manager.core.infrastructure.base.AuditFields;
import lombok.Getter;
import lombok.Setter;

/**
 * alert_rule_template表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "alert_rule_template")
public class AlertRuleTemplateEntity extends AuditFields implements AggreagteRoot {
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

    /**
     * pk
     */
    @TableId(value = "appId", type = IdType.AUTO)
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
     * Builder Pattern
     */
    public static class Builder {
        private final Long id;
        private final String name;
        private final String express;
        private final Integer wait;
        private final Integer severity;
        private final String summary;
        private final String description;

        public Builder(final Long id, final String name, final String express, final Integer wait,
                       final Integer severity, final String summary, final String description) {
            this.id = id;
            this.name = name;
            this.express = express;
            this.wait = wait;
            this.severity = severity;
            this.summary = summary;
            this.description = description;
        }

        public AlertRuleTemplateEntity build() {
            final AlertRuleTemplateEntity entity = new AlertRuleTemplateEntity(this);

            // 构建完模型时对模型进行完整性校验
            entity.validate();

            return entity;
        }
    }

    public AlertRuleTemplateEntity() {
        //
    }

    /**
     * 利用builder pattern对内部对象赋值
     *
     * @param builder builder对象
     */
    private AlertRuleTemplateEntity(AlertRuleTemplateEntity.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.express = builder.express;
        this.wait = builder.wait;
        this.severity = builder.severity;
        this.summary = builder.summary;
        this.description = builder.description;
    }

    /**
     * 校验整个模型合法性
     */
    @Override
    public void validate() {
        //
    }
}
