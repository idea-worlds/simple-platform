package dev.simpleframework.platform.system.event;

import lombok.Data;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public abstract class BaseOperateUsersEvent {
    /**
     * 操作人
     */
    private Long operateUserId;
    private String operateUserName;
    /**
     * 被修改的用户
     */
    private List<Long> changedUserIds;
}
