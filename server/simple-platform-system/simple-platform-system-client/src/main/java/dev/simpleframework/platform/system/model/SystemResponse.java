package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 示例：返回值
 */
@Data
public class SystemResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

}
