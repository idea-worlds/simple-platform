package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.platform.system.api.SystemApi;
import dev.simpleframework.platform.system.model.SystemModifyArgs;
import dev.simpleframework.platform.system.model.SystemPageQueryArgs;
import dev.simpleframework.platform.system.model.SystemResponse;
import dev.simpleframework.core.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 示例：controller
 */
@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SystemController {
    private final SystemApi api;

    @GetMapping(value = "/{id}")
    public SystemResponse get(@PathVariable Long id) {
        return this.api.findSystem(id);
    }

    @GetMapping(value = "/page")
    public PageResponse<SystemResponse> page(@ModelAttribute SystemPageQueryArgs query) {
        return this.api.pageSystem(query);
    }

    @PostMapping(value = "")
    public Long add(@RequestBody SystemModifyArgs args) {
        return this.api.addSystem(args);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody SystemModifyArgs args) {
        this.api.updateSystem(id, args);
        return id;
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        this.api.removeSystem(id);
        return id;
    }

}
