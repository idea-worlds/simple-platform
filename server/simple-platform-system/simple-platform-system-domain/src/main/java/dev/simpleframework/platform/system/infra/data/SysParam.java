package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysParam extends BaseDataModel<SysParam> {

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
