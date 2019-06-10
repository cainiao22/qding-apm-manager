package com.qding.apm.manager.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qding.apm.manager.core.domain.repository.AlertReceiverRepository;
import com.qding.apm.manager.core.infrastructure.base.AuditFields;
import com.qding.apm.manager.core.infrastructure.base.ddd.AggreagteRoot;
import com.qding.apm.manager.core.infrastructure.base.spring.SpringContext;
import com.qding.apm.manager.core.infrastructure.base.utils.CollectionUtils;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AlertRuleDao;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AppDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.List;

/**
 * app表实体定义
 *
 * @author weijia
 */
@Getter
@Setter
@TableName(value = "app")
public class AppEntity extends AuditFields implements AggreagteRoot {
    @TableField(exist = false)
    public static final String ID = "id";

    @TableField(exist = false)
    public static final String NAME = "name";

    /**
     * pk
     */
    @TableId(value = ID, type = IdType.AUTO)
    @TableField
    private Long id;

    /**
     * 应用名称
     */
    @TableField
    private String name;

    /**
     * 应用对应告警规则
     */
    @TableField(exist = false)
    private List<AlertRuleEntity> alertRuleList;

    /**
     * 应用关联告警接收人
     */
    @TableField(exist = false)
    private List<AlertReceiverEntity> alertReceiverList;

    /**
     * 新增告警规则列表
     */
    @TableField(exist = false)
    private List<AlertRuleEntity> addAlertRuleList;

    /**
     * 更新告警规则列表
     */
    @TableField(exist = false)
    private List<AlertRuleEntity> updateAlertRuleList;

    /**
     * 移除告警规则列表
     */
    @TableField(exist = false)
    private List<Long> removeAlertRuleList;

    /**
     * 新增告警接收人列表
     */
    @TableField(exist = false)
    private List<Long> addAlertReceiverList;

    /**
     * 移除告警接收人列表
     */
    @TableField(exist = false)
    private List<Long> removeAlertReceiverList;

    /**
     * 默认构造函数
     */
    public AppEntity() {
        //
    }

    /**
     * 利用builder pattern对内部对象赋值
     *
     * @param builder builder对象
     */
    private AppEntity(AppEntity.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.addAlertRuleList = builder.addAlertRuleList;
        this.updateAlertRuleList = builder.updateAlertRuleList;
        this.removeAlertRuleList = builder.removeAlertRuleList;
        this.addAlertReceiverList = builder.addAlertReceiverList;
        this.removeAlertReceiverList = builder.removeAlertReceiverList;
    }

    /**
     * 获取应用管理告警规则列表
     *
     * @return 应用管理告警规则列表
     */
    public List<AlertRuleEntity> getAlertRuleList() {
        // 实现alertRuleList惰性加载
        if (alertRuleList != null) {
            return alertRuleList;
        }

        final AlertRuleDao alertRuleDao = (AlertRuleDao) SpringContext.getBean("alertRuleDao");

        final QueryWrapper<AlertRuleEntity> cond = new QueryWrapper<>();
        cond.eq(AlertRuleEntity.APP_ID, id).eq(AuditFields.IS_DELETED, AuditFields.NOT_DELETED);

        alertRuleList = alertRuleDao.selectList(cond);

        return alertRuleList;
    }

    /**
     * 获取应用关联告警接收人列表
     *
     * @return 应用关联告警接收人列表
     */
    public List<AlertReceiverEntity> getAlertReceiverList() {
        // 实现alertReceiverList惰性加载
        if (alertReceiverList != null) {
            return alertReceiverList;
        }

        final AppDao appDao = (AppDao) SpringContext.getBean("appDao");
        alertReceiverList = appDao.findAlertReceiverByAppId(id);

        return alertReceiverList;
    }

    /**
     * 校验整个模型合法性
     */
    @Override
    public void validate() {
        if (id == null) {
            createValidate();
        } else {
            updateValidate();
        }
    }

    private void createValidate() {
        Assert.notEmpty(addAlertRuleList, "应用程序必须关联一个以上的告警规则");
        Assert.notEmpty(addAlertReceiverList, "应用程序必须关联一个以上的告警接收人");

        final AlertReceiverRepository alertReceiverRepository = (AlertReceiverRepository) SpringContext.getBean("alertReceiverRepositoryImpl");
        final List<AlertReceiverEntity> alertReceiverEntityList = alertReceiverRepository.findAll();

        Assert.isTrue(addAlertReceiverList.stream().allMatch(id -> alertReceiverEntityList.stream().anyMatch(elem -> elem.getId().equals(id))),
                "告警接收人不存在");
    }

    private void updateValidate() {
        final List<AlertRuleEntity> alertRuleEntityList = getAlertRuleList();

        Assert.isTrue(CollectionUtils.size(alertRuleEntityList) + CollectionUtils.size(addAlertRuleList) - CollectionUtils.size(removeAlertRuleList) > 0,
                "应用程序必须关联一个以上的告警规则");
        Assert.isTrue(removeAlertRuleList.stream().allMatch(e -> alertRuleEntityList.contains(e)),
                "移除告警规则不在应用关联告警规则列表中");
        Assert.isTrue(updateAlertRuleList.stream().allMatch(e -> alertRuleEntityList.stream().anyMatch(elem -> elem.getId().equals(e.getId()))),
                "更新告警规则不在应用关联告警规则列表中");

        final List<AlertReceiverEntity> alertReceiverEntityList = getAlertReceiverList();

        Assert.isTrue(CollectionUtils.size(alertReceiverEntityList) + CollectionUtils.size(addAlertReceiverList) - CollectionUtils.size(removeAlertReceiverList) > 0,
                "应用程序必须关联一个以上的告警接收人");
        Assert.isTrue(removeAlertReceiverList.stream().allMatch(e -> alertReceiverEntityList.contains(e)),
                "移除告警接收人不在应用关联告警接收人列表中");
    }

    /**
     * Builder Pattern
     */
    public static class Builder {
        private final Long id;
        private final String name;
        private final List<AlertRuleEntity> addAlertRuleList;
        private final List<AlertRuleEntity> updateAlertRuleList;
        private final List<Long> removeAlertRuleList;
        private final List<Long> addAlertReceiverList;
        private final List<Long> removeAlertReceiverList;

        public Builder(final Long id, final String name, final List<AlertRuleEntity> addAlertRuleList, final List<AlertRuleEntity> updateAlertRuleList,
                       final List<Long> removeAlertRuleList, final List<Long> addAlertReceiverList, final List<Long> removeAlertReceiverList) {
            this.id = id;
            this.name = name;
            this.addAlertRuleList = addAlertRuleList;
            this.updateAlertRuleList = updateAlertRuleList;
            this.removeAlertRuleList = removeAlertRuleList;
            this.addAlertReceiverList = addAlertReceiverList;
            this.removeAlertReceiverList = removeAlertReceiverList;
        }

        public AppEntity build() {
            final AppEntity appEntity = new AppEntity(this);

            // 构建完模型时对模型进行完整性校验
            appEntity.validate();

            return appEntity;
        }
    }
}
