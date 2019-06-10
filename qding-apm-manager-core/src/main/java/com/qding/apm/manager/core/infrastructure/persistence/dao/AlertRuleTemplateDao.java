package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.AlertRuleTemplateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * alert_rule_template table dao
 *
 * @author weijia
 */
@Mapper
public interface AlertRuleTemplateDao extends BaseMapper<AlertRuleTemplateEntity> {
    //
}
