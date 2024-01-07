package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户账号
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserAccount extends BaseDataModel<SysUserAccount> {

    /**
     * 用户 id
     */
    private Long userId;
    /**
     * 账号类型
     */
    private String type;
    /**
     * 账号名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 最后登录 ip
     */
    private String lastLoginIp;
    /**
     * 最后登录浏览器
     */
    private String lastLoginAgent;
    /**
     * 最后登录时间
     */
    private Long lastLoginTime;

}
