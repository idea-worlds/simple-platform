package dev.simpleframework.platform.commons;

import dev.simpleframework.core.Pair;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.token.context.ContextManager;
import dev.simpleframework.token.session.SessionInfo;
import dev.simpleframework.util.Strings;

import java.util.*;

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
     * 获取当前登录用户
     */
    public static Pair<Long, String> getLoginUser() {
        SessionInfo session = SimpleTokens.getSession();
        Long id = Long.parseLong(session.getLoginId());
        String name = session.attr(CommonConstant.SESSION_USERNAME);
        return new Pair<>(id, name);
    }

    /**
     * 获取当前登录用户的工作空间信息 <code,userTypes>
     */
    public static Map<String, List<String>> getLoginUserWorkspaceInfo() {
        Map<String, List<String>> result = SimpleTokens.getSession().attr(CommonConstant.SESSION_WORKSPACES);
        return result == null ? Collections.emptyMap() : result;
    }

    /**
     * 获取当前请求的工作空间
     */
    public static String getCurrentWorkspace() {
        return ContextManager.findContext().request().getHeader(CommonConstant.HEADER_WORKSPACE);
    }

    /**
     * 判断当前工作空间是否是【系统管理】
     */
    public static boolean isAdminWorkspace() {
        return isAdminWorkspace(getCurrentWorkspace());
    }

    /**
     * 判断工作空间是否是【系统管理】
     */
    public static boolean isAdminWorkspace(String workspace) {
        return CommonConstant.WORKSPACE_ADMIN.equals(workspace);
    }

    /**
     * 判断工作空间是否是【首页】
     */
    public static boolean isHomeWorkspace(String workspace) {
        return CommonConstant.WORKSPACE_HOME.equals(workspace);
    }

    public static boolean parseBool(Integer num) {
        if (num == null) {
            return false;
        }
        return num == 1;
    }

    public static List<String> parseList(String str) {
        if (Strings.isBlank(str)) {
            return Collections.emptyList();
        }
        return Arrays.stream(str.split(","))
                .filter(Strings::hasText)
                .toList();
    }

}
