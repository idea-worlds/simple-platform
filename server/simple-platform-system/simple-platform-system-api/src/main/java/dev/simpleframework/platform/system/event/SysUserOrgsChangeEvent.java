package dev.simpleframework.platform.system.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 事件：用户设置/删除组织
 *
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserOrgsChangeEvent extends BaseOperateUsersEvent {
    public static final String TYPE_SET = "SET";
    public static final String TYPE_REMOVE = "REMOVE";

    private List<String> orgs;
    private String type;
}
