package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.AlertRuleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * alert_rule table dao
 *
 * @author weijia
 */
@Mapper
public interface AlertRuleDao extends BaseMapper<AlertRuleEntity> {
    //
}
