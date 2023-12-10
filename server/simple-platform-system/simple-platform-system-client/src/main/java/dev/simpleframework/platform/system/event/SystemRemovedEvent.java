package dev.simpleframework.platform.system.event;

import lombok.Data;

/**
 * 示例：事件类
 */
@Data
public class SystemRemovedEvent {

    private Long id;

    public static SystemRemovedEvent of(Long id) {
        SystemRemovedEvent event = new SystemRemovedEvent();
        event.setId(id);
        return event;
    }

}
