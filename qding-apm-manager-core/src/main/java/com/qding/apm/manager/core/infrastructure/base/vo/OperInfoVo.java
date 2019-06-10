package com.qding.apm.manager.core.infrastructure.base.vo;

import com.qding.apm.manager.core.infrastructure.base.ddd.ValueObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 操作人及操作时间
 * @author weijia
 */
@Getter
@Setter
public class OperInfoVo implements ValueObject {
    /**
     * 操作人
     */
    private String user;

    /**
     * 操作时间
     */
    private Date date = new Date();

    public OperInfoVo(final String user) {
        this.user = user;
    }
}
