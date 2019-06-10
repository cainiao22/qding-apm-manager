package com.qding.apm.manager.core.infrastructure.assembler;

import com.qding.apm.manager.core.application.dto.EnvDto;
import com.qding.apm.manager.core.domain.entity.EnvEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * pojo对象转换
 *
 * @author weijia
 */
@Mapper
public interface EnvMapper {
    EnvMapper INSTANCE = Mappers.getMapper(EnvMapper.class);

    List<EnvDto> toEnvDto(final List<EnvEntity> envEntityList);

    EnvDto toEnvDto(final EnvEntity envEntity);
}
