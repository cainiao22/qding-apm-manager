package com.qding.apm.manager.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qding.platform.framework.base.AuditFields;
import lombok.Getter;
import lombok.Setter;

/**
 * app_alert_receiver_relation表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "app_alertreceiver_relation")
public class AppAndAlertReceiverEntity extends AuditFields {
    @TableField(exist = false)
    public static final String ID = "id";

    @TableField(exist = false)
    public static final String APP_ID = "app_id";

    @TableField(exist = false)
    public static final String ALERT_RECEIVER_ID = "alert_receiver_id";

    @TableField(exist = false)
    public static final String SIGN = "sign";

    /**
     * pk
     */
    @TableId(value = ID, type = IdType.AUTO)
    @TableField
    private Long id;

    /**
     * app table pk
     */
    @TableField
    private Long appId;

    /**
     * alert_reeceiver table pk
     */
    @TableField
    private Long alertReceiverId;

//    /**
//     * 唯一索引
//     */
//    @TableField
//    private String sign; //tdoo
}
