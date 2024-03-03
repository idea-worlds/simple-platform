package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 事件：删除系统参数
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParamRemoveEvent extends BaseEvent {
    private List<String> codes;
}
