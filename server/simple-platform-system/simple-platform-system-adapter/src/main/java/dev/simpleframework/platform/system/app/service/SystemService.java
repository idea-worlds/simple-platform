package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.platform.system.api.SystemApi;
import dev.simpleframework.platform.system.app.executor.SystemCommonCommand;
import dev.simpleframework.platform.system.app.executor.SystemCommonQuery;
import dev.simpleframework.platform.system.model.SystemModifyArgs;
import dev.simpleframework.platform.system.model.SystemPageQueryArgs;
import dev.simpleframework.platform.system.model.SystemResponse;
import dev.simpleframework.core.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 示例：接口实现门面，转发请求到具体的执行器
 */
@Service
@RequiredArgsConstructor
public class SystemService implements SystemApi {
    private final SystemCommonCommand commonCommand;
    private final SystemCommonQuery commonQuery;

    @Override
    public Long addSystem(SystemModifyArgs args) {
        return this.commonCommand.add(args);
    }

    @Override
    public void updateSystem(Long id, SystemModifyArgs args) {
        this.commonCommand.update(id, args);
    }

    @Override
    public void removeSystem(Long id) {
        this.commonCommand.removeById(id);
    }

    @Override
    public SystemResponse findSystem(Long id) {
        return this.commonQuery.findById(id);
    }

    @Override
    public PageResponse<SystemResponse> pageSystem(SystemPageQueryArgs query) {
        return this.commonQuery.findPage(query);
    }

}
