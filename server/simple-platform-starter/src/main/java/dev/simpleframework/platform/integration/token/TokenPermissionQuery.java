package dev.simpleframework.platform.integration.token;

import dev.simpleframework.token.permission.PermissionQuery;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Component
public class TokenPermissionQuery implements PermissionQuery {

    @Override
    public List<String> listPermissions() {
        return Collections.emptyList();
    }

    @Override
    public List<String> listRoles() {
        return Collections.emptyList();
    }

}
