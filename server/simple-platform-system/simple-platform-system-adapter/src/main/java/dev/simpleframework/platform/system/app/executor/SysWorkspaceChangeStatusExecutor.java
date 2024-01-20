package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.core.Pair;
import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.event.SysWorkspaceDisableEvent;
import dev.simpleframework.platform.system.event.SysWorkspaceEnableEvent;
import dev.simpleframework.platform.system.infra.constant.WorkspaceStatus;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceChangeStatusExecutor {
    private final List<Long> ids;
    private final WorkspaceStatus status;

    public void exec() {
        if (this.ids == null || this.ids.isEmpty()) {
            return;
        }
        this.updateStatus();
        this.publishEvent();
    }

    private void updateStatus() {
        SysWorkspace dao = new SysWorkspace();
        dao.setStatus(this.status.name());
        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getId, ConditionType.in, this.ids);
        dao.updateByConditions(conditions);
    }

    private void publishEvent() {
        Pair<Long, String> user = CommonUtils.getLoginUser();
        Long userId = user.getLeft();
        String userName = user.getRight();
        if (this.status == WorkspaceStatus.ENABLE) {
            SysWorkspaceEnableEvent event = new SysWorkspaceEnableEvent();
            event.setOperateUserId(userId);
            event.setOperateUserName(userName);
            event.setWorkspaceIds(this.ids);
            SimpleSpringUtils.publishEvent(event);
        } else if (this.status == WorkspaceStatus.DISABLE) {
            SysWorkspaceDisableEvent event = new SysWorkspaceDisableEvent();
            event.setOperateUserId(userId);
            event.setOperateUserName(userName);
            event.setWorkspaceIds(this.ids);
            SimpleSpringUtils.publishEvent(event);
        }
    }

}
