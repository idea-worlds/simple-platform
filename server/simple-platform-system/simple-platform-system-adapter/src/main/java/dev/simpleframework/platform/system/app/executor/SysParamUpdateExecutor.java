package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysParamUpdateEvent;
import dev.simpleframework.platform.system.infra.data.SysParam;
import dev.simpleframework.platform.system.model.SysParamModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import dev.simpleframework.util.Strings;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysParamUpdateExecutor {
    private final SysParamModifyArgs args;

    public void exec() {
        String code = this.args.getCode();
        if (Strings.isBlank(code)) {
            return;
        }
        this.update();
        this.publishEvent();
    }

    private void update() {
        SysParam param = new SysParam();
        param.setName(this.args.getName());
        param.setVal(this.args.getVal());
        param.setDescription(this.args.getDescription());

        QueryConditions conditions = QueryConditions.and()
                .add(SysParam::getCode, ConditionType.equal, this.args.getCode());
        param.updateByConditions(conditions);
    }

    private void publishEvent() {
        SysParamUpdateEvent event = new SysParamUpdateEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
