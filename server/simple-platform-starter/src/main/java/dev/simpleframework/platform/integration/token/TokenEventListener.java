package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.system.event.BaseOperateUsersEvent;
import dev.simpleframework.platform.system.event.SysUserDisableEvent;
import dev.simpleframework.platform.system.event.SysUserRemoveEvent;
import dev.simpleframework.platform.system.event.SysUserWorkspacesChangeEvent;
import dev.simpleframework.token.SimpleTokens;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Component
public class TokenEventListener {

    @Async
    @TransactionalEventListener(value = {SysUserDisableEvent.class, SysUserRemoveEvent.class})
    public void userStatusChanged(BaseOperateUsersEvent event) {
        List<Long> userIds = event.getChangedUserIds();
        for (Long userId : userIds) {
            try {
                SimpleTokens.kickout(userId.toString());
            } catch (Exception ignore) {
            }
        }
    }

    @Async
    @TransactionalEventListener(SysUserWorkspacesChangeEvent.class)
    public void userWorkspacesChanged(SysUserWorkspacesChangeEvent event) {
        List<Long> userIds = event.getChangedUserIds();
        for (Long userId : userIds) {
            try {
                SimpleTokens.refreshSession(userId.toString());
            } catch (Exception ignore) {
            }
        }
    }

}
