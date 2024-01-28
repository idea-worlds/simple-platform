package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.system.event.SysUserChangeWorkspaceEvent;
import dev.simpleframework.platform.system.event.SysUserDisableEvent;
import dev.simpleframework.platform.system.event.SysUserRemoveEvent;
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
    @TransactionalEventListener(value = SysUserDisableEvent.class)
    public void listenerUserDisable(SysUserDisableEvent event) {
        List<Long> userIds = event.getUserIds();
        for (Long userId : userIds) {
            try {
                SimpleTokens.kickout(userId.toString());
            } catch (Exception ignore) {
            }
        }
    }

    @Async
    @TransactionalEventListener(value = SysUserRemoveEvent.class)
    public void listenerUserRemove(SysUserRemoveEvent event) {
        List<Long> userIds = event.getUserIds();
        for (Long userId : userIds) {
            try {
                SimpleTokens.kickout(userId.toString());
            } catch (Exception ignore) {
            }
        }
    }

    @Async
    @TransactionalEventListener(SysUserChangeWorkspaceEvent.class)
    public void listenerUserWorkspacesChanged(SysUserChangeWorkspaceEvent event) {
        List<Long> userIds = event.getUserIds();
        for (Long userId : userIds) {
            try {
                SimpleTokens.refreshSession(userId.toString());
            } catch (Exception ignore) {
            }
        }
    }

}
