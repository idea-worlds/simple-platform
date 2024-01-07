package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.system.infra.util.SystemUtil;
import dev.simpleframework.token.user.UserAccountPasswordValidator;
import org.springframework.stereotype.Component;

/**
 * simple-token 用户账号密码校验器实现类
 */
@Component
public class TokenUserAccountPasswordValidator implements UserAccountPasswordValidator {

    @Override
    public boolean validate(String accountType, String paramPassword, String storePassword) {
        return paramPassword != null
                && SystemUtil.encryptAccountPassword(paramPassword).equals(storePassword);
    }

}
