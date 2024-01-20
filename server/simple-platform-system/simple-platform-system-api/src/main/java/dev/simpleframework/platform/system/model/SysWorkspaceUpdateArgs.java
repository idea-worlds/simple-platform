package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysWorkspaceUpdateArgs {

    /**
     * 类型
     */
    @NotBlank(message = "{blank.type}")
    private String type;
    /**
     * 名称
     */
    @NotBlank(message = "{blank.name}")
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否外部应用
     */
    private Integer ext;
    /**
     * 外部链接
     */
    private String extUrl;
    /**
     * 外部信息
     */
    private Map<String, Object> extInfo;
    /**
     * 序号（越大越前）
     */
    private Integer sortNo;

}
