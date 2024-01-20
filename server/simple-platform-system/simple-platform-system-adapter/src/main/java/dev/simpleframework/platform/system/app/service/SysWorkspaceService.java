package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.core.PageData;
import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;
import dev.simpleframework.crud.core.QuerySorters;
import dev.simpleframework.platform.system.api.SysWorkspaceApi;
import dev.simpleframework.platform.system.app.converter.SysWorkspaceConverter;
import dev.simpleframework.platform.system.app.executor.SysWorkspaceAddExecutor;
import dev.simpleframework.platform.system.app.executor.SysWorkspaceChangeStatusExecutor;
import dev.simpleframework.platform.system.app.executor.SysWorkspaceRemoveExecutor;
import dev.simpleframework.platform.system.app.executor.SysWorkspaceUpdateExecutor;
import dev.simpleframework.platform.system.infra.constant.WorkspaceStatus;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceAddArgs;
import dev.simpleframework.platform.system.model.SysWorkspacePageQueryArgs;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;
import dev.simpleframework.platform.system.model.SysWorkspaceUpdateArgs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Service
public class SysWorkspaceService implements SysWorkspaceApi {

    @Override
    public SysWorkspaceResponse findWorkspace(Long id) {
        SysWorkspace workspace = new SysWorkspace().findById(id);
        return SysWorkspaceConverter.toResponse(workspace);
    }

    @Override
    public PageData<SysWorkspaceResponse> findWorkspaces(SysWorkspacePageQueryArgs args, int pageNum, int pageSize) {
        QueryConfig config = QueryConfig.of()
                .addCondition(SysWorkspace::getCode, ConditionType.like_all, args.getCode())
                .addCondition(SysWorkspace::getType, ConditionType.equal, args.getType())
                .addCondition(SysWorkspace::getName, ConditionType.like_all, args.getName())
                .addCondition(SysWorkspace::getExtFlag, ConditionType.equal, args.getExt())
                .addCondition(SysWorkspace::getStatus, ConditionType.equal, args.getStatus())
                .addSorter(QuerySorters.desc(SysWorkspace::getSortNo));
        Page<SysWorkspaceResponse> page = new SysWorkspace()
                .pageByConditions(pageNum, pageSize, config)
                .convert(SysWorkspaceConverter::toResponse);
        return PageData.of(pageNum, pageSize, page.getTotal(), page.getItems());
    }

    @Override
    public List<SysWorkspaceResponse> findWorkspacesByCodes(List<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return Collections.emptyList();
        }
        QueryConfig config = QueryConfig.of()
                .addSorter(QuerySorters.desc(SysWorkspace::getSortNo));
        if (codes.size() == 1) {
            config.addCondition(SysWorkspace::getCode, ConditionType.equal, codes.get(0));
        } else {
            config.addCondition(SysWorkspace::getCode, ConditionType.in, codes);
        }
        return new SysWorkspace().listByConditions(config)
                .stream()
                .map(SysWorkspaceConverter::toResponse)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addWorkspace(SysWorkspaceAddArgs args) {
        return new SysWorkspaceAddExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWorkspace(Long id, SysWorkspaceUpdateArgs args) {
        new SysWorkspaceUpdateExecutor(id, args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeWorkspaces(List<Long> ids) {
        new SysWorkspaceRemoveExecutor(ids).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> ids) {
        new SysWorkspaceChangeStatusExecutor(ids, WorkspaceStatus.ENABLE).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(List<Long> ids) {
        new SysWorkspaceChangeStatusExecutor(ids, WorkspaceStatus.DISABLE).exec();
    }

}
