package com.qding.apm.manager.core.application.service.impl;

import com.qding.apm.manager.core.application.dto.AlertRuleTemplateDto;
import com.qding.apm.manager.core.application.service.AlertRuleTemplateService;
import com.qding.apm.manager.core.domain.entity.AlertRuleTemplateEntity;
import com.qding.apm.manager.core.domain.repository.AlertRuleTemplateRepository;
import com.qding.apm.manager.core.infrastructure.assembler.AlertRuleTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理告警规则模版
 *
 * @author weijia
 */
@Service
public class AlertRuleTemplateServiceImpl implements AlertRuleTemplateService {
    @Autowired
    private AlertRuleTemplateRepository<AlertRuleTemplateEntity> alertRuleTemplateRepository;

    /**
     * 查询全部告警规则模版
     *
     * @return 返回全部告警规则模版
     */
    @Override
    public List<AlertRuleTemplateDto> findAllAlertRuleTemplate() {
        final List<AlertRuleTemplateEntity> alertRuleTemplateModelList = alertRuleTemplateRepository.findAll();

        return AlertRuleTemplateMapper.INSTANCE.toAlertRuleTemplateDto(alertRuleTemplateModelList);
    }

    /**
     * 保存告警规则模版
     *
     * @param alertRuleTemplateDto 告警规则模版
     * @return 告警规则模版id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long saveAlertRuleTemplate(final AlertRuleTemplateDto alertRuleTemplateDto) {
        final AlertRuleTemplateEntity alertRuleTemplateEntity = new AlertRuleTemplateEntity.Builder(alertRuleTemplateDto.getId(),
                alertRuleTemplateDto.getName(), alertRuleTemplateDto.getExpress(), alertRuleTemplateDto.getWait(),
                alertRuleTemplateDto.getSeverity(), alertRuleTemplateDto.getSummary(), alertRuleTemplateDto.getDescription()).build();

        alertRuleTemplateRepository.save(alertRuleTemplateEntity);

        return alertRuleTemplateEntity.getId();
    }

    /**
     * 删除告警规则模版
     *
     * @param id 告警规则模版id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAlertRuleTemplateById(final Long id) {
        alertRuleTemplateRepository.delete(id);
    }
}
