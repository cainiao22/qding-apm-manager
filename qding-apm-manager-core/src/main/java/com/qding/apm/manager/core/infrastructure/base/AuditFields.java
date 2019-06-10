package com.qding.apm.manager.core.infrastructure.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 数据库审计字段定义
 *
 * @author weijia
 */
@Getter
@Setter
public class AuditFields {
    /**
     * 逻辑上被删除标记
     */
    @TableField(exist = false)
    public static final int DELETED = 1;

    /**
     * 逻辑上未被删除标记
     */
    @TableField(exist = false)
    public static final int NOT_DELETED = 0;

    @TableField(exist = false)
    public static final String CREATE_DATE = "create_date";

    @TableField(exist = false)
    public static final String CREATE_BY = "create_by";

    @TableField(exist = false)
    public static final String UPDATE_DATE = "update_date";

    @TableField(exist = false)
    public static final String UPDATE_BY = "update_by";

    @TableField(exist = false)
    public static final String IS_DELETED = "is_deleted";

    /**
     * 记录建立时间
     */
    @TableField(value = CREATE_DATE)
    private Date createDate;

    /**
     * 记录建立人
     */
    @TableField(value = CREATE_BY)
    private String createBy;

    /**
     * 记录上一次更新时间
     */
    @TableField(value = UPDATE_DATE)
    private Date updateDate;

    /**
     * 记录上一次更新人
     */
    @TableField(value = UPDATE_BY)
    private String updateBy;

    /**
     * 逻辑删除标志
     */
    @TableField
    private Integer isDeleted = NOT_DELETED;
}
