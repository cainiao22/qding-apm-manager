package com.qding.apm.manager.core.infrastructure.assembler;

import com.qding.apm.manager.core.application.dto.AlertRuleDto;
import com.qding.apm.manager.core.application.dto.AppDto;
import com.qding.apm.manager.core.application.dto.AppModelDto;
import com.qding.apm.manager.core.domain.entity.AlertRuleEntity;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * pojo对象转换
 *
 * @author weijia
 */
@Mapper
public interface AppMapper {
    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    AppDto toAppDto(final AppEntity appEntity);

    List<AppDto> toAppDto(final List<AppEntity> appEntityList);

    AppModelDto toAppModelDto(final AppEntity appEntity);

    AlertRuleEntity toAlertRuleEntity(AlertRuleDto alertRuleDto);

    List<AlertRuleEntity> toAlertRuleEntity(List<AlertRuleDto> alertRuleDtoList);

}
