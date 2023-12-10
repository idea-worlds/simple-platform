package dev.simpleframework.platform.system.infra.exception;

/**
 * 示例：异常 System 不存在
 */
public class SystemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SystemNotFoundException(Long id) {
        super(String.format("%s [%s] cannot be found", "System", id));
    }

}
