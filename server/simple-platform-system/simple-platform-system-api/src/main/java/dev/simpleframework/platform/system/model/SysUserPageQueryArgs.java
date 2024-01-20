package dev.simpleframework.platform.system.model;

import lombok.Data;

/**
 * 入参：系统用户分页查询
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserPageQueryArgs {

    /**
     * 关键字（用户名、手机号、邮箱）
     */
    private String keyword;
    /**
     * 性别
     */
    private String sex;
    /**
     * 工作空间
     */
    private String workspace;
    /**
     * 组织
     */
    private String org;
    /**
     * 状态
     */
    private Integer status;

    public String getKeyword() {
        return "".equals(keyword) ? null : keyword;
    }

    public String getSex() {
        return "".equals(sex) ? null : sex;
    }

    public String getWorkspace() {
        return "".equals(workspace) ? null : workspace;
    }

    public String getOrg() {
        return "".equals(org) ? null : org;
    }

}
