package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.event.SysSignOutEvent;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.util.SimpleSpringUtils;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public class SignOutExecutor {

    public void exec() {
        SysSignOutEvent event = new SysSignOutEvent();
        Long userId = null;
        try {
            event.fillUser();
            event.setTime(System.currentTimeMillis());
            userId = event.getOperateUserId();
        } catch (Exception ignore) {
        }

        if (userId != null) {
            SimpleTokens.logout();
            SimpleSpringUtils.publishEvent(event);
        }
    }

}
