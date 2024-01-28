package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysWorkspaceResponse {

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
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否外部应用
     */
    private Boolean ext;
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
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 创建时间
     */
    private Long createTime;

}
