package dev.simpleframework.platform.integration.token;

import dev.simpleframework.token.user.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * simple-token 用户（拓展）
 */
@Setter
@Getter
public class TokenUser extends UserInfo {
    private String name;
    private Map<String, List<String>> workspaces = new LinkedHashMap<>();

    public void addWorkspace(String code, List<String> types) {
        this.workspaces.put(code, types);
    }

}
