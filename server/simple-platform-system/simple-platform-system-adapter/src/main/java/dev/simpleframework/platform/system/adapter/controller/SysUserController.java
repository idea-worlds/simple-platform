package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.core.PageData;
import dev.simpleframework.core.PageRequest;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.commons.IdsArgs;
import dev.simpleframework.platform.system.api.SysUserApi;
import dev.simpleframework.platform.system.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 系统用户
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {
    private final SysUserApi api;

    /**
     * 获取用户信息
     */
    @GetMapping(value = "/{id}")
    public SysUserResponse get(@PathVariable Long id) {
        return this.api.findUser(id);
    }

    /**
     * 分页查询用户信息
     */
    @GetMapping(value = "/page")
    public PageData<SysUserResponse> page(@ModelAttribute SysUserPageQueryArgs args, PageRequest pageRequest) {
        // 非【系统管理】只能查当前空间的人员
        if (!CommonUtils.isAdminWorkspace()) {
            args.setWorkspace(CommonUtils.getCurrentWorkspace());
        }
        return this.api.findUsers(args, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    /**
     * 获取用户账号列表
     */
    @GetMapping(value = "/accounts/{id}")
    public List<SysUserAccountResponse> accounts(@PathVariable Long id) {
        return this.api.findAccounts(id);
    }

    /**
     * 新增用户
     */
    @PostMapping(value = "")
    public Long add(@RequestBody SysUserAddArgs args) {
        // 非【系统管理】新增用户时只能设置当前工作空间
        String workspace = CommonUtils.getCurrentWorkspace();
        if (!CommonUtils.isAdminWorkspace(workspace)) {
            args.setWorkspaces(Collections.singletonList(workspace));
        }
        return this.api.addUser(args);
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SysUserUpdateArgs args) {
        this.api.updateUser(id, args);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        String workspace = CommonUtils.getCurrentWorkspace();
        // 【系统管理】删除用户
        if (CommonUtils.isAdminWorkspace(workspace)) {
            this.api.removeUser(Collections.singletonList(id));
        }
        // 非【系统管理】删除用户的当前工作空间
        else {
            this.api.removeWorkspaces(Collections.singletonList(id), workspace);
        }
    }

    /**
     * 删除用户（批量）
     */
    @DeleteMapping("/batch")
    public void remove(@RequestBody IdsArgs args) {
        String workspace = CommonUtils.getCurrentWorkspace();
        // 【系统管理】删除用户
        if (CommonUtils.isAdminWorkspace(workspace)) {
            this.api.removeUser(args.getIds());
        }
        // 非【系统管理】删除用户的当前工作空间
        else {
            this.api.removeWorkspaces(args.getIds(), workspace);
        }
    }

    /**
     * 设置用户工作空间
     */
    @PostMapping("/workspaces/{id}")
    public void setWorkspaces(@PathVariable Long id, @RequestBody SysUserSetWorkspacesArgs args) {
        this.api.setWorkspaces(id, args);
    }

    /**
     * 设置用户组织
     */
    @PostMapping("/orgs/{id}")
    public void setOrgs(@PathVariable Long id, @RequestBody SysUserSetOrgsArgs args) {
        this.api.setOrgs(id, args.getOrgs());
    }

    /**
     * 启用用户
     */
    @PostMapping("/status/enable")
    public void enable(@RequestBody IdsArgs args) {
        this.api.enable(args.getIds());
    }

    /**
     * 禁用用户
     */
    @PostMapping("/status/disable")
    public void disable(@RequestBody IdsArgs args) {
        this.api.disable(args.getIds());
    }

    /**
     * 锁定用户
     */
    @PostMapping("/status/lock")
    public void lock(@RequestBody IdsArgs args) {
        String workspace = CommonUtils.getCurrentWorkspace();
        this.api.lock(args.getIds(), workspace);
    }

    /**
     * 解锁用户
     */
    @PostMapping("/status/unlock")
    public void unlock(@RequestBody IdsArgs args) {
        String workspace = CommonUtils.getCurrentWorkspace();
        this.api.unlock(args.getIds(), workspace);
    }

}
