package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysDictAddEvent;
import dev.simpleframework.platform.system.infra.constant.WorkspaceStatus;
import dev.simpleframework.platform.system.infra.data.SysDict;
import dev.simpleframework.platform.system.model.SysDictModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysDictAddExecutor {
    private final SysDictModifyArgs args;

    public void exec() {
        this.checkArgs();
        this.insert();
        this.publishEvent();
    }

    private void checkArgs() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysDict::getCode, this.args.getCode());
        if (new SysDict().existByConditions(conditions)) {
            throw new IllegalArgumentException("编码已存在");
        }
    }

    private void insert() {
        SysDict dict = new SysDict();
        dict.setCode(this.args.getCode());
        dict.setName(this.args.getName());
        dict.setDescription(this.args.getDescription());
        this.args.getItems().forEach(i -> dict.addItem(i.getCode(), i.getName(), i.getVal()));

        dict.insert();
    }

    private void publishEvent() {
        SysDictAddEvent event = new SysDictAddEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
