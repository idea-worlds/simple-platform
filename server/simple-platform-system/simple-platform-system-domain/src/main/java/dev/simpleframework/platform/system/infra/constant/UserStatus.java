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
    public static final UserStatus INACTIVE = new UserStatus(-1);
    /**
     * 停用
     */
    public static final UserStatus DISABLE = new UserStatus(0);
    /**
     * 正常
     */
    public static final UserStatus ENABLE = new UserStatus(1);

    private final Integer name;

    private UserStatus(Integer name) {
        this.name = name;
    }

    public Integer name() {
        return this.name;
    }

    public static UserStatus valueOf(Integer name) {
        if (name == null) {
            return null;
        }
        return switch (name) {
            case -1 -> INACTIVE;
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
        if (obj instanceof UserStatus o) {
            return this.name.equals(o.name);
        }
        return false;
    }

}
