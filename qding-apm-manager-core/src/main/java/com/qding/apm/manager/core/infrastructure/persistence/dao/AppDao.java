package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * app table dao
 *
 * @author weijia
 */
@Mapper
public interface AppDao extends BaseMapper<AppEntity> {
    /**
     * 查询应用程序所关联的告警接收人
     * @param id 应用程序id
     * @return 应用程序所关联的告警接收人
     */
    @Select("select " +
            "t3.id as id, " +
            "t3.name as name, " +
            "t3.wechat_id as wechat_id, " +
            "t3.create_date as create_date, " +
            "t3.create_by as create_by, " +
            "t3.update_date as update_date, " +
            "t3.update_by as update_by " +
            "from app t1, app_alertreceiver_relation t2, alert_receiver t3 " +
            "where t1.id = t2.app_id " +
            "and t2.alert_receiver_id = t3.id " +
            "and t1.is_deleted = 0 " +
            "and t2.is_deleted = 0 " +
            "and t3.is_deleted = 0 " +
            "and t1.id=#{id}")
    List<AlertReceiverEntity> findAlertReceiverByAppId(@Param("id") Long id);
}
