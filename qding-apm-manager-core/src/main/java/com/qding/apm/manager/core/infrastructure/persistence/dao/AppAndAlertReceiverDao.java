package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.AppAndAlertReceiverEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * app_alert_receiver_relation table dao
 *
 * @author weijia
 */
@Mapper
public interface AppAndAlertReceiverDao extends BaseMapper<AppAndAlertReceiverEntity> {
    //
}
