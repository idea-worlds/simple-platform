package dev.simpleframework.platform.system.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 事件：登出成功
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@NoArgsConstructor
@Getter
public class SysSignOutEvent {

    private Long userId;
    private Long time;

    public SysSignOutEvent(Long userId) {
        this.userId = userId;
        this.time = System.currentTimeMillis();
    }

}
