package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.core.ListData;
import dev.simpleframework.core.PageData;
import dev.simpleframework.core.PageRequest;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.commons.IdsArgs;
import dev.simpleframework.platform.system.api.SysWorkspaceApi;
import dev.simpleframework.platform.system.model.SysWorkspaceAddArgs;
import dev.simpleframework.platform.system.model.SysWorkspacePageQueryArgs;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;
import dev.simpleframework.platform.system.model.SysWorkspaceUpdateArgs;
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
    @GetMapping(value = "/{id}")
    public SysWorkspaceResponse get(@PathVariable Long id) {
        return this.api.findWorkspace(id);
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
    @GetMapping(value = "/list/code")
    public ListData<SysWorkspaceResponse> list(@RequestParam(required = false) String codes) {
        List<SysWorkspaceResponse> responses = this.api.findWorkspacesByCodes(CommonUtils.parseList(codes));
        return PageData.of(responses);
    }

    /**
     * 新增工作空间
     */
    @PostMapping(value = "")
    public Long add(@Validated @RequestBody SysWorkspaceAddArgs args) {
        return this.api.addWorkspace(args);
    }

    /**
     * 修改工作空间
     */
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Validated @RequestBody SysWorkspaceUpdateArgs args) {
        this.api.updateWorkspace(id, args);
    }

    /**
     * 删除工作空间（批量）
     */
    @DeleteMapping("")
    public void remove(@RequestBody IdsArgs args) {
        this.api.removeWorkspaces(args.getIds());
    }

    /**
     * 启用工作空间
     */
    @PostMapping(value = "/status/enable")
    public void enable(@RequestBody IdsArgs args) {
        this.api.enable(args.getIds());
    }

    /**
     * 禁用工作空间
     */
    @PostMapping(value = "/status/disable")
    public void disable(@RequestBody IdsArgs args) {
        this.api.disable(args.getIds());
    }

}
