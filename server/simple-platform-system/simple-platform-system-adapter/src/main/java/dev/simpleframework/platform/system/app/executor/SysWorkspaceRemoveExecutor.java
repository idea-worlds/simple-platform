package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.core.Pair;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.event.SysWorkspaceRemoveEvent;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceRemoveExecutor {
    private final List<Long> ids;

    public void exec() {
        this.remove();
        this.publishEvent();
    }

    private void remove() {
        new SysWorkspace().deleteByIds(this.ids);
    }

    private void publishEvent() {
        Pair<Long, String> user = CommonUtils.getLoginUser();
        Long userId = user.getLeft();
        String userName = user.getRight();
        SysWorkspaceRemoveEvent event = new SysWorkspaceRemoveEvent();
        event.setOperateUserId(userId);
        event.setOperateUserName(userName);
        event.setWorkspaceIds(this.ids);
        SimpleSpringUtils.publishEvent(event);
    }

}
