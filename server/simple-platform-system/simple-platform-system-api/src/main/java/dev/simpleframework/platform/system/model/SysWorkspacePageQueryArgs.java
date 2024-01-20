package dev.simpleframework.platform.system.model;

import lombok.Data;

/**
 * 入参：系统用户分页查询
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysWorkspacePageQueryArgs {

    /**
     * 编码
     */
    private String code;
    /**
     * 类型
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否外部应用
     */
    private Integer ext;
    /**
     * 状态
     */
    private Integer status;

    public String getCode() {
        return "".equals(code) ? null : code;
    }

    public String getType() {
        return "".equals(type) ? null : type;
    }

    public String getName() {
        return "".equals(name) ? null : name;
    }

}
