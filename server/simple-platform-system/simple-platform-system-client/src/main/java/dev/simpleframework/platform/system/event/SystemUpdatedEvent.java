package dev.simpleframework.platform.system.event;

import dev.simpleframework.platform.system.model.SystemModifyArgs;
import lombok.Data;

/**
 * 示例：事件类
 */
@Data
public class SystemUpdatedEvent {

    private Long id;
    private SystemModifyArgs args;

    public static SystemUpdatedEvent of(Long id, SystemModifyArgs args) {
        SystemUpdatedEvent event = new SystemUpdatedEvent();
        event.setId(id);
        event.setArgs(args);
        return event;
    }

}
