package dev.simpleframework.platform.integration.token;

import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.token.SimpleTokens;
import dev.simpleframework.token.exception.NotPermissionException;
import dev.simpleframework.token.path.PathActionExecutor;
import dev.simpleframework.token.path.PathActionInit;
import dev.simpleframework.token.path.PathInfo;
import dev.simpleframework.util.Strings;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * simple-token 路径方法初始化构建器实现类
 */
@Component
public class TokenPathActionInit implements PathActionInit {

    @Override
    public List<PathActionExecutor> init() {
        List<PathActionExecutor> result = new ArrayList<>();
        List<PathInfo> permitPaths = SimpleTokens.getGlobalConfig().getPath().getAllPermitPaths();
        PathActionExecutor action = PathActionExecutor.of()
                .notMatchInfo(permitPaths)
                .action(this::checkWorkspace);
        result.add(action);
        return result;
    }

    /**
     * 检验当前登录用户的工作空间权限
     */
    private void checkWorkspace() {
        String currentWorkspace = CommonUtils.getCurrentWorkspace();
        if (Strings.isBlank(currentWorkspace)) {
            throw new NotPermissionException("workspace is blank");
        }
        if (CommonUtils.isHomeWorkspace(currentWorkspace)) {
            return;
        }
        if (CommonUtils.getLoginUserWorkspaces().contains(currentWorkspace)) {
            return;
        }
        throw new NotPermissionException("workspace [" + currentWorkspace + "]");
    }

}
