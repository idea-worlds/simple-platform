package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import dev.simpleframework.platform.commons.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 系统用户
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseDataModel<SysUser> {

    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 头像
     */
    private Long avatar;
    /**
     * 工作空间
     */
    private Map<String, Workspace> workspaces;
    /**
     * 组织
     */
    private List<String> orgs;
    /**
     * 排序号（越高越前）
     */
    private Integer sortNo;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 设置工作空间
     */
    public void changeWorkspaces(List<String> workspaces) {
        if (workspaces == null) {
            return;
        }
        Map<String, List<String>> data = new LinkedHashMap<>();
        for (String workspace : workspaces) {
            data.put(workspace, Collections.emptyList());
        }
        this.changeWorkspaces(data);
    }

    /**
     * 设置工作空间
     *
     * @param workspaceUserTypes <code,types>
     */
    public void changeWorkspaces(Map<String, List<String>> workspaceUserTypes) {
        if (workspaceUserTypes == null || workspaceUserTypes.isEmpty()) {
            this.workspaces.clear();
            return;
        }
        if (this.workspaces == null) {
            this.workspaces = new LinkedHashMap<>();
        }
        Map<String, Workspace> data = new LinkedHashMap<>();
        workspaceUserTypes.forEach((code, types) -> {
            if (types == null || types.isEmpty()) {
                types = new ArrayList<>();
                types.add(CommonConstant.USER_TYPE_USER);
            }
            boolean lock = this.workspaces.containsKey(code) && this.workspaces.get(code).getLock();
            data.put(code, new Workspace(types, lock));
        });
        this.workspaces = data;
    }

    public void removeWorkspaces(List<String> workspaces) {
        if (this.workspaces == null || workspaces == null) {
            return;
        }
        for (String workspace : workspaces) {
            this.workspaces.remove(workspace);
        }
    }

    public void changeWorkspacesLock(List<String> workspaces, boolean lock) {
        if (this.workspaces == null || workspaces == null) {
            return;
        }
        for (String workspace : workspaces) {
            Workspace data = this.workspaces.get(workspace);
            if (data == null) {
                continue;
            }
            data.setLock(lock);
        }
    }

    @Data
    public static class Workspace {
        private List<String> types;
        private Boolean lock;

        public Workspace() {
            this.types = new ArrayList<>();
            this.lock = false;
        }

        public Workspace(String type) {
            List<String> types = new ArrayList<>();
            types.add(type);
            this.types = types;
            this.lock = false;
        }

        public Workspace(List<String> types) {
            this.types = types;
            this.lock = false;
        }

        public Workspace(List<String> types, boolean lock) {
            this.types = types;
            this.lock = lock;
        }

    }

}
