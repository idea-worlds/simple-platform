package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 事件：新增系统参数
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParamAddEvent extends BaseEvent {
    private String code;
}
