package dev.simpleframework.platform.system.infra.repo.impl;

import dev.simpleframework.platform.system.domain.SystemEntity;
import dev.simpleframework.platform.system.infra.data.System;
import dev.simpleframework.platform.system.infra.exception.SystemNotFoundException;
import dev.simpleframework.platform.system.infra.repo.SystemRepo;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;
import org.springframework.stereotype.Component;

/**
 * 示例：仓储接口实现类
 */
@Component
public class SystemRepoImpl implements SystemRepo {
    private static final System dao = new System();

    @Override
    public SystemEntity findEntity(Long id) {
        System data = this.findById(id);
        if(data == null) {
            throw new SystemNotFoundException(id);
        }
        return SystemEntity.create(id, data);
    }

    @Override
    public void save(SystemEntity entity) {
        System data = entity.data();
        if (entity.isNew()) {
            data.insert();
        } else {
            data.updateById();
        }
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public System findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Page<System> pageByConditions(int pageNum, int pageSize, QueryConfig config) {
        return dao.pageByConditions(pageNum, pageSize, config);
    }

}
