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
 * env表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "env")
public class EnvEntity extends AuditFields implements AggreagteRoot {
    @TableField(exist = false)
    public static final String ID = "id";

    @TableField(exist = false)
    public static final String ATTRIBUTE = "attribute";

    @TableField(exist = false)
    public static final String ATTRIBUTE_VALUE = "attribute_value";

    /**
     * pk
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 配置项名称
     */
    @TableField
    private String attribute;

    /**
     * 配置项值
     */
    @TableField
    private String attributeValue;

    /**
     * Builder Pattern
     */
    public static class Builder {
        private final Long id;
        private final String attribute;
        private final String attributeValue;


        public Builder(final Long id, final String attribute, final String attributeValue) {
            this.id = id;
            this.attribute = attribute;
            this.attributeValue = attributeValue;
        }

        public EnvEntity build() {
            EnvEntity envModel = new EnvEntity(this);
            // 构建完模型时对模型进行完整性校验
            envModel.validate();

            return envModel;
        }
    }

    public EnvEntity() {
        //
    }

    private EnvEntity(Builder builder) {
        this.id = builder.id;
        this.attribute = builder.attribute;
        this.attributeValue = builder.attributeValue;
    }

    @Override
    public void validate() {
        // 1. 环境变量名称不能重复 todo
    }
}
