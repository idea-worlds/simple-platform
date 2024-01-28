package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.core.PageData;
import dev.simpleframework.core.PageRequest;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.api.SysDictApi;
import dev.simpleframework.platform.system.model.SysDictModifyArgs;
import dev.simpleframework.platform.system.model.SysDictPageQueryArgs;
import dev.simpleframework.platform.system.model.SysDictResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@RestController
@RequestMapping("/sys/dict")
@RequiredArgsConstructor
public class SysDictController {
    private final SysDictApi api;

    /**
     * 获取数据字典信息
     */
    @GetMapping(value = "")
    public SysDictResponse get(@RequestParam String code) {
        return this.api.findDict(code);
    }

    /**
     * 分页查询数据字典信息
     */
    @GetMapping(value = "/page")
    public PageData<SysDictResponse> page(@ModelAttribute SysDictPageQueryArgs args, PageRequest pageRequest) {
        return this.api.findDicts(args, pageRequest.getPageNum(), pageRequest.getPageSize());
    }

    /**
     * 新增数据字典
     */
    @PostMapping(value = "")
    public void add(@Validated @RequestBody SysDictModifyArgs args) {
        this.api.addDict(args);
    }

    /**
     * 修改数据字典
     */
    @PutMapping("")
    public void update(@Validated @RequestBody SysDictModifyArgs args) {
        this.api.updateDict(args);
    }

    /**
     * 删除数据字典（批量）
     */
    @DeleteMapping("")
    public void remove(@RequestParam String codes) {
        this.api.removeDicts(CommonUtils.parseList(codes));
    }

}
