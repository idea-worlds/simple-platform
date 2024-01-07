package dev.simpleframework.platform.system.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 事件：注册成功
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Getter
@NoArgsConstructor
public class SysSignUpEvent {

    private Long userId;
    private Long time;

    public SysSignUpEvent(Long userId) {
        this.userId = userId;
        this.time = System.currentTimeMillis();
    }

}
