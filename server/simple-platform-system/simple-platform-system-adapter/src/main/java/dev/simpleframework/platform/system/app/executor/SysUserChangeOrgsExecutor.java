package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.crud.core.QueryFields;
import dev.simpleframework.platform.system.event.SysUserChangeOrgEvent;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserChangeOrgsExecutor {
    private final List<Long> userIds;
    private final List<String> orgs;
    private final Type type;

    public static SysUserChangeOrgsExecutor ofSet(List<Long> userIds, List<String> orgs) {
        return new SysUserChangeOrgsExecutor(userIds, orgs, Type.SET);
    }

    public static SysUserChangeOrgsExecutor ofRemove(List<Long> userIds, List<String> orgs) {
        return new SysUserChangeOrgsExecutor(userIds, orgs, Type.REMOVE);
    }

    public void exec() {
        if (this.userIds == null || this.userIds.isEmpty()) {
            return;
        }
        switch (this.type) {
            case SET -> this.doSet();
            case REMOVE -> this.doRemove();
        }
        this.publishEvent();
    }

    private void doSet() {
        List<String> orgs = this.orgs == null ? Collections.emptyList() : this.orgs;
        QueryConditions conditions = QueryConditions.and()
                .add(SysUser::getId, ConditionType.in, this.userIds);
        SysUser dao = new SysUser();
        dao.setOrgs(orgs);
        dao.updateByConditions(conditions);
    }

    private void doRemove() {
        if (this.orgs == null || this.orgs.isEmpty()) {
            return;
        }
        QueryFields fields = QueryFields.of()
                .add(SysUser::getId)
                .add(SysUser::getOrgs);
        List<SysUser> users = new SysUser().listByIds(this.userIds, fields);
        for (SysUser user : users) {
            if (user.getOrgs().removeAll(this.orgs)) {
                user.updateById();
            }
        }
    }

    private void publishEvent() {
        SysUserChangeOrgEvent event = new SysUserChangeOrgEvent();
        event.fillUser();
        event.setUserIds(this.userIds);
        event.setOrgs(this.orgs);

        String operateType = null;
        switch (this.type) {
            case SET -> operateType = SysUserChangeOrgEvent.TYPE_SET;
            case REMOVE -> operateType = SysUserChangeOrgEvent.TYPE_REMOVE;
        }
        event.setType(operateType);
        SimpleSpringUtils.publishEvent(event);
    }

    private enum Type {
        SET, REMOVE
    }

}
