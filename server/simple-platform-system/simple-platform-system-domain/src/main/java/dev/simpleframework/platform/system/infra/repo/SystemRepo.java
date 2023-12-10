package dev.simpleframework.platform.system.infra.repo;

import dev.simpleframework.platform.system.domain.SystemEntity;
import dev.simpleframework.platform.system.infra.data.System;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;

/**
 * 示例：仓储接口
 */
public interface SystemRepo {

    /**
     * 根据 id 查询
     */
    SystemEntity findEntity(Long id);

    /**
     * 保存（无 id 新增，有 id 修改）
     *
     * @param entity 实体
     */
    void save(SystemEntity entity);

    /**
     * 根据 id 删除
     */
    void deleteById(Long id);

    /**
     * 根据 id 查询
     */
    System findById(Long id);

    /**
     * 根据条件查询分页
     */
    Page<System> pageByConditions(int pageNum, int pageSize, QueryConfig config);

}
