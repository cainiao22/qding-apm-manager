package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * alert_receiver table dao
 *
 * @author weijia
 */
@Mapper
public interface AlertReceiverDao extends BaseMapper<AlertReceiverEntity> {
    /**
     * 查询告警接收人关联的应用程序
     *
     * @param id 告警接收人id
     * @return 返回告警接收人关联的应用程序
     */
    @Select("select " +
            "t1.id as id, " +
            "t1.name as name " +
            "from app t1, app_alertreceiver_relation t2, alert_receiver t3 " +
            "where t1.id = t2.app_id " +
            "and t2.alert_receiver_id = t3.id " +
            "and t1.is_deleted = 0 " +
            "and t2.is_deleted = 0 " +
            "and t3.is_deleted = 0 " +
            "and t3.id=#{id}")
    List<AppEntity> findAppByAlertReceiverId(@Param("id") long id);
}
