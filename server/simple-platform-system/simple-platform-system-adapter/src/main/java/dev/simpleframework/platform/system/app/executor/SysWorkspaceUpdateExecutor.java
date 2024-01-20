package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceUpdateArgs;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysWorkspaceUpdateExecutor {
    private final Long id;
    private final SysWorkspaceUpdateArgs args;

    public Long exec() {
        SysWorkspace workspace = this.buildWorkspace();
        workspace.updateById();

        return workspace.getId();
    }

    private SysWorkspace buildWorkspace() {
        SysWorkspace workspace = new SysWorkspace();
        workspace.setId(this.id);
        workspace.setType(this.args.getType());
        workspace.setName(this.args.getName());
        workspace.setIcon(this.args.getIcon());
        workspace.setExtFlag(this.args.getExt());
        workspace.setExtUrl(this.args.getExtUrl());
        workspace.setExtInfo(this.args.getExtInfo());
        workspace.setSortNo(this.args.getSortNo());
        return workspace;
    }

}
