package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 事件：修改系统参数
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParamUpdateEvent extends BaseEvent {
    private String code;
}
