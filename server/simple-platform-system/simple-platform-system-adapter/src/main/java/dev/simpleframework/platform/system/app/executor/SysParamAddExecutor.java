package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysParamAddEvent;
import dev.simpleframework.platform.system.infra.data.SysParam;
import dev.simpleframework.platform.system.model.SysParamModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysParamAddExecutor {
    private final SysParamModifyArgs args;

    public void exec() {
        this.checkArgs();
        this.insert();
        this.publishEvent();
    }

    private void checkArgs() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysParam::getCode, this.args.getCode());
        if (new SysParam().existByConditions(conditions)) {
            throw new IllegalArgumentException("编码已存在");
        }
    }

    private void insert() {
        SysParam param = new SysParam();
        param.setCode(this.args.getCode());
        param.setName(this.args.getName());
        param.setVal(this.args.getVal());
        param.setDescription(this.args.getDescription());

        param.insert();
    }

    private void publishEvent() {
        SysParamAddEvent event = new SysParamAddEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
