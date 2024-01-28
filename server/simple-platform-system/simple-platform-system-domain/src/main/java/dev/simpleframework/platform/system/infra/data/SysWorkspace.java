package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 工作空间
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysWorkspace extends BaseDataModel<SysWorkspace> {

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
    private Integer extFlag;
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
     * 状态
     */
    private Integer status;

}
