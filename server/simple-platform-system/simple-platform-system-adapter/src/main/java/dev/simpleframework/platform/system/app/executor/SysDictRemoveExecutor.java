package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysDictRemoveEvent;
import dev.simpleframework.platform.system.infra.data.SysDict;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysDictRemoveExecutor {
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
                .add(SysDict::getCode, ConditionType.in, this.codes);
        new SysDict().deleteByConditions(conditions);
    }

    private void publishEvent() {
        SysDictRemoveEvent event = new SysDictRemoveEvent();
        event.fillUser();
        event.setCodes(this.codes);
        SimpleSpringUtils.publishEvent(event);
    }

}
