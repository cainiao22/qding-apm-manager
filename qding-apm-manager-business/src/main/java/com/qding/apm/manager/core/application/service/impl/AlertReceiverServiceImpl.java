package com.qding.apm.manager.core.application.service.impl;

import com.qding.apm.manager.core.application.dto.AlertReceiverDto;
import com.qding.apm.manager.core.application.dto.AlertReceiverModelDto;
import com.qding.apm.manager.core.application.dto.SaveAlertReceiverDto;
import com.qding.apm.manager.core.application.service.AlertReceiverService;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import com.qding.apm.manager.core.domain.repository.AlertReceiverRepository;
import com.qding.apm.manager.core.infrastructure.assembler.AlertReceiverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理告警接收人
 *
 * @author weijia
 */
@Service
public class AlertReceiverServiceImpl implements AlertReceiverService {
    /**
     * 告警接收人仓库
     */
    @Autowired
    private AlertReceiverRepository<AlertReceiverEntity> alertReceiverRepository;

    /**
     * 查找全部告警接收人
     *
     * @return 返回全部告警接收人
     */
    @Override
    public List<AlertReceiverDto> findAllAlertReceiver() {
        final List<AlertReceiverEntity> alertReceiverEntityList = alertReceiverRepository.findAll();

        return AlertReceiverMapper.INSTANCE.toAlertReceiverDto(alertReceiverEntityList);
    }

    /**
     * 通过告警接收人id查询告警接收人及关联的应用程序
     *
     * @param id 告警接收人id
     * @return 返回告警接收人及关联的应用程序
     */
    @Override
    public AlertReceiverModelDto findAlertReceiverById(long id) {
        final AlertReceiverEntity alertReceiverEntity = alertReceiverRepository.findById(id);

        return AlertReceiverMapper.INSTANCE.toAlertReceiverModelDto(alertReceiverEntity);
    }

    /**
     * 保存告警接收人
     *
     * @param alertReceiverDto 告警接收人
     * @return 告警接收人id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long saveAlertReceiver(final SaveAlertReceiverDto alertReceiverDto) {
        final AlertReceiverEntity alertReceiverEntity = new AlertReceiverEntity.Builder(
                alertReceiverDto.getId(), alertReceiverDto.getName(), alertReceiverDto.getWechatId(),
                alertReceiverDto.getAddAppIdList(), alertReceiverDto.getRemoveAppIdList()).build();

        alertReceiverRepository.save(alertReceiverEntity);

        return alertReceiverEntity.getId();
    }

    /**
     * 删除告警接收人
     *
     * @param id 告警接收人id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAlertReceiverById(final long id) {
        alertReceiverRepository.delete(id);
    }
}
