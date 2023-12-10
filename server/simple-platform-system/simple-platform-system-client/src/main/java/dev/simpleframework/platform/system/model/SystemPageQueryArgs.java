package dev.simpleframework.platform.system.model;

import dev.simpleframework.core.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 示例：分页查询入参
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemPageQueryArgs extends PageRequest {

}
