package dev.simpleframework.platform.system.adapter.controller;

import dev.simpleframework.platform.system.api.SysSignApi;
import dev.simpleframework.platform.system.model.SignInArgs;
import dev.simpleframework.platform.system.model.SignUpArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册、登录、登出
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SysSignController {
    private final SysSignApi api;

    /**
     * 注册
     */
    @PostMapping("/up")
    public void up(@Validated @RequestBody SignUpArgs args) {
        this.api.up(args);
    }

    /**
     * 登录
     */
    @PostMapping("/in")
    public String in(@Validated @RequestBody SignInArgs args) {
        return this.api.in(args);
    }

    /**
     * 登出
     */
    @PostMapping("/out")
    public void out() {
        this.api.out();
    }

}
