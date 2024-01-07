package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.event.SysSignOutEvent;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.util.SimpleSpringUtils;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public class SignOutExecutor {

    public void exec() {
        Long userId = null;
        try {
            userId = SimpleTokens.getLoginIdAsLong();
        } catch (Exception ignore) {
        }
        SimpleTokens.logout();

        // 推送事件
        if (userId != null) {
            SimpleSpringUtils.publishEvent(new SysSignOutEvent(userId));
        }
    }

}
