package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.crud.BaseModel;
import lombok.Data;

/**
 * 示例：数据模型
 */
@Data
public class System implements BaseModel<System> {

    private Long id;

}
