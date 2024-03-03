package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.core.PageData;
import dev.simpleframework.core.PageRequest;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.api.SysParamApi;
import dev.simpleframework.platform.system.model.SysParamModifyArgs;
import dev.simpleframework.platform.system.model.SysParamPageQueryArgs;
import dev.simpleframework.platform.system.model.SysParamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统参数
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@RestController
@RequestMapping("/sys/param")
@RequiredArgsConstructor
public class SysParamController {
    private final SysParamApi api;

    /**
     * 获取系统参数信息
     */
    @GetMapping(value = "")
    public SysParamResponse get(@RequestParam String code) {
        return this.api.findParam(code);
    }

    /**
     * 分页查询系统参数信息
     */
    @GetMapping(value = "/page")
    public PageData<SysParamResponse> page(@ModelAttribute SysParamPageQueryArgs args, PageRequest pageRequest) {
        return this.api.findParams(args, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    /**
     * 新增系统参数
     */
    @PostMapping(value = "")
    public void add(@Validated @RequestBody SysParamModifyArgs args) {
        this.api.addParam(args);
    }

    /**
     * 修改系统参数
     */
    @PutMapping("")
    public void update(@Validated @RequestBody SysParamModifyArgs args) {
        this.api.updateParam(args);
    }

    /**
     * 删除系统参数（批量）
     */
    @DeleteMapping("")
    public void remove(@RequestParam String codes) {
        this.api.removeParams(CommonUtils.parseList(codes));
    }

}
