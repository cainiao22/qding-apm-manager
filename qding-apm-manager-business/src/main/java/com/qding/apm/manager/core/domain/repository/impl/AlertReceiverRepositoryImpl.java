package com.qding.apm.manager.core.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AppAndAlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import com.qding.apm.manager.core.domain.event.event.DeleteAlertReceiverEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAlertReceiverEvent;
import com.qding.apm.manager.core.domain.repository.AlertReceiverRepository;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AlertReceiverDao;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AppAndAlertReceiverDao;
import com.qding.platform.framework.base.AuditFields;
import com.qding.platform.framework.base.context.OperContext;
import com.qding.platform.framework.base.exception.BizException;
import com.qding.platform.framework.base.vo.OperInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 告警接收人领域模型仓库实现
 *
 * @author weijia
 */
@Repository
public class AlertReceiverRepositoryImpl implements AlertReceiverRepository<AlertReceiverEntity> {
    /**
     * alert_receiver table dao
     */
    @Autowired
    private AlertReceiverDao alertReceiverDao;

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
     * 查找全部告警接收人
     *
     * @return 返回全部告警接收人
     */
    @Override
    public List<AlertReceiverEntity> findAll() {
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        return alertReceiverWrapper.findAll();
    }

    /**
     * 通过告警接收人id查询告警接收人及关联的应用程序
     *
     * @param id 告警接收人id
     * @return 返回告警接收人及关联的应用程序
     */
    @Override
    public AlertReceiverEntity findById(final long id) {
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        final AlertReceiverEntity alertReceiverEntity = alertReceiverWrapper.findById(id);

        if (alertReceiverEntity == null) {
            return null;
        }

        final AppWrapper appWrapper = new AppWrapper();
        alertReceiverEntity.setAppList(appWrapper.findAppByAlertReceiverId(id));

        return alertReceiverEntity;
    }

    /**
     * 保存告警接收人
     *
     * @param alertReceiverEntity 告警接收人
     */
    @Override
    public void save(final AlertReceiverEntity alertReceiverEntity) {
        if (alertReceiverEntity.getId() == null) {
            create(alertReceiverEntity);
        } else {
            update(alertReceiverEntity);
        }

        // 发布告警接收人保存事件
        publisher.publishEvent(new SaveAlertReceiverEvent(alertReceiverEntity.getId()));
    }

    /**
     * 删除告警接收人
     *
     * @param id 告警接收人id
     */
    @Override
    public void delete(final long id) {
        final OperInfoVo operInfoVo = OperContext.get();

        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        alertReceiverWrapper.delete(id);

        final AppAndAlertReceiverWrapper appAndAlertReceiverWrapper = new AppAndAlertReceiverWrapper();
        appAndAlertReceiverWrapper.delete(id);

        // 发布告警接收人删除事件
        publisher.publishEvent(new DeleteAlertReceiverEvent(id));
    }

    private void create(final AlertReceiverEntity alertReceiverEntity) {
        final OperInfoVo operInfoVo = OperContext.get();

        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        alertReceiverWrapper.insert(alertReceiverEntity);

        if (alertReceiverEntity.getAddAppIdList() != null) {
            final AppAndAlertReceiverWrapper appAndAlertReceiverWrapper = new AppAndAlertReceiverWrapper();
            alertReceiverEntity.getAddAppIdList().stream().forEach(appId -> appAndAlertReceiverWrapper.insert(appId, alertReceiverEntity.getId()));
        }
    }

    private void update(final AlertReceiverEntity alertReceiverEntity) {
        final AlertReceiverWrapper alertReceiverWrapper = new AlertReceiverWrapper();
        alertReceiverWrapper.update(alertReceiverEntity);

        final AppAndAlertReceiverWrapper appAndAlertReceiverWrapper = new AppAndAlertReceiverWrapper();

        if (alertReceiverEntity.getAddAppIdList() != null) {
            alertReceiverEntity.getAddAppIdList().stream().forEach(elem -> appAndAlertReceiverWrapper.insert(elem, alertReceiverEntity.getId()));
        }

        if (alertReceiverEntity.getRemoveAppIdList() != null) {
            alertReceiverEntity.getRemoveAppIdList().stream().forEach(appId -> appAndAlertReceiverWrapper.delete(appId, alertReceiverEntity.getId()));
        }
    }

    class AlertReceiverWrapper {
        public List<AlertReceiverEntity> findAll() {
            final QueryWrapper<AlertReceiverEntity> cond = new QueryWrapper<>();
            cond.eq(AlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return alertReceiverDao.selectList(cond);
        }

        public AlertReceiverEntity findById(final long id) {
            final QueryWrapper<AlertReceiverEntity> cond = new QueryWrapper<>();
            cond.eq(AlertReceiverEntity.ID, id).eq(AlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return alertReceiverDao.selectOne(cond);
        }

        public void insert(final AlertReceiverEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setIsDeleted(AuditFields.NOT_DELETED);

            alertReceiverDao.insert(entity);
        }

        public void update(final AlertReceiverEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AlertReceiverEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertReceiverEntity.ID, entity.getId()).eq(AlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            final int records = alertReceiverDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }

        public void delete(final long id) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AlertReceiverEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertReceiverEntity.ID, id).eq(AlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final AlertReceiverEntity entity = new AlertReceiverEntity();
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setIsDeleted(AuditFields.DELETED);

            int records = alertReceiverDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }
    }

    class AppAndAlertReceiverWrapper {
        public void insert(final long appId, long alertReceiverId) {
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

        public void delete(final long appId, final long alertReceiverId) {
            final OperInfoVo operInfoVo = OperContext.get();

            final AppAndAlertReceiverEntity entity = new AppAndAlertReceiverEntity();
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setIsDeleted(AuditFields.DELETED);

            final UpdateWrapper<AppAndAlertReceiverEntity> cond = new UpdateWrapper<>();
            cond.eq(AppAndAlertReceiverEntity.APP_ID, appId).eq(AppAndAlertReceiverEntity.ALERT_RECEIVER_ID, alertReceiverId)
                    .eq(AppAndAlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            appAndAlertReceiverDao.update(entity, cond);
        }

        public void delete(final long alertReceiverId) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AppAndAlertReceiverEntity> relationCond = new UpdateWrapper<>();
            relationCond.eq(AppAndAlertReceiverEntity.ALERT_RECEIVER_ID, alertReceiverId).eq(AppAndAlertReceiverEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final AppAndAlertReceiverEntity appAndAlertReceiverEntity = new AppAndAlertReceiverEntity();
            appAndAlertReceiverEntity.setUpdateDate(operInfoVo.getDate());
            appAndAlertReceiverEntity.setUpdateBy(operInfoVo.getUser());
            appAndAlertReceiverEntity.setIsDeleted(AuditFields.DELETED);

            appAndAlertReceiverDao.update(appAndAlertReceiverEntity, relationCond);
        }
    }

    class AppWrapper {
        public List<AppEntity> findAppByAlertReceiverId(final long id) {
            return alertReceiverDao.findAppByAlertReceiverId(id);
        }
    }
}
