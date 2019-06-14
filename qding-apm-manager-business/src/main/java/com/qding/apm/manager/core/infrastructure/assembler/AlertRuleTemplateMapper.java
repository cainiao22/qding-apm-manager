package com.qding.apm.manager.core.infrastructure.assembler;

import com.qding.apm.manager.core.application.dto.AlertRuleTemplateDto;
import com.qding.apm.manager.core.domain.entity.AlertRuleTemplateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * pojo对象转换
 *
 * @author weijia
 */
@Mapper
public interface AlertRuleTemplateMapper {
    AlertRuleTemplateMapper INSTANCE = Mappers.getMapper(AlertRuleTemplateMapper.class);

    AlertRuleTemplateDto toAlertRuleTemplateDto(final AlertRuleTemplateEntity alertRuleTemplateEntity);

    List<AlertRuleTemplateDto> toAlertRuleTemplateDto(final List<AlertRuleTemplateEntity> alertRuleTemplateEntityList);

}
