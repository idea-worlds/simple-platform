package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserAddArgs {

    /**
     * 用户名
     */
    @NotBlank(message = "{blank.name}")
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
    /**
     * 工作空间
     */
    private List<String> workspaces;
    /**
     * 组织
     */
    private List<String> orgs;
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

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

}
