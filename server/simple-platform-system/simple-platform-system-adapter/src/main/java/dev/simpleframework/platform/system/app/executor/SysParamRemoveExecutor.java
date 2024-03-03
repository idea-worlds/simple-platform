package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysParamRemoveEvent;
import dev.simpleframework.platform.system.infra.data.SysParam;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysParamRemoveExecutor {
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
                .add(SysParam::getCode, ConditionType.in, this.codes);
        new SysParam().deleteByConditions(conditions);
    }

    private void publishEvent() {
        SysParamRemoveEvent event = new SysParamRemoveEvent();
        event.fillUser();
        event.setCodes(this.codes);
        SimpleSpringUtils.publishEvent(event);
    }

}
