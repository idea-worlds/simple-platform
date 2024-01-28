package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysUserDisableEvent;
import dev.simpleframework.platform.system.event.SysUserEnableEvent;
import dev.simpleframework.platform.system.infra.constant.UserStatus;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserChangeStatusExecutor {
    private final List<Long> userIds;
    private final UserStatus status;

    public void exec() {
        if (this.userIds == null || this.userIds.isEmpty()) {
            return;
        }
        this.updateStatus();
        this.publishEvent();
    }

    private void updateStatus() {
        SysUser dao = new SysUser();
        dao.setStatus(this.status.name());
        QueryConditions conditions = QueryConditions.and()
                .add(SysUser::getId, ConditionType.in, this.userIds);
        dao.updateByConditions(conditions);
    }

    private void publishEvent() {
        if (this.status == UserStatus.ENABLE) {
            SysUserEnableEvent event = new SysUserEnableEvent();
            event.fillUser();
            event.setUserIds(this.userIds);
            SimpleSpringUtils.publishEvent(event);
        } else if (this.status == UserStatus.DISABLE) {
            SysUserDisableEvent event = new SysUserDisableEvent();
            event.fillUser();
            event.setUserIds(this.userIds);
            SimpleSpringUtils.publishEvent(event);
        }
    }

}
