package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 事件：禁用用户
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDisableEvent extends BaseEvent {

    /**
     * 被修改的用户
     */
    private List<Long> userIds;

}
