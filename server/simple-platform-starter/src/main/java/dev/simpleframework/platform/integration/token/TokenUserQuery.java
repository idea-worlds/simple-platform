package dev.simpleframework.platform.integration.token;

import dev.simpleframework.crud.core.QueryConfig;
import dev.simpleframework.platform.system.infra.constant.UserStatus;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.token.user.UserAccount;
import dev.simpleframework.token.user.UserInfo;
import dev.simpleframework.token.user.UserQuery;
import dev.simpleframework.util.Strings;
import org.springframework.stereotype.Component;

/**
 * simple-token 用户查询实现类
 */
@Component
public class TokenUserQuery implements UserQuery {

    @Override
    public UserInfo getInfoById(String loginId) {
        SysUser sysUser = new SysUser().findById(loginId);
        if (sysUser == null) {
            return null;
        }
        TokenUser result = new TokenUser();
        result.setId(sysUser.getId().toString());
        result.setEnable(UserStatus.isEnable(sysUser.getStatus()));
        result.setName(sysUser.getName());
        sysUser.getWorkspaces().forEach((code, w) -> {
            if (!w.getLock()) {
                result.addWorkspace(code, w.getTypes());
            }
        });
        return result;
    }

    @Override
    public UserAccount getAccountByName(String accountType, String accountName) {
        if (Strings.isBlank(accountType) || Strings.isBlank(accountName)) {
            return null;
        }
        QueryConfig config = QueryConfig.of()
                .addCondition(SysUserAccount::getType, accountType)
                .addCondition(SysUserAccount::getName, accountName);
        SysUserAccount account = new SysUserAccount().findOneByConditions(config);
        if (account == null) {
            return null;
        }
        UserAccount result = new UserAccount();
        result.setUserId(account.getUserId().toString());
        result.setName(account.getName());
        result.setPassword(account.getPassword());
        return result;
    }

}
