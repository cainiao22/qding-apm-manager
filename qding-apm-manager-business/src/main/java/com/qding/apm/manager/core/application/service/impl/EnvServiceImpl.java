package com.qding.apm.manager.core.application.service.impl;

import com.qding.apm.manager.core.application.dto.EnvDto;
import com.qding.apm.manager.core.application.service.EnvService;
import com.qding.apm.manager.core.domain.entity.EnvEntity;
import com.qding.apm.manager.core.domain.repository.EnvRepository;
import com.qding.apm.manager.core.infrastructure.assembler.EnvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理环境变量service
 *
 * @author weijia
 */
@Service
public class EnvServiceImpl implements EnvService {
    @Autowired
    private EnvRepository<EnvEntity> envRepository;

    /**
     * 查找全部环境变量设置
     *
     * @return 返回全部环境变量设置
     */
    @Override
    public List<EnvDto> findAllEnv() {
        final List<EnvEntity> envEntityList = envRepository.findAll();

        return EnvMapper.INSTANCE.toEnvDto(envEntityList);
    }

    /**
     * 保存环境变量配置
     *
     * @param envDto 环境变量
     * @return 返回环境变量id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long saveEnv(final EnvDto envDto) {
        final EnvEntity envEntity = new EnvEntity.Builder(envDto.getId(), envDto.getAttribute(), envDto.getAttributeValue()).build();

        envRepository.save(envEntity);

        return envEntity.getId();
    }

    /**
     * 删除环境变量
     *
     * @param id 环境变量id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEnvById(final long id) {
        envRepository.delete(id);
    }
}
