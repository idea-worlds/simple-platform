package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserSetWorkspacesArgs {

    /**
     * 工作空间
     */
    private List<Workspace> workspaces;

    public List<Workspace> getWorkspaces() {
        return workspaces == null ? Collections.emptyList() : workspaces;
    }

    @Data
    public static class Workspace {
        /**
         * 工作空间编码
         */
        private String code;
        /**
         * 用户类型
         */
        private List<String> types;
    }

}
