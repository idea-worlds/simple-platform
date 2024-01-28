package dev.simpleframework.platform.system.model;

import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysDictPageQueryArgs {

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    public String getCode() {
        return "".equals(code) ? null : code;
    }

    public String getName() {
        return "".equals(name) ? null : name;
    }

}
