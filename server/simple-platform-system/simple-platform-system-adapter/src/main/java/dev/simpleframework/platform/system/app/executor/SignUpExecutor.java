package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.event.SysSignUpEvent;
import dev.simpleframework.platform.system.infra.constant.UserStatus;
import dev.simpleframework.platform.system.model.SignUpArgs;
import dev.simpleframework.platform.system.model.SysUserAddArgs;
import dev.simpleframework.util.SimpleSpringUtils;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public class SignUpExecutor extends SysUserAddExecutor {
    private final SignUpArgs args;

    public SignUpExecutor(SignUpArgs args) {
        super(toAddArgs(args));
        this.args = args;
    }

    @Override
    protected UserStatus initUserStatus() {
        return UserStatus.INACTIVE;
    }

    @Override
    protected void publishEvent() {
        SysSignUpEvent event = new SysSignUpEvent();
        event.setOperateUserId(super.userId());
        event.setOperateUserName(this.args.getUserName());
        event.setTime(System.currentTimeMillis());
        SimpleSpringUtils.publishEvent(event);
    }

    private static SysUserAddArgs toAddArgs(SignUpArgs args) {
        SysUserAddArgs result = new SysUserAddArgs();
        result.setName(args.getUserName());
        result.setMobile(args.getMobile());
        result.setEmail(args.getEmail());
        result.setAccountName(args.getAccountName());
        result.setAccountPassword(args.getAccountPassword());
        return result;
    }

}
