package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysUserRemoveEvent;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserRemoveExecutor {
    private final List<Long> userIds;

    public void exec() {
        this.removeUsers();
        this.removeAccounts();
        this.publishEvent();
    }

    private void removeUsers() {
        new SysUser().deleteByIds(this.userIds);
    }

    private void removeAccounts() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysUserAccount::getUserId, ConditionType.in, this.userIds);
        new SysUserAccount().deleteByConditions(conditions);
    }

    private void publishEvent() {
        SysUserRemoveEvent event = new SysUserRemoveEvent();
        event.fillUser();
        event.setUserIds(this.userIds);
        SimpleSpringUtils.publishEvent(event);
    }

}
