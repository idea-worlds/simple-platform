package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserUpdateArgs {

    /**
     * 用户名
     */
    private String name;
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
     * 性别
     */
    private String sex;

}
