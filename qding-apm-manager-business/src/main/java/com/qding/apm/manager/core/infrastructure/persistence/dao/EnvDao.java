package com.qding.apm.manager.core.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qding.apm.manager.core.domain.entity.EnvEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * env table dao
 *
 * @author weijia
 */
@Mapper
public interface EnvDao extends BaseMapper<EnvEntity> {
    //
}
