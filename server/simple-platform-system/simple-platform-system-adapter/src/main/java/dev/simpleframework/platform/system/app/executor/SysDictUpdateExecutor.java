package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysDictUpdateEvent;
import dev.simpleframework.platform.system.infra.data.SysDict;
import dev.simpleframework.platform.system.model.SysDictModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import dev.simpleframework.util.Strings;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysDictUpdateExecutor {
    private final SysDictModifyArgs args;

    public void exec() {
        String code = this.args.getCode();
        if (Strings.isBlank(code)) {
            return;
        }
        this.update();
        this.publishEvent();
    }

    private void update() {
        SysDict dict = new SysDict();
        dict.setName(this.args.getName());
        dict.setDescription(this.args.getDescription());
        dict.setSortNo(this.args.getSortNo());
        this.args.getItems().forEach(i -> dict.addItem(i.getCode(), i.getName(), i.getSortNo()));

        QueryConditions conditions = QueryConditions.and()
                .add(SysDict::getCode, ConditionType.equal, this.args.getCode());
        dict.updateByConditions(conditions);
    }

    private void publishEvent() {
        SysDictUpdateEvent event = new SysDictUpdateEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
