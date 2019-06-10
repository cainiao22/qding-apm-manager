package com.qding.apm.manager.core.application.service;

import com.qding.apm.manager.core.application.dto.AlertReceiverDto;
import com.qding.apm.manager.core.application.dto.AlertReceiverModelDto;
import com.qding.apm.manager.core.application.dto.SaveAlertReceiverDto;

import java.util.List;

/**
 * 管理告警接收人
 *
 * @author weijia
 */
public interface AlertReceiverService {
    /**
     * 查找全部告警接收人
     *
     * @return 返回全部告警接收人
     */
    List<AlertReceiverDto> findAllAlertReceiver();

    /**
     * 通过告警接收人id查询告警接收人及关联的应用程序
     *
     * @param id 告警接收人id
     * @return 返回告警接收人及关联的应用程序
     */
    AlertReceiverModelDto findAlertReceiverById(long id);

    /**
     * 保存告警接收人
     *
     * @param alertReceiverDto 告警接收人
     * @return 告警接收人id
     */
    long saveAlertReceiver(final SaveAlertReceiverDto alertReceiverDto);

    /**
     * 删除告警接收人
     *
     * @param id 告警接收人id
     */
    void deleteAlertReceiverById(final long id);
}
