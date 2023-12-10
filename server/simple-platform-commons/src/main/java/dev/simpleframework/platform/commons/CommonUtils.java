package dev.simpleframework.platform.commons;

import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.token.context.ContextManager;
import dev.simpleframework.token.exception.NotPermissionException;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class CommonUtils {

    /**
     * 获取当前登录用户 id
     */
    public static Long getLoginUserId() {
        return SimpleTokens.getLoginIdAsLong();
    }

    /**
     * 获取当前登录用户名
     */
    public static String getLoginUserName() {
        return SimpleTokens.getSession().attr(CommonConstant.SESSION_USERNAME);
    }

    /**
     * 获取当前登录用户所在的组织
     */
    public static List<String> getLoginUserOrgs() {
        List<String> result = SimpleTokens.getSession().attr(CommonConstant.SESSION_ORGS);
        return result == null ? Collections.emptyList() : result;
    }

    /**
     * 获取当前登录用户所在的工作空间
     */
    public static List<String> getLoginUserWorkspaces() {
        List<String> result = SimpleTokens.getSession().attr(CommonConstant.SESSION_WORKSPACES);
        return result == null ? Collections.emptyList() : result;
    }

    /**
     * 获取当前请求的工作空间
     */
    public static String getCurrentWorkspace() {
        return ContextManager.findContext().request().getHeader(CommonConstant.HEADER_WORKSPACE);
    }

    /**
     * 当前工作空间是否是【系统管理】
     */
    public static boolean isAdminWorkspace() {
        return CommonConstant.ADMIN_WORKSPACE.equals(getCurrentWorkspace());
    }

    /**
     * 检验当前登录用户是否在工作空间内
     */
    public static void checkLoginUserInWorkspace() {
        String workspace = getCurrentWorkspace();
        if (workspace == null || getLoginUserWorkspaces().contains(workspace)) {
            return;
        }
        throw new NotPermissionException("workspace [" + workspace + "]");
    }

}
