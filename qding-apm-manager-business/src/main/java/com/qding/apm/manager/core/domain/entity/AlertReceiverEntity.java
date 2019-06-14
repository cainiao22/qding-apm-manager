package com.qding.apm.manager.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AlertReceiverDao;
import com.qding.platform.framework.base.AuditFields;
import com.qding.platform.framework.base.ddd.AggreagteRoot;
import com.qding.platform.framework.base.spring.SpringContext;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * alert_receiver表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "alert_receiver")
public class AlertReceiverEntity extends AuditFields implements AggreagteRoot {
    @TableField(exist = false)
    public static final String ID = "id";

    @TableField(exist = false)
    public static final String NAME = "name";

    @TableField(exist = false)
    public static final String WECHAT_ID = "wechat_id";

    /**
     * pk
     */
    @TableId(value = "id", type = IdType.AUTO)
    @TableField
    private Long id;

    /**
     * 企业微信姓名
     */
    @TableField
    private String name;

    /**
     * 企业微信ID
     */
    @TableField
    private String wechatId;

    /**
     * 告警接收人所关联应用列表
     */
    @TableField(exist = false)
    private List<AppEntity> appList;

    /**
     * 新增告警接收人所关联应用列表
     */
    @TableField(exist = false)
    private List<Long> addAppIdList;

    /**
     * 移除告警接收人所关联应用列表
     */
    @TableField(exist = false)
    private List<Long> removeAppIdList;

    /**
     * Builder Pattern
     */
    public static class Builder {
        private final Long id;
        private final String name;
        private final String wechatId;
        private final List<Long> addAppIdList;
        private final List<Long> removeAppIdList;

        public Builder(final Long id, final String name, final String wechatId,
                       final List<Long> addAppIdList, final List<Long> removeAppIdList) {
            this.id = id;
            this.name = name;
            this.wechatId = wechatId;
            this.addAppIdList = addAppIdList;
            this.removeAppIdList = removeAppIdList;
        }

        public AlertReceiverEntity build() {
            final AlertReceiverEntity alertReceiverEntity = new AlertReceiverEntity(this);

            // 构建完模型时对模型进行完整性校验
            alertReceiverEntity.validate();

            return alertReceiverEntity;
        }
    }

    public AlertReceiverEntity() {
        //
    }

    private AlertReceiverEntity(AlertReceiverEntity.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.wechatId = builder.wechatId;
        this.addAppIdList = builder.addAppIdList;
        this.removeAppIdList = builder.removeAppIdList;
    }

    public List<AppEntity> getAppList() {
        // 实现appList惰性加载
        if (appList != null) {
            return appList;
        }

        final AlertReceiverDao alertReciverDao = (AlertReceiverDao) SpringContext.getBean("alertReciverDao");
        appList = alertReciverDao.findAppByAlertReceiverId(id);

        return appList;
    }

    /**
     * 校验整个模型合法性
     */
    @Override
    public void validate() {
        // todo 应用id存在
    }
}
