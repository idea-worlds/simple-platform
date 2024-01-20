package dev.simpleframework.platform.system.infra.constant;

/**
 * 工作空间状态
 *
 * @author loyayz (loyayz@foxmail.com)
 */
public final class WorkspaceStatus {

    /**
     * 停用
     */
    public static final WorkspaceStatus DISABLE = new WorkspaceStatus(0);
    /**
     * 正常
     */
    public static final WorkspaceStatus ENABLE = new WorkspaceStatus(1);

    private final Integer name;

    private WorkspaceStatus(Integer name) {
        this.name = name;
    }

    public Integer name() {
        return this.name;
    }

    public static WorkspaceStatus valueOf(Integer name) {
        if (name == null) {
            return null;
        }
        return switch (name) {
            case 0 -> DISABLE;
            case 1 -> ENABLE;
            default -> null;
        };
    }

    public static boolean isEnable(Integer name) {
        return ENABLE == valueOf(name);
    }

    @Override
    public String toString() {
        return this.name.toString();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorkspaceStatus o) {
            return this.name.equals(o.name);
        }
        return false;
    }

}
