package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 事件：用户设置/删除/锁定/解锁工作空间
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserChangeWorkspaceEvent extends BaseEvent {
    public static final String TYPE_SET = "SET";
    public static final String TYPE_REMOVE = "REMOVE";
    public static final String TYPE_LOCK = "LOCK";
    public static final String TYPE_UNLOCK = "UNLOCK";

    /**
     * 被修改的用户
     */
    private List<Long> userIds;
    private List<String> workspaces;
    private String type;
}
