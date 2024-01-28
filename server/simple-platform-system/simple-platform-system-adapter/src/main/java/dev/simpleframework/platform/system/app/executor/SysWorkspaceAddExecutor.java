package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysWorkspaceAddEvent;
import dev.simpleframework.platform.system.infra.constant.WorkspaceStatus;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceModifyArgs;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceAddExecutor {
    private final SysWorkspaceModifyArgs args;

    public void exec() {
        this.checkArgs();
        this.insert();
        this.publishEvent();
    }

    private void checkArgs() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getCode, this.args.getCode());
        if (new SysWorkspace().existByConditions(conditions)) {
            throw new IllegalArgumentException("编码已存在");
        }
    }

    private void insert() {
        SysWorkspace workspace = new SysWorkspace();
        workspace.setCode(this.args.getCode());
        workspace.setType(this.args.getType());
        workspace.setName(this.args.getName());
        workspace.setIcon(this.args.getIcon());
        workspace.setDescription(this.args.getDescription());
        workspace.setExtFlag(this.args.getExt());
        workspace.setExtUrl(this.args.getExtUrl());
        workspace.setExtInfo(this.args.getExtInfo());
        workspace.setSortNo(this.args.getSortNo());
        workspace.setStatus(WorkspaceStatus.DISABLE.name());

        workspace.insert();
    }

    private void publishEvent() {
        SysWorkspaceAddEvent event = new SysWorkspaceAddEvent();
        event.fillUser();
        event.setCode(this.args.getCode());
        SimpleSpringUtils.publishEvent(event);
    }

}
