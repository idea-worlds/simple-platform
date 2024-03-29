package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
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
    private final List<String> codes;

    public void exec() {
        if (this.codes == null || this.codes.isEmpty()) {
            return;
        }
        this.remove();
        this.publishEvent();
    }

    private void remove() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getCode, ConditionType.in, this.codes);
        new SysWorkspace().deleteByConditions(conditions);
    }

    private void publishEvent() {
        SysWorkspaceRemoveEvent event = new SysWorkspaceRemoveEvent();
        event.fillUser();
        event.setCodes(this.codes);
        SimpleSpringUtils.publishEvent(event);
    }

}
