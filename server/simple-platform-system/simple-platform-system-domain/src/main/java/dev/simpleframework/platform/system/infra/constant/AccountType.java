package dev.simpleframework.platform.system.infra.constant;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class AccountType {

    public static final AccountType NAME = new AccountType("name");

    private final String name;

    public AccountType(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
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
        if (obj instanceof AccountType o) {
            return this.name.equals(o.name);
        }
        return false;
    }

}
