package dev.simpleframework.platform.system.adapter.listener;

import dev.simpleframework.platform.system.api.SystemApi;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * 示例：事件监听器
 */
@Component
@RequiredArgsConstructor
public class SystemEventHandler {
    private final SystemApi api;

}