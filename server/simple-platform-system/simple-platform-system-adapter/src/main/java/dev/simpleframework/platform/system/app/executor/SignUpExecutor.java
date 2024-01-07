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

    public SignUpExecutor(SignUpArgs args) {
        super(toAddArgs(args));
    }

    @Override
    protected UserStatus userStatus() {
        return UserStatus.INACTIVE;
    }

    @Override
    protected void publishEvent() {
        SysSignUpEvent event = new SysSignUpEvent(super.userId());
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
