package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.crud.core.QueryConditions;
import dev.simpleframework.platform.system.infra.constant.AccountType;
import dev.simpleframework.platform.system.infra.constant.UserStatus;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.platform.system.infra.util.SystemUtil;
import dev.simpleframework.platform.system.model.SysUserAddArgs;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@RequiredArgsConstructor
public class SysUserAddExecutor {
    private final SysUserAddArgs args;
    private Long userId;

    public Long exec() {
        this.checkArgs();

        // 新增用户
        SysUser user = this.buildUser();
        user.insert();
        this.userId = user.getId();

        // 新增用户账号
        List<SysUserAccount> accounts = this.buildAccounts();
        new SysUserAccount().insertBatch(accounts);

        // 推送事件
        this.publishEvent();
        return this.userId;
    }

    public Long userId() {
        return this.userId;
    }

    protected void checkArgs() {
        QueryConditions conditions = QueryConditions.and()
                .add(SysUserAccount::getType, AccountType.NAME.name())
                .add(SysUserAccount::getName, this.args.getAccountName());
        if (new SysUserAccount().existByConditions(conditions)) {
            throw new IllegalArgumentException("账号名已存在");
        }
    }

    protected SysUser buildUser() {
        SysUser user = new SysUser();
        user.setName(this.args.getName());
        user.setMobile(this.args.getMobile());
        user.setEmail(this.args.getEmail());
        user.setSex(this.args.getSex());
        user.setOrgs(this.args.getOrgs());
        user.setStatus(this.initUserStatus().name());
        user.setSortNo(0);
        user.changeWorkspaces(this.args.getWorkspaces());
        return user;
    }

    protected List<SysUserAccount> buildAccounts() {
        String password = SystemUtil.encryptAccountPassword(this.args.getAccountPassword());
        SysUserAccount account = new SysUserAccount();
        account.setUserId(this.userId);
        account.setType(AccountType.NAME.name());
        account.setName(this.args.getAccountName());
        account.setPassword(password);
        return Collections.singletonList(account);
    }

    protected UserStatus initUserStatus() {
        return UserStatus.ENABLE;
    }

    protected void publishEvent() {
    }

}
