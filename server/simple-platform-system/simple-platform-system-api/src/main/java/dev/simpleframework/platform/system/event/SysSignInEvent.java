package dev.simpleframework.platform.system.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 事件：登录成功
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Getter
@NoArgsConstructor
public class SysSignInEvent {

    private Long userId;
    private String userName;
    private String token;
    private Long time;

    public SysSignInEvent(Long userId, String userName, String token, Long time) {
        this.userId = userId;
        this.userName = userName;
        this.token = token;
        this.time = time;
    }

}
