package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryFields;
import dev.simpleframework.platform.system.event.SysUserChangeWorkspaceEvent;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.*;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserChangeWorkspacesExecutor {
    private final List<Long> userIds;
    private final List<String> workspaces;
    private final Type type;
    private final Map<String, List<String>> userTypes = new LinkedHashMap<>();

    public static SysUserChangeWorkspacesExecutor ofSet(Long userId) {
        return new SysUserChangeWorkspacesExecutor(Collections.singletonList(userId), new ArrayList<>(), Type.SET);
    }

    public static SysUserChangeWorkspacesExecutor ofRemove(List<Long> userIds, String workspace) {
        return ofRemove(userIds, Collections.singletonList(workspace));
    }

    public static SysUserChangeWorkspacesExecutor ofRemove(List<Long> userIds, List<String> workspaces) {
        return new SysUserChangeWorkspacesExecutor(userIds, workspaces, Type.REMOVE);
    }

    public static SysUserChangeWorkspacesExecutor ofLock(List<Long> userIds, String workspace) {
        return ofLock(userIds, Collections.singletonList(workspace));
    }

    public static SysUserChangeWorkspacesExecutor ofLock(List<Long> userIds, List<String> workspaces) {
        return new SysUserChangeWorkspacesExecutor(userIds, workspaces, Type.LOCK);
    }

    public static SysUserChangeWorkspacesExecutor ofUnlock(List<Long> userIds, String workspace) {
        return ofUnlock(userIds, Collections.singletonList(workspace));
    }

    public static SysUserChangeWorkspacesExecutor ofUnlock(List<Long> userIds, List<String> workspaces) {
        return new SysUserChangeWorkspacesExecutor(userIds, workspaces, Type.UNLOCK);
    }

    public void setWorkspaceUserTypes(String workspace, List<String> types) {
        if (!this.workspaces.contains(workspace)) {
            this.workspaces.add(workspace);
        }
        this.userTypes.put(workspace, types);
    }

    public void exec() {
        if (this.userIds == null || this.userIds.isEmpty()) {
            return;
        }
        QueryFields fields = QueryFields.of()
                .add(SysUser::getId)
                .add(SysUser::getWorkspaces);
        List<SysUser> users = new SysUser().listByIds(this.userIds, fields);
        switch (this.type) {
            case SET -> this.doSet(users);
            case REMOVE -> this.doRemove(users);
            case LOCK -> this.doLock(users);
            case UNLOCK -> this.doUnlock(users);
        }
        this.publishEvent();
    }

    private void doSet(List<SysUser> users) {
        for (SysUser user : users) {
            user.changeWorkspaces(this.userTypes);
            user.updateById();
        }
    }

    private void doRemove(List<SysUser> users) {
        for (SysUser user : users) {
            user.removeWorkspaces(this.workspaces);
            user.updateById();
        }
    }

    private void doLock(List<SysUser> users) {
        for (SysUser user : users) {
            user.changeWorkspacesLock(this.workspaces, true);
            user.updateById();
        }
    }

    private void doUnlock(List<SysUser> users) {
        for (SysUser user : users) {
            user.changeWorkspacesLock(this.workspaces, false);
            user.updateById();
        }
    }

    private void publishEvent() {
        SysUserChangeWorkspaceEvent event = new SysUserChangeWorkspaceEvent();
        event.fillUser();
        event.setUserIds(this.userIds);
        event.setWorkspaces(this.workspaces);

        String operateType = null;
        switch (this.type) {
            case SET -> operateType = SysUserChangeWorkspaceEvent.TYPE_SET;
            case REMOVE -> operateType = SysUserChangeWorkspaceEvent.TYPE_REMOVE;
            case LOCK -> operateType = SysUserChangeWorkspaceEvent.TYPE_LOCK;
            case UNLOCK -> operateType = SysUserChangeWorkspaceEvent.TYPE_UNLOCK;
        }
        event.setType(operateType);
        SimpleSpringUtils.publishEvent(event);
    }

    private enum Type {
        SET, REMOVE, LOCK, UNLOCK
    }

}
