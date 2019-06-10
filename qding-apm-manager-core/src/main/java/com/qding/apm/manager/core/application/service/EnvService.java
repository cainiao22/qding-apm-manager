package com.qding.apm.manager.core.application.service;

import com.qding.apm.manager.core.application.dto.EnvDto;

import java.util.List;

/**
 * 管理环境变量service
 *
 * @author weijia
 */
public interface EnvService {
    /**
     * 查找全部环境变量设置
     *
     * @return 返回全部环境变量设置
     */
    List<EnvDto> findAllEnv();

    /**
     * 保存环境变量配置
     *
     * @param envDto 环境变量
     * @return 返回环境变量id
     */
    long saveEnv(final EnvDto envDto);

    /**
     * 删除环境变量
     *
     * @param id 环境变量id
     */
    void deleteEnvById(final long id);
}
