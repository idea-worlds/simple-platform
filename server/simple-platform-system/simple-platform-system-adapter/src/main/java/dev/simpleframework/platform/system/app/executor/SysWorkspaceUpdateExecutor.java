package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysWorkspaceUpdateEvent;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import dev.simpleframework.util.Strings;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceUpdateExecutor {
    private final SysWorkspaceModifyArgs args;

    public void exec() {
        String code = this.args.getCode();
        if (Strings.isBlank(code)) {
            return;
        }
        this.update();
        this.publishEvent();
    }

    private void update() {
        SysWorkspace workspace = new SysWorkspace();
        workspace.setType(this.args.getType());
        workspace.setName(this.args.getName());
        workspace.setIcon(this.args.getIcon());
        workspace.setDescription(this.args.getDescription());
        workspace.setExtFlag(this.args.getExt());
        workspace.setExtUrl(this.args.getExtUrl());
        workspace.setExtInfo(this.args.getExtInfo());
        workspace.setSortNo(this.args.getSortNo());

        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getCode, ConditionType.equal, this.args.getCode());
        workspace.updateByConditions(conditions);
    }

    private void publishEvent() {
        SysWorkspaceUpdateEvent event = new SysWorkspaceUpdateEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
