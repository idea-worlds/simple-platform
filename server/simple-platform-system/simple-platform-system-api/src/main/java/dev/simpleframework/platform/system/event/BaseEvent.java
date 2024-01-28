package dev.simpleframework.platform.system.event;

import dev.simpleframework.core.Pair;
import dev.simpleframework.platform.commons.CommonUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Getter
@Setter
public abstract class BaseEvent {
    /**
     * 操作人
     */
    private Long operateUserId;
    private String operateUserName;

    public void fillUser() {
        Pair<Long, String> user = CommonUtils.getLoginUser();
        this.setOperateUserId(user.getLeft());
        this.setOperateUserName(user.getRight());
    }

}
