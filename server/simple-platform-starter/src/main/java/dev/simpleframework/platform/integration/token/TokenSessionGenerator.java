package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.commons.CommonConstant;
import dev.simpleframework.token.session.SessionGenerator;
import dev.simpleframework.token.session.impl.DefaultSessionGenerator;
import dev.simpleframework.token.user.UserInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * simple-token 会话值生成器实现类
 */
@Component
public class TokenSessionGenerator extends DefaultSessionGenerator implements SessionGenerator {

    @Override
    public Map<String, Object> generateAttrs(UserInfo userInfo, long expiredTime) {
        TokenUser user = (TokenUser) userInfo;
        Map<String, Object> result = new HashMap<>();
        result.put(CommonConstant.SESSION_USERNAME, user.getName());
        result.put(CommonConstant.SESSION_WORKSPACES, user.getWorkspaces());
        return result;
    }

}
