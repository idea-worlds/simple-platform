package dev.simpleframework.platform.system.model;

import lombok.Data;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserAccountResponse {

    /**
     * 账号类型
     */
    private String type;
    /**
     * 账号名
     */
    private String name;
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
