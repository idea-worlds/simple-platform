package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.event.SysSignInEvent;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.platform.system.model.SignInArgs;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.token.context.ContextManager;
import dev.simpleframework.token.context.ContextRequest;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SignInExecutor {
    private final SignInArgs args;
    private final long now = System.currentTimeMillis();
    private String token;

    public String exec() {
        this.token = SimpleTokens.loginByAccount(this.args.getType(), this.args.getName(), this.args.getPassword());
        this.changeUserLoginInfo();
        this.publishEvent();
        return this.token;
    }

    private void changeUserLoginInfo() {
        ContextRequest request = ContextManager.findContext().request();
        SysUserAccount account = new SysUserAccount();
        account.setLastLoginIp(request.getIp());
        account.setLastLoginAgent(request.getHeader("User-Agent"));
        account.setLastLoginTime(this.now);

        QueryConditions conditions = QueryConditions.and()
                .add(SysUserAccount::getType, this.args.getType())
                .add(SysUserAccount::getName, this.args.getName());
        account.updateByConditions(conditions);
    }

    private void publishEvent() {
        SysSignInEvent event = new SysSignInEvent();
        event.fillUser();
        event.setToken(this.token);
        event.setTime(this.now);
        SimpleSpringUtils.publishEvent(event);
    }

}
