package dev.simpleframework.platform.system.event;

import lombok.Data;

import java.util.List;

/**
 * 事件：启用工作空间
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysWorkspaceEnableEvent {
    /**
     * 操作人
     */
    private Long operateUserId;
    private String operateUserName;
    private List<Long> workspaceIds;
}
