package com.qding.apm.manager.core.application.service;

import com.qding.apm.manager.core.application.dto.AlertRuleTemplateDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理告警规则模版
 *
 * @author weijia
 */
@Service
public interface AlertRuleTemplateService {
    /**
     * 查询全部告警规则模版
     *
     * @return 返回全部告警规则模版
     */
    List<AlertRuleTemplateDto> findAllAlertRuleTemplate();

    /**
     * 保存告警规则模版
     *
     * @param alertRuleTemplateDto 告警规则模版
     * @return 告警规则模版id
     */
    long saveAlertRuleTemplate(final AlertRuleTemplateDto alertRuleTemplateDto);

    /**
     * 删除告警规则模版
     *
     * @param id 告警规则模版id
     */
    void deleteAlertRuleTemplateById(final Long id);
}
