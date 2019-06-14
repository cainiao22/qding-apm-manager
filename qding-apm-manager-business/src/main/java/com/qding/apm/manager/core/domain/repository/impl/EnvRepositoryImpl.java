package com.qding.apm.manager.core.domain.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qding.apm.manager.core.domain.entity.EnvEntity;
import com.qding.apm.manager.core.domain.event.event.DeleteEnvEvent;
import com.qding.apm.manager.core.domain.event.event.SaveEnvEvent;
import com.qding.apm.manager.core.domain.repository.EnvRepository;
import com.qding.apm.manager.core.infrastructure.persistence.dao.EnvDao;
import com.qding.platform.framework.base.AuditFields;
import com.qding.platform.framework.base.context.OperContext;
import com.qding.platform.framework.base.exception.BizException;
import com.qding.platform.framework.base.vo.OperInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理环境变量仓库
 *
 * @author weijia
 */
@Repository
public class EnvRepositoryImpl implements EnvRepository<EnvEntity> {
    @Autowired
    private EnvDao envDao;

    @Autowired
    private ApplicationContext publisher;

    /**
     * 查找全部环境变量设置
     *
     * @return 返回全部环境变量设置
     */
    @Override
    public List<EnvEntity> findAll() {
        final EnvWrapper envWrapper = new EnvWrapper();

        return envWrapper.findAll();
    }

    /**
     * 保存环境变量配置
     *
     * @param envEntity 环境变量
     * @return 返回环境变量id
     */
    @Override
    public void save(final EnvEntity envEntity) {
        final EnvWrapper envWrapper = new EnvWrapper();

        if (envEntity.getId() == null) {
            envWrapper.insert(envEntity);
        } else {
            envWrapper.update(envEntity);
        }

        publisher.publishEvent(new SaveEnvEvent(envEntity.getId()));
    }

    @Override
    public EnvEntity findById(long id) {
        final EnvWrapper envWrapper = new EnvWrapper();

        return envWrapper.findById(id);
    }

    /**
     * 删除环境变量
     *
     * @param id 环境变量id
     */
    @Override
    public void delete(final long id) {
        final OperInfoVo operInfoVo = OperContext.get();

        final UpdateWrapper<EnvEntity> cond = new UpdateWrapper<>();
        cond.eq(EnvEntity.ID, id).eq(EnvEntity.IS_DELETED, AuditFields.NOT_DELETED);

        final EnvEntity entity = new EnvEntity();
        entity.setUpdateDate(operInfoVo.getDate());
        entity.setUpdateBy(operInfoVo.getUser());
        entity.setIsDeleted(AuditFields.DELETED);

        final int records = envDao.update(entity, cond);
        if (records != 1) {
            throw new BizException("系统中无此数据");
        }

        publisher.publishEvent(new DeleteEnvEvent(id));
    }

    class EnvWrapper {
        public List<EnvEntity> findAll() {
            final QueryWrapper<EnvEntity> cond = new QueryWrapper<>();
            cond.eq(EnvEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return envDao.selectList(cond);
        }

        public EnvEntity findById(final long id) {
            final QueryWrapper<EnvEntity> cond = new QueryWrapper<>();
            cond.eq(EnvEntity.ID, id).eq(EnvEntity.IS_DELETED, AuditFields.NOT_DELETED);

            return envDao.selectOne(cond);
        }

        public void insert(final EnvEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setCreateBy(operInfoVo.getUser());
            entity.setCreateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());
            entity.setUpdateDate(operInfoVo.getDate());

            envDao.insert(entity);
        }

        public void update(final EnvEntity entity) {
            final OperInfoVo operInfoVo = OperContext.get();

            entity.setUpdateDate(operInfoVo.getDate());
            entity.setUpdateBy(operInfoVo.getUser());

            final UpdateWrapper<EnvEntity> cond = new UpdateWrapper<>();
            cond.eq(EnvEntity.IS_DELETED, AuditFields.NOT_DELETED);

            final int records = envDao.update(entity, cond);
            if (records != 1) {
                throw new BizException("系统中无此数据");
            }
        }
    }
}
