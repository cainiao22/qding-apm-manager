package com.qding.apm.manager.core.infrastructure.assembler;

import com.qding.apm.manager.core.application.dto.AlertReceiverDto;
import com.qding.apm.manager.core.application.dto.AlertReceiverModelDto;
import com.qding.apm.manager.core.domain.entity.AlertReceiverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * pojo对象转换
 *
 * @author weijia
 */
@Mapper
public interface AlertReceiverMapper {
    AlertReceiverMapper INSTANCE = Mappers.getMapper(AlertReceiverMapper.class);

    AlertReceiverDto toAlertReceiverDto(AlertReceiverEntity alertReceiverEntity);

    List<AlertReceiverDto> toAlertReceiverDto(List<AlertReceiverEntity> alertReceiverEntityList);

    AlertReceiverModelDto toAlertReceiverModelDto(AlertReceiverEntity alertReceiverEntity);
}
