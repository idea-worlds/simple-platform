package dev.simpleframework.platform.system.api;

import dev.simpleframework.platform.system.model.SignInArgs;
import dev.simpleframework.platform.system.model.SignUpArgs;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysSignApi {

    /**
     * 注册
     */
    void up(SignUpArgs args);

    /**
     * 登录
     *
     * @return token
     */
    String in(SignInArgs args);

    /**
     * 登出
     */
    void out();

}
