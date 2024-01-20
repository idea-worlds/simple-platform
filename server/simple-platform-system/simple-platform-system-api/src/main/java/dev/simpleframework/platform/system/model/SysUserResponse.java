package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserResponse {

    private Long id;
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
    private List<Workspace> workspaces = new ArrayList<>();
    /**
     * 组织
     */
    private List<String> orgs = new ArrayList<>();
    /**
     * 排序号（越高越前）
     */
    private Integer sortNo = 0;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Long createTime;

    public void addWorkspace(String code, List<String> types, Boolean lock) {
        Workspace workspace = new Workspace(code, types, lock);
        this.workspaces.add(workspace);
    }

    @Data
    public static class Workspace {
        private String code;
        private List<String> types;
        private Boolean lock;

        public Workspace() {
            this.types = new ArrayList<>();
            this.lock = false;
        }

        public Workspace(String code, List<String> types, Boolean lock) {
            this.code = code;
            this.types = types;
            this.lock = lock;
        }
    }

}
