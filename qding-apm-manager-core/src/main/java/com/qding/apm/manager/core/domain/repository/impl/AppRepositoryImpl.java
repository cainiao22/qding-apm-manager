package com.qding.apm.manager.core.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AlertRuleEntity;
import com.qding.apm.manager.core.domain.entity.AppAndAlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import com.qding.apm.manager.core.domain.event.event.DeleteAppEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAppEvent;
import com.qding.apm.manager.core.domain.repository.AppRepository;
import com.qding.apm.manager.core.infrastructure.base.context.OperContext;
import com.qding.apm.manager.core.infrastructure.base.exception.BizException;
import com.qding.apm.manager.core.infrastructure.base.vo.OperInfoVo;
import com.qding.apm.manager.core.infrastructure.base.AuditFields;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AlertRuleDao;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AppAndAlertReceiverDao;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

/**
 * AppModel仓库实现
 *
 * @author weijia
 */
@Repository
public class AppRepositoryImpl implements AppRepository<AppEntity> {
    /**
     * app table dao
     */
    @Autowired
    private AppDao appDao;

    /**
     * alert_rule table dao
     */
    @Autowired
    private AlertRuleDao alertRuleDao;

    /**
     * app_alert_receiver_relation table dao
     */
    @Autowired
    private AppAndAlertReceiverDao appAndAlertReceiverDao;

    /**
     * spring application context
     */
    @Autowired
    private ApplicationContext publisher;

    /**
     * 查找全部应用程序对象
     *
     * @return 返回全部AppModel对象
     */
    @Override
    public List<AppEntity> findAll() {
        final AppWrapper appWrapper = new AppWrapper();

        return appWrapper.findAll();
    }

    /**
     * 通过应用程序id查找应用程序对象
     *
     * @param id 应用程序id
     * @return 返回查询到的应用程序对象，查询不到返回null
     */
    @Override
    public AppEntity findById(long id) {
        // 查询应用
        final AppWrapper appWrapper = new AppWrapper();
        final AppEntity appEntity = appWrapper.findById(id);
        if (appEntity == null) {
            return null;
        }

        // 查询告警规则
        final AlertRuleWrapper alertRuleWrapper = new AlertRuleWrapper();
        final List<AlertRuleEntity> alertRuleList = alertRuleWrapper.findById(id);
        appEntity.setAlertRuleList(alertRuleList);

        // 查询告警接收人
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        final List<AlertReceiverEntity> alertReceiverEntityList = alertReceiverWrapper.findAlertReceiverByAppId(id);
        appEntity.setAlertReceiverList(alertReceiverEntityList);

        return appEntity;
    }

    /**
     * 保存应用程序对象变更
     *
     * @param appEntity 应用程序对象
     */
    @Override
    public void save(final AppEntity appEntity) {
        if (appEntity.getId() == null) {
            create(appEntity);
        } else {
            update(appEntity);
        }

        // 保存模型后发布领域事件
        publisher.publishEvent(new SaveAppEvent(appEntity.getId()));
    }

    /**
     * 删除应用程序对象
     *
     * @param id 应用程序id
     */
    @Override
    public void delete(final long id) {
        // 逻辑删除应用
        final AppWrapper appWrapper = new AppWrapper();
        appWrapper.delete(id);

        // 逻辑删除应用关联告警规则
        final AlertRuleWrapper alertRuleWrapper = new AlertRuleWrapper();
        alertRuleWrapper.delete(() -> {
            final UpdateWrapper<AlertRuleEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertRuleEntity.APP_ID, id).eq(AlertRuleEntity.IS_DELETED, AuditFields.NOT_DELETED);
            return cond;
        });

        // 逻辑删除应用关联告警接收人
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        alertReceiverWrapper.delete(() -> {
            final UpdateWrapper<AppAndAlertReceiverEntity> cond = new UpdateWrapper<>();
            cond.eq(AppAndAlertReceiverEntity.APP_ID, id).eq(AppAndAlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);
            return cond;
        });

