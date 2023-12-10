package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.app.converter.SystemConverter;
import dev.simpleframework.platform.system.model.SystemPageQueryArgs;
import dev.simpleframework.platform.system.model.SystemResponse;
import dev.simpleframework.platform.system.infra.data.System;
import dev.simpleframework.platform.system.infra.repo.SystemRepo;
import dev.simpleframework.core.PageResponse;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 示例：通用查询（简单查询）执行器
 */
@Component
@RequiredArgsConstructor
public class SystemCommonQuery {
    private final SystemRepo repo;

    public SystemResponse findById(Long id) {
        System data = this.repo.findById(id);
        return SystemConverter.toDto(data);
    }

    public PageResponse<SystemResponse> findPage(SystemPageQueryArgs query) {
        QueryConfig config = QueryConfig.of();
        // todo  query -> config
        // config.addCondition();
        Page<System> page = this.repo.pageByConditions(query.getPageNum(), query.getPageSize(), config);
        return page.toResponse(SystemConverter::toDto);
    }

}
