package dev.simpleframework.platform.system.event;

import lombok.Data;

import java.util.List;

/**
 * 事件：删除工作空间
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysWorkspaceRemoveEvent {
    /**
     * 操作人
     */
    private Long operateUserId;
    private String operateUserName;
    private List<Long> workspaceIds;
}
