package com.qding.apm.manager.core.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qding.apm.manager.core.domain.entity.AlertRuleTemplateEntity;
import com.qding.apm.manager.core.domain.event.event.DeleteAlertRuleTemplateEvent;
import com.qding.apm.manager.core.domain.event.event.SaveAlertRuleTemplateEvent;
import com.qding.apm.manager.core.domain.repository.AlertRuleTemplateRepository;
import com.qding.apm.manager.core.infrastructure.base.AuditFields;
import com.qding.apm.manager.core.infrastructure.base.context.OperContext;
import com.qding.apm.manager.core.infrastructure.base.exception.BizException;
import com.qding.apm.manager.core.infrastructure.base.vo.OperInfoVo;
import com.qding.apm.manager.core.infrastructure.persistence.dao.AlertRuleTemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 告警规则模版领域模型仓库实现
 *
 * @author weijia
 */
@Repository
public class AlertRuleTemplateRepositoryImpl implements AlertRuleTemplateRepository<AlertRuleTemplateEntity> {
    @Autowired
    private AlertRuleTemplateDao alertRuleTemplateDao;

    /**
     * spring application context
     */
    @Autowired
    private ApplicationContext publisher;

    /**
     * 查找全部告警规则模版
     *
     * @return 返回全部告警规则模版
     */
    @Override
    public List<AlertRuleTemplateEntity> findAll() {
        final AlertRuleTemplateWrapper alertRuleTemplateWrapper = new AlertRuleTemplateWrapper();

        return alertRuleTemplateWrapper.findAll();
    }

    /**
     * 保存告警规则模版
     *
     * @param alertRuleTemplateEntity 告警规则模版
     * @return 告警规则模版id
     */
    @Override
    public void save(final AlertRuleTemplateEntity alertRuleTemplateEntity) {
        final AlertRuleTemplateWrapper alertRuleTemplateWrapper = new AlertRuleTemplateWrapper();

        if (alertRuleTemplateEntity.getId() == null) {
            alertRuleTemplateWrapper.insert(alertRuleTemplateEntity);
        } else {
            alertRuleTemplateWrapper.update(alertRuleTemplateEntity);
        }

        // 发布告警规则模版保存领域事件
        publisher.publishEvent(new SaveAlertRuleTemplateEvent(alertRuleTemplateEntity.getId()));
    }

    /**
     * 删除告警规则模版
     *
     * @param id 告警规则模版id
     */
    @Override
    public void delete(final long id) {
        final AlertRuleTemplateWrapper alertRuleTemplateWrapper = new AlertRuleTemplateWrapper();
        alertRuleTemplateWrapper.delete(id);

        // 发布删除告警规则领域事件
        publisher.publishEvent(new DeleteAlertRuleTemplateEvent(id));
    }

    /**
     * 通过id查询告警规则模版
     *
     * @param id 告警规则模版id
     * @return 告警规则模版
     */
    @Override
    public AlertRuleTemplateEntity findById(final long id) {
        final AlertRuleTemplateWrapper alertRuleTemplateWrapper = new AlertRuleTemplateWrapper();

        return alertRuleTemplateWrapper.findById(id);
    }

    class AlertRuleTemplateWrapper {
        public List<AlertRuleTemplateEntity> findAll() {
            final QueryWrapper<AlertRuleTemplateEntity> cond = new QueryWrapper<>();
            cond.eq(AlertRuleTemplateEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return alertRuleTemplateDao.selectList(cond);
        }

        public AlertRuleTemplateEntity findById(long id) {
            final QueryWrapper<AlertRuleTemplateEntity> cond = new QueryWrapper<>();
            cond.eq(AlertRuleTemplateEntity.ID, id).eq(AlertRuleTemplateEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return alertRuleTemplateDao.selectOne(cond);
        }

        public void insert(final AlertRuleTemplateEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            alertRuleTemplateDao.insert(entity);
        }

        public void update(final AlertRuleTemplateEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());

            final UpdateWrapper<AlertRuleTemplateEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertRuleTemplateEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final int records = alertRuleTemplateDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }

        public void delete(final long id) {
            final OperInfoVo operInfoVo = OperContext.get();

            final UpdateWrapper<AlertRuleTemplateEntity> cond = new UpdateWrapper<>();
            cond.eq(AlertRuleTemplateEntity.ID, id).eq(AlertRuleTemplateEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final AlertRuleTemplateEntity entity = new AlertRuleTemplateEntity();
            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setIsDeleted(AuditFields.DELETED);

            final int records = alertRuleTemplateDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }
    }
}
