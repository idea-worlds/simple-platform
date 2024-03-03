package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysParamModifyArgs {

    /**
     * 编码
     */
    @NotBlank(message = "{blank.code}")
    private String code;
    /**
     * 名称
     */
    @NotBlank(message = "{blank.name}")
    private String name;
    /**
     * 值
     */
    @NotBlank(message = "{blank.val}")
    private String val;
    /**
     * 描述
     */
    private String description;

}
