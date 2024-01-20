package dev.simpleframework.platform.integration.spring;

import dev.simpleframework.platform.commons.CommonConstant;
import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.commons.annotation.CheckWorkspaceUserType;
import dev.simpleframework.token.exception.InvalidPermissionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Component
@Order
public class SpringGlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            CheckWorkspaceUserType annotation = method.getDeclaringClass().getAnnotation(CheckWorkspaceUserType.class);
            this.checkPermission(annotation);
            annotation = method.getAnnotation(CheckWorkspaceUserType.class);
            this.checkPermission(annotation);

        }
        return true;
    }

    private void checkPermission(CheckWorkspaceUserType annotation) {
        if (annotation == null) {
            return;
        }
        Map<String, List<String>> info = CommonUtils.getLoginUserWorkspaceInfo();
        // 系统管理员拥有所有权限
        boolean isAdmin = info
                .getOrDefault(CommonConstant.WORKSPACE_ADMIN, Collections.emptyList())
                .contains(CommonConstant.WORKSPACE_ADMIN);
        if (isAdmin) {
            return;
        }
        // 校验用户在当前工作空间下的用户类型
        List<String> actual = info.getOrDefault(CommonUtils.getCurrentWorkspace(), Collections.emptyList());
        String[] define = annotation.value();
        if (Collections.disjoint(actual, Arrays.asList(define))) {
            throw new InvalidPermissionException(String.join(",", define), false);
        }
    }

}
