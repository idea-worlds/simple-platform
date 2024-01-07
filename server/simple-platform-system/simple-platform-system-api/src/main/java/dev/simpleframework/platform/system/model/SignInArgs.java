package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 入参：用户登录
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SignInArgs {

    /**
     * 账号类型
     */
    @NotBlank(message = "{blank.type}")
    private String type;
    /**
     * 账号名
     */
    @NotBlank(message = "{blank.account}")
    private String name;
    /**
     * 密码
     */
    @NotBlank(message = "{blank.password}")
    private String password;

}
