package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.commons.CommonConstant;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.token.permission.PermissionQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Component
public class TokenPermissionQuery implements PermissionQuery {

    @Override
    public List<String> listPermissions() {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> workspaces = SimpleTokens.getSession().attr(CommonConstant.SESSION_WORKSPACES);
        for (Map.Entry<String, List<String>> entry : workspaces.entrySet()) {
            String workspace = entry.getKey();
            List<String> userTypes = entry.getValue();
            for (String userType : userTypes) {
                result.add(workspace + "." + userType);
            }
        }
        return result;
    }

    @Override
    public List<String> listRoles() {
        return Collections.emptyList();
    }

}
