package dev.simpleframework.platform.system.api;

import dev.simpleframework.core.PageData;
import dev.simpleframework.platform.system.model.SysWorkspaceModifyArgs;
import dev.simpleframework.platform.system.model.SysWorkspacePageQueryArgs;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysWorkspaceApi {

    /**
     * 查询工作空间信息
     *
     * @param code 编码
     */
    SysWorkspaceResponse findWorkspace(String code);

    /**
     * 分页查询工作空间信息
     */
    PageData<SysWorkspaceResponse> findWorkspaces(SysWorkspacePageQueryArgs args, int pageNum, int pageSize);

    /**
     * 根据编码查询工作空间信息集合
     *
     * @param codes 编码
     */
    List<SysWorkspaceResponse> findWorkspaces(List<String> codes);

    /**
     * 新增工作空间
     */
    void addWorkspace(SysWorkspaceModifyArgs args);

    /**
     * 修改工作空间
     */
    void updateWorkspace(SysWorkspaceModifyArgs args);

    /**
     * 删除工作空间
     *
     * @param codes 编码
     */
    void removeWorkspaces(List<String> codes);

    /**
     * 启用工作空间
     *
     * @param codes 编码
     */
    void enable(List<String> codes);

    /**
     * 禁用工作空间
     *
     * @param codes 编码
     */
    void disable(List<String> codes);

}
