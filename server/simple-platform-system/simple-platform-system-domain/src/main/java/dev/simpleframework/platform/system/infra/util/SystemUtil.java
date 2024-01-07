package dev.simpleframework.platform.system.infra.util;

import cn.hutool.crypto.SecureUtil;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class SystemUtil {

    /**
     * 加密账号密码
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptAccountPassword(String password) {
        return SecureUtil.sha256(password);
    }

}
