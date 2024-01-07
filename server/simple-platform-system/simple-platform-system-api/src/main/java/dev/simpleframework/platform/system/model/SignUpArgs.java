package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 入参：用户注册
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SignUpArgs {

    /**
     * 用户名
     */
    @NotBlank(message = "{blank.name}")
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    @Email(message = "{format.email}")
    private String email;
    /**
     * 账号名
     */
    @NotBlank(message = "{blank.account}")
    private String accountName;
    /**
     * 密码
     */
    @NotBlank(message = "{blank.password}")
    private String accountPassword;

}
