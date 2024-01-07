package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.model.SysUserUpdateArgs;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserUpdateExecutor {
    private final Long userId;
    private final SysUserUpdateArgs args;

    public void exec() {
        this.updateUser();
    }

    private void updateUser() {
        SysUser user = new SysUser();
        user.setId(this.userId);
        user.setName(this.args.getName());
        user.setMobile(this.args.getMobile());
        user.setEmail(this.args.getEmail());
        user.setSex(this.args.getSex());
        user.updateById();
    }

}
