package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.core.PageData;
import dev.simpleframework.crud.core.*;
import dev.simpleframework.platform.system.api.SysUserApi;
import dev.simpleframework.platform.system.app.converter.SysUserConverter;
import dev.simpleframework.platform.system.app.executor.*;
import dev.simpleframework.platform.system.infra.constant.UserStatus;
import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.platform.system.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Service
public class SysUserService implements SysUserApi {

    @Override
    public SysUserResponse findUser(Long id) {
        SysUser user = new SysUser().findById(id);
        return SysUserConverter.toResponse(user);
    }

    @Override
    public PageData<SysUserResponse> findUsers(SysUserPageQueryArgs args, int pageNum, int pageSize) {
        QueryConditions keywordCondition = QueryConditions.or()
                .add(SysUser::getName, ConditionType.like_all, args.getKeyword())
                .add(SysUser::getMobile, ConditionType.like_all, args.getKeyword())
                .add(SysUser::getEmail, ConditionType.like_all, args.getKeyword());
        QueryConditions queryConditions = QueryConditions.and()
                .add(keywordCondition)
                .add(SysUser::getSex, ConditionType.equal, args.getSex())
                .add(SysUser::getWorkspaces, ConditionType.json_exist_key, args.getWorkspace())
                .add(SysUser::getOrgs, ConditionType.array_contains, args.getOrg())
                .add(SysUser::getStatus, ConditionType.equal, args.getStatus());
        QueryConfig config = QueryConfig.of()
                .addCondition(queryConditions)
                .addSorter(QuerySorters.desc(SysUser::getSortNo));
        Page<SysUserResponse> page = new SysUser()
                .pageByConditions(pageNum, pageSize, config)
                .convert(SysUserConverter::toResponse);
        return PageData.of(pageNum, pageSize, page.getTotal(), page.getItems());
    }

    @Override
    public List<SysUserAccountResponse> findAccounts(Long id) {
        QueryConfig config = QueryConfig.of()
                .addCondition(SysUserAccount::getUserId, id);
        return new SysUserAccount().listByConditions(config)
                .stream()
                .map(SysUserConverter::toResponse)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addUser(SysUserAddArgs args) {
        return new SysUserAddExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Long id, SysUserUpdateArgs args) {
        new SysUserUpdateExecutor(id, args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUser(List<Long> ids) {
        new SysUserRemoveExecutor(ids).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setWorkspaces(Long id, SysUserSetWorkspacesArgs args) {
        SysUserChangeWorkspacesExecutor executor = SysUserChangeWorkspacesExecutor.ofSet(id);
        args.getWorkspaces().forEach(w -> executor.setWorkspaceUserTypes(w.getCode(), w.getTypes()));
        executor.exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeWorkspaces(List<Long> ids, List<String> workspaces) {
        SysUserChangeWorkspacesExecutor.ofRemove(ids, workspaces).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setOrgs(List<Long> ids, List<String> orgs) {
        SysUserChangeOrgsExecutor.ofSet(ids, orgs).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeOrgs(List<Long> ids, List<String> orgs) {
        SysUserChangeOrgsExecutor.ofRemove(ids, orgs).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(List<Long> ids) {
        new SysUserChangeStatusExecutor(ids, UserStatus.ENABLE).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disable(List<Long> ids) {
        new SysUserChangeStatusExecutor(ids, UserStatus.DISABLE).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void lock(List<Long> ids, String workspace) {
        SysUserChangeWorkspacesExecutor.ofLock(ids, workspace).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlock(List<Long> ids, String workspace) {
        SysUserChangeWorkspacesExecutor.ofUnlock(ids, workspace).exec();
    }

}
