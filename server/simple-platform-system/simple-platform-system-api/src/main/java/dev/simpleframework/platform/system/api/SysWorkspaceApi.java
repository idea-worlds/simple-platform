package dev.simpleframework.platform.system.api;

import dev.simpleframework.core.PageData;
import dev.simpleframework.platform.system.model.SysWorkspaceAddArgs;
import dev.simpleframework.platform.system.model.SysWorkspacePageQueryArgs;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;
import dev.simpleframework.platform.system.model.SysWorkspaceUpdateArgs;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysWorkspaceApi {

    /**
     * 查询工作空间信息
     *
     * @param id 工作空间 id
     */
    SysWorkspaceResponse findWorkspace(Long id);

    /**
     * 分页查询工作空间信息
     */
    PageData<SysWorkspaceResponse> findWorkspaces(SysWorkspacePageQueryArgs args, int pageNum, int pageSize);

    /**
     * 根据编码查询工作空间信息集合
     *
     * @param codes 工作空间编码
     */
    List<SysWorkspaceResponse> findWorkspacesByCodes(List<String> codes);

    /**
     * 新增工作空间
     */
    Long addWorkspace(SysWorkspaceAddArgs args);

    /**
     * 修改工作空间
     *
     * @param id 工作空间 id
     */
    void updateWorkspace(Long id, SysWorkspaceUpdateArgs args);

    /**
     * 删除工作空间
     *
     * @param ids 工作空间 ids
     */
    void removeWorkspaces(List<Long> ids);

    /**
     * 启用工作空间
     *
     * @param ids 工作空间 ids
     */
    void enable(List<Long> ids);

    /**
     * 禁用工作空间
     *
     * @param ids 工作空间 ids
     */
    void disable(List<Long> ids);

}
