package dev.simpleframework.platform.system.infra.constant;

/**
 * 用户状态
 *
 * @author loyayz (loyayz@foxmail.com)
 */
public final class UserStatus {

    /**
     * 未审核
     */
    public static final UserStatus INACTIVE = new UserStatus("INACTIVE");
    /**
     * 正常
     */
    public static final UserStatus ENABLE = new UserStatus("ENABLE");
    /**
     * 停用
     * public static final UserStatus
     */
    public static final UserStatus DISABLE = new UserStatus("DISABLE");

    private final String name;

    private UserStatus(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public static UserStatus valueOf(String name) {
        if (name == null) {
            return null;
        }
        return switch (name) {
            case "INACTIVE" -> INACTIVE;
            case "ENABLE" -> ENABLE;
            case "DISABLE" -> DISABLE;
            default -> null;
        };
    }

    public static boolean isEnable(String name) {
        return ENABLE == valueOf(name);
    }

    @Override
    public String toString() {
        return this.name;
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
        if (obj instanceof UserStatus o) {
            return this.name.equals(o.name);
        }
        return false;
    }

}
