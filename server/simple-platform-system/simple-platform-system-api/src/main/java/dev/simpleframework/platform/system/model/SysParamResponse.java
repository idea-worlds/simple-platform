package dev.simpleframework.platform.system.model;

import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysParamResponse {

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String val;
    /**
     * 描述
     */
    private String description;

}
