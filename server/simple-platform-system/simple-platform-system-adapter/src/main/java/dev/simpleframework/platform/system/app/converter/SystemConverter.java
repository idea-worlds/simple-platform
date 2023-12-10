package dev.simpleframework.platform.system.app.converter;

import dev.simpleframework.platform.system.model.SystemModifyArgs;
import dev.simpleframework.platform.system.model.SystemResponse;
import dev.simpleframework.platform.system.infra.data.System;

/**
 * 示例：数据转换器
 */
public final class SystemConverter {

    public static SystemResponse toDto(System data) {
        SystemResponse result = new SystemResponse();
        result.setId(data.getId());
        // todo  data -> dto
        return result;
    }

    public static System toData(SystemModifyArgs args) {
        System result = new System();
        // todo  args -> data
        return result;
    }

}