        // 变更模型后发布领域事件
        publisher.publishEvent(new DeleteAppEvent(id));
    }

    private void create(final AppEntity appEntity) {
        final AppWrapper appWrapper = new AppWrapper();
        appWrapper.insert(appEntity);

        // 插入告警规则
        final AlertRuleWrapper alertRuleWrapper = new AlertRuleWrapper();
        appEntity.getAddAlertRuleList().stream().forEach(elem -> alertRuleWrapper.insert(appEntity.getId(), elem));

        // 插入告警接收人
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        if (appEntity.getAddAlertReceiverList() != null) {
            appEntity.getAddAlertReceiverList().stream().forEach(elem -> alertReceiverWrapper.insert(appEntity.getId(), elem));
        }
    }

    private void update(final AppEntity appEntity) {
        final AppWrapper appWrapper = new AppWrapper();
        appWrapper.update(appEntity);

        final AlertRuleWrapper alertRuleWrapper = new AlertRuleWrapper();

        // 插入告警规则
        if (appEntity.getAddAlertRuleList() != null) {
            appEntity.getAddAlertRuleList().stream().forEach(elem -> alertRuleWrapper.insert(appEntity.getId(), elem));
        }

        // 更新告警规则
        if (appEntity.getUpdateAlertRuleList() != null) {
            appEntity.getUpdateAlertRuleList().stream().forEach(elem -> alertRuleWrapper.update(elem));
        }

        // 逻辑删除应用关联告警规则
        if (appEntity.getRemoveAlertRuleList() != null) {
            appEntity.getRemoveAlertRuleList().stream().forEach(elem -> {
                alertRuleWrapper.delete(() -> {
                    final UpdateWrapper<AlertRuleEntity> cond = new UpdateWrapper<>();
                    cond.eq(AlertRuleEntity.ID, elem).eq(AlertRuleEntity.IS_DELETED, AuditFields.NOT_DELETED);
                    return cond;
                });
            });
        }

        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();

        // 插入告警接收人
        if (appEntity.getAddAlertReceiverList() != null) {
            appEntity.getAddAlertReceiverList().stream().forEach(elem -> alertReceiverWrapper.insert(appEntity.getId(), elem));
        }

        // 删除告警接收人
        if (appEntity.getRemoveAlertReceiverList() != null) {
            appEntity.getRemoveAlertReceiverList().stream().forEach(elem -> {
                alertReceiverWrapper.delete(() -> {
                    final UpdateWrapper<AppAndAlertReceiverEntity> cond = new UpdateWrapper<>();
                    cond.eq(AppAndAlertReceiverEntity.APP_ID, appEntity.getId()).eq(AppAndAlertReceiverEntity.ALERT_RECEIVER_ID, elem)
                            .eq(AppAndAlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);
                    return cond;
                });
            });
        }
    }

    /**
     * 封装对app表基本操作
     */
    class AppWrapper {
        public List<AppEntity> findAll() {
            final QueryWrapper<AppEntity> cond = new QueryWrapper<>();
            cond.eq(AppEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return appDao.selectList(cond);
        }

        public AppEntity findById(final long id) {
            final QueryWrapper<AppEntity> cond = new QueryWrapper<>();
            cond.eq(AppEntity.ID, id).eq(AppEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return appDao.selectOne(cond);
        }

        public void insert(final AppEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            appDao.insert(entity);
        }

        public void update(final AppEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AppEntity> cond = new UpdateWrapper<>();
            cond.eq(AppEntity.ID, entity.getId()).eq(AppEntity.IS_DELETED, AuditFields.NOT_DELETED);

            entity.setName(entity.getName());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            if (appDao.update(entity, cond) != 1) {
                throw new BizException("系统中无此数据");
            }
        }

        public void delete(final long id) {
            final OperInfoVo operInfoVo = OperContext.get();

            // 逻辑删除app table记录
            final UpdateWrapper<AppEntity> cond = new UpdateWrapper<>();
            cond.eq(AppEntity.ID, id).eq(AppEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final AppEntity entity = new AppEntity();
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setIsDeleted(AuditFields.DELETED);

            int records = appDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }
    }

    /**
     * 封装对alert_rule表基本操作
     */
    class AlertRuleWrapper {
        public List<AlertRuleEntity> findById(final long id) {
            final QueryWrapper<AlertRuleEntity> cond = new QueryWrapper<>();
            cond.eq(AlertRuleEntity.APP_ID, id).eq(AlertRuleEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return alertRuleDao.selectList(cond);
        }

        public void insert(final long appId, final AlertRuleEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setAppId(appId);
            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            alertRuleDao.insert(entity);
        }

        public void update(final AlertRuleEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AlertRuleEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertRuleEntity.ID, entity.getId()).eq(AlertRuleEntity.IS_DELETED, AuditFields.NOT_DELETED);

            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            alertRuleDao.update(entity, cond);
        }

        public void delete(final Supplier<UpdateWrapper<AlertRuleEntity>> supplier) {
            final OperInfoVo operInfoVo = OperContext.get();

            final AlertRuleEntity entity = new AlertRuleEntity();
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setIsDeleted(AuditFields.DELETED);

            alertRuleDao.update(entity, supplier.get());
        }
    }

    /**
     * 封装对app_alert_receiver_relation表操作
     */
    class AlertReceiverWrapper {
        public List<AlertReceiverEntity> findAlertReceiverByAppId(final long id) {
            return appDao.findAlertReceiverByAppId(id);
        }

        public void insert(final long appId, final long alertReceiverId) {
            final OperInfoVo operInfoVo = OperContext.get();

            final AppAndAlertReceiverEntity entity = new AppAndAlertReceiverEntity();
            entity.setAppId(appId);
            entity.setAlertReceiverId(alertReceiverId);
            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            appAndAlertReceiverDao.insert(entity);
        }

        public void delete(final Supplier<UpdateWrapper<AppAndAlertReceiverEntity>> supplier) {
            final OperInfoVo operInfoVo = OperContext.get();

            final AppAndAlertReceiverEntity entity = new AppAndAlertReceiverEntity();
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setIsDeleted(AuditFields.DELETED);

            appAndAlertReceiverDao.update(entity, supplier.get());
        }
    }
}
