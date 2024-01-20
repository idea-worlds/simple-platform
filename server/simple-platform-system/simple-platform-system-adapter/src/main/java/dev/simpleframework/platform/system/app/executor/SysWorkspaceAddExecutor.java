package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.infra.constant.WorkspaceStatus;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceAddArgs;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceAddExecutor {
    private final SysWorkspaceAddArgs args;

    public Long exec() {
        this.checkArgs();

        SysWorkspace workspace = this.buildWorkspace();
        workspace.insert();

        return workspace.getId();
    }

    private void checkArgs() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysWorkspace::getCode, this.args.getCode());
        if (new SysWorkspace().existByConditions(conditions)) {
            throw new IllegalArgumentException("编码已存在");
        }
    }

    private SysWorkspace buildWorkspace() {
        SysWorkspace workspace = new SysWorkspace();
        workspace.setCode(this.args.getCode());
        workspace.setType(this.args.getType());
        workspace.setName(this.args.getName());
        workspace.setIcon(this.args.getIcon());
        workspace.setExtFlag(this.args.getExt());
        workspace.setExtUrl(this.args.getExtUrl());
        workspace.setExtInfo(this.args.getExtInfo());
        workspace.setSortNo(this.args.getSortNo());
        workspace.setStatus(WorkspaceStatus.DISABLE.name());
        return workspace;
    }

}
