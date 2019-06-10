package com.qding.apm.manager.core.application.service;

import com.qding.apm.manager.core.application.dto.AppDto;
import com.qding.apm.manager.core.application.dto.AppModelDto;
import com.qding.apm.manager.core.application.dto.CreateAppDto;
import com.qding.apm.manager.core.application.dto.UpdateAppDto;

import java.util.List;

/**
 * 应用程序管理service
 *
 * @author weijia
 */
public interface AppService {
    /**
     * 获取全部应用程序列表
     *
     * @return 返回全部应用程序列表
     */
    List<AppDto> findAllApp();

    /**
     * 查询应用及关联告警规则、告警接收人
     *
     * @param id 应用程序id
     * @return 返回查询到的应用配置
     */
    AppModelDto findAppById(final long id);

    /**
     * 新建应用程序apm配置
     *
     * @param appDto 应用的apm配置
     * @return 返回应用程序id
     */
    long createApp(final CreateAppDto appDto);

    /**
     * 保存应用程序apm配置
     *
     * @param saveAppDto 应用的apm配置
     */
    void updateApp(final UpdateAppDto saveAppDto);

    /**
     * 删除应用程序及相关apm配置
     *
     * @param id 应用程序id
     */
    void deleteAppById(final long id);
}
