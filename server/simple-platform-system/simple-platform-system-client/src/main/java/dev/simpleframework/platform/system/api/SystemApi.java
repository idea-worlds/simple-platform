package dev.simpleframework.platform.system.api;

import dev.simpleframework.platform.system.model.SystemModifyArgs;
import dev.simpleframework.platform.system.model.SystemPageQueryArgs;
import dev.simpleframework.platform.system.model.SystemResponse;
import dev.simpleframework.core.PageResponse;

/**
 * 示例：接口
 */
public interface SystemApi {

    /**
     * 新增
     */
    Long addSystem(SystemModifyArgs args);

    /**
     * 修改
     */
    void updateSystem(Long id, SystemModifyArgs args);

    /**
     * 删除
     */
    void removeSystem(Long id);

    /**
     * 根据 id 查询
     */
    SystemResponse findSystem(Long id);

    /**
     * 分页查询
     */
    PageResponse<SystemResponse> pageSystem(SystemPageQueryArgs query);

}
