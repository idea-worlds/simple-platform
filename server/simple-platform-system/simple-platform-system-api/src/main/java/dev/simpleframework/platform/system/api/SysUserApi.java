package dev.simpleframework.platform.system.api;

import dev.simpleframework.core.PageData;
import dev.simpleframework.platform.system.model.*;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysUserApi {

    /**
     * 查询用户信息
     *
     * @param id 用户 id
     */
    SysUserResponse findUser(Long id);

    /**
     * 分页查询用户
     */
    PageData<SysUserResponse> findUsers(SysUserPageQueryArgs args, int pageNum, int pageSize);

    /**
     * 查询用户账号
     *
     * @param id 用户 id
     */
    List<SysUserAccountResponse> findAccounts(Long id);

    /**
     * 新增用户
     *
     * @param args 用户信息
     * @return 用户 id
     */
    Long addUser(SysUserAddArgs args);

    /**
     * 修改用户信息
     *
     * @param id   用户 id
     * @param args 用户信息
     */
    void updateUser(Long id, SysUserUpdateArgs args);

    /**
     * 删除用户
     *
     * @param ids 用户 ids
     */
    void removeUser(List<Long> ids);

    /**
     * 设置用户工作空间
     *
     * @param id 用户 id
     */
    void setWorkspaces(Long id, SysUserSetWorkspacesArgs args);

    /**
     * 删除用户工作空间
     *
     * @param ids       用户 ids
     * @param workspace 工作空间
     */
    default void removeWorkspaces(List<Long> ids, String workspace) {
        removeWorkspaces(ids, Collections.singletonList(workspace));
    }

    /**
     * 删除用户工作空间
     *
     * @param ids        用户 ids
     * @param workspaces 工作空间
     */
    void removeWorkspaces(List<Long> ids, List<String> workspaces);

    /**
     * 设置用户组织
     *
     * @param id   用户 id
     * @param orgs 组织
     */
    default void setOrgs(Long id, List<String> orgs) {
        setOrgs(Collections.singletonList(id), orgs);
    }

    /**
     * 设置用户组织
     *
     * @param ids  用户 ids
     * @param orgs 组织
     */
    void setOrgs(List<Long> ids, List<String> orgs);

    /**
     * 删除用户组织
     *
     * @param id   用户 id
     * @param orgs 组织
     */
    default void removeOrgs(Long id, List<String> orgs) {
        removeOrgs(Collections.singletonList(id), orgs);
    }

    /**
     * 删除用户组织
     *
     * @param ids  用户 ids
     * @param orgs 组织
     */
    void removeOrgs(List<Long> ids, List<String> orgs);

    /**
     * 启用用户
     *
     * @param ids 用户 ids
     */
    void enable(List<Long> ids);

    /**
     * 禁用用户
     *
     * @param ids 用户 ids
     */
    void disable(List<Long> ids);

    /**
     * 锁定用户
     *
     * @param ids       用户 ids
     * @param workspace 工作空间
     */
    void lock(List<Long> ids, String workspace);

    /**
     * 解锁用户
     *
     * @param ids       用户 ids
     * @param workspace 工作空间
     */
    void unlock(List<Long> ids, String workspace);

}
