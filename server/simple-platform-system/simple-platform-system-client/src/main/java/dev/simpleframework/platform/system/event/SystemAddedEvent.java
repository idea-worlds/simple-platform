package dev.simpleframework.platform.system.event;

import dev.simpleframework.platform.system.model.SystemModifyArgs;
import lombok.Data;

/**
 * 示例：事件类
 */
@Data
public class SystemAddedEvent {

    private Long id;
    private SystemModifyArgs args;

    public static SystemAddedEvent of(Long id, SystemModifyArgs args) {
        SystemAddedEvent event = new SystemAddedEvent();
        event.setId(id);
        event.setArgs(args);
        return event;
    }

}
