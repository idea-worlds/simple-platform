package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.core.ListData;
import dev.simpleframework.core.PageData;
import dev.simpleframework.core.PageRequest;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.api.SysWorkspaceApi;
import dev.simpleframework.platform.system.model.SysWorkspaceModifyArgs;
import dev.simpleframework.platform.system.model.SysWorkspacePageQueryArgs;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工作空间
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@RestController
@RequestMapping("/sys/workspace")
@RequiredArgsConstructor
public class SysWorkspaceController {
    private final SysWorkspaceApi api;

    /**
     * 获取工作空间信息
     */
    @GetMapping(value = "")
    public SysWorkspaceResponse get(@RequestParam String code) {
        return this.api.findWorkspace(code);
    }

    /**
     * 分页查询工作空间信息
     */
    @GetMapping(value = "/page")
    public PageData<SysWorkspaceResponse> page(@ModelAttribute SysWorkspacePageQueryArgs args, PageRequest pageRequest) {
        return this.api.findWorkspaces(args, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    /**
     * 根据编码查询工作空间信息集合
     */
    @GetMapping(value = "/list")
    public ListData<SysWorkspaceResponse> list(@RequestParam String codes) {
        List<SysWorkspaceResponse> responses = this.api.findWorkspaces(CommonUtils.parseList(codes));
        return PageData.of(responses);
    }

    /**
     * 新增工作空间
     */
    @PostMapping(value = "")
    public void add(@Validated @RequestBody SysWorkspaceModifyArgs args) {
        this.api.addWorkspace(args);
    }

    /**
     * 修改工作空间
     */
    @PutMapping("")
    public void update(@Validated @RequestBody SysWorkspaceModifyArgs args) {
        this.api.updateWorkspace(args);
    }

    /**
     * 删除工作空间（批量）
     */
    @DeleteMapping("")
    public void remove(@RequestParam String codes) {
        this.api.removeWorkspaces(CommonUtils.parseList(codes));
    }

    /**
     * 启用工作空间
     */
    @PostMapping(value = "/status/enable")
    public void enable(@RequestParam String codes) {
        this.api.enable(CommonUtils.parseList(codes));
    }

    /**
     * 禁用工作空间
     */
    @PostMapping(value = "/status/disable")
    public void disable(@RequestParam String codes) {
        this.api.disable(CommonUtils.parseList(codes));
    }

}
