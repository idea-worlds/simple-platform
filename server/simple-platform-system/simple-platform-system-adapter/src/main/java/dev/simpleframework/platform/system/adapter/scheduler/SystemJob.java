package dev.simpleframework.platform.system.adapter.scheduler;

import dev.simpleframework.platform.system.api.SystemApi;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * 示例：任务调度
 */
@Component
@RequiredArgsConstructor
public class SystemJob {
    private final SystemApi api;

}