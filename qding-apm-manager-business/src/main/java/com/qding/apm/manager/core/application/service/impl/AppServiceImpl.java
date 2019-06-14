package com.qding.apm.manager.core.application.service.impl;

import com.qding.apm.manager.core.application.dto.AppDto;
import com.qding.apm.manager.core.application.dto.AppModelDto;
import com.qding.apm.manager.core.application.dto.CreateAppDto;
import com.qding.apm.manager.core.application.dto.UpdateAppDto;
import com.qding.apm.manager.core.application.service.AppService;
import com.qding.apm.manager.core.domain.entity.AppEntity;
import com.qding.apm.manager.core.domain.repository.AppRepository;
import com.qding.apm.manager.core.infrastructure.assembler.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 应用程序管理service
 *
 * @author weijia
 */
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppRepository<AppEntity> appRepository;

    /**
     * 获取全部应用程序列表
     *
     * @return 返回全部应用程序列表
     */
    @Override
    public List<AppDto> findAllApp() {
        final List<AppEntity> appEntityList = appRepository.findAll();

        return AppMapper.INSTANCE.toAppDto(appEntityList);
    }

    /**
     * 查询应用及关联告警规则、告警接收人
     *
     * @param id 应用程序id
     * @return 返回查询到的应用配置
     */
    @Override
    public AppModelDto findAppById(final long id) {
        final AppEntity appEntity = appRepository.findById(id);

        return AppMapper.INSTANCE.toAppModelDto(appEntity);
    }

    /**
     * 保存应用程序apm配置
     *
     * @param updateAppDto 应用的apm配置
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateApp(final UpdateAppDto updateAppDto) {
        // 构建应用程序领域对象
        final AppEntity appEntity = new AppEntity.Builder(updateAppDto.getId(),
                updateAppDto.getName(),
                AppMapper.INSTANCE.toAlertRuleEntity(updateAppDto.getAddAlertRuleList()),
                AppMapper.INSTANCE.toAlertRuleEntity(updateAppDto.getUpdateAlertRuleList()),
                updateAppDto.getRemoveAlertRuleList(),
                updateAppDto.getAddAlertReceiverList(),
                updateAppDto.getRemoveAlertReceiverList()).build();

        appRepository.save(appEntity);
    }

    /**
     * 新建应用程序apm配置
     *
     * @param appDto 应用的apm配置
     * @return 返回应用程序id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long createApp(final CreateAppDto appDto) {
        // 构建应用程序领域对象
        final AppEntity appEntity = new AppEntity.Builder(null,
                appDto.getName(),
                AppMapper.INSTANCE.toAlertRuleEntity(appDto.getAddAlertRuleList()),
                Collections.emptyList(),
                Collections.emptyList(),
                appDto.getAddAlertReceiverList(),
                Collections.emptyList()).build();

        appRepository.save(appEntity);

        return appEntity.getId();
    }

    /**
     * 删除应用程序及相关apm配置
     *
     * @param id 应用程序id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAppById(final long id) {
        appRepository.delete(id);
    }
}
