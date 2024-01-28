package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
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
    private final List<String> codes;
    private final WorkspaceStatus status;

    public void exec() {
        if (this.codes == null || this.codes.isEmpty()) {
            return;
        }
        this.updateStatus();
        this.publishEvent();
    }

    private void updateStatus() {
        SysWorkspace dao = new SysWorkspace();
        dao.setStatus(this.status.name());
        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getCode, ConditionType.in, this.codes);
        dao.updateByConditions(conditions);
    }

    private void publishEvent() {
        if (this.status == WorkspaceStatus.ENABLE) {
            SysWorkspaceEnableEvent event = new SysWorkspaceEnableEvent();
            event.fillUser();
            event.setCodes(this.codes);
            SimpleSpringUtils.publishEvent(event);
        } else if (this.status == WorkspaceStatus.DISABLE) {
            SysWorkspaceDisableEvent event = new SysWorkspaceDisableEvent();
            event.fillUser();
            event.setCodes(this.codes);
            SimpleSpringUtils.publishEvent(event);
        }
    }

}
