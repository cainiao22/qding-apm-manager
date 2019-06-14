package com.qding.apm.manager.core.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 应用程序基本数据
 *
 * @author weijia
 */
@Getter
@Setter
public class AppDto {
    /**
     * appId
     */
    private Long id;

    /**
     * 应用名称
     */
    private String name;
}
