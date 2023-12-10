package dev.simpleframework.platform.system.app.executor;

import dev.simpleframework.platform.system.app.converter.SystemConverter;
import dev.simpleframework.platform.system.model.SystemModifyArgs;
import dev.simpleframework.platform.system.domain.SystemEntity;
import dev.simpleframework.platform.system.event.SystemAddedEvent;
import dev.simpleframework.platform.system.event.SystemRemovedEvent;
import dev.simpleframework.platform.system.event.SystemUpdatedEvent;
import dev.simpleframework.platform.system.infra.data.System;
import dev.simpleframework.platform.system.infra.repo.SystemRepo;
import dev.simpleframework.util.SimpleSpringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 示例：通用命令（简单增删改）执行器
 */
@Component
@RequiredArgsConstructor
public class SystemCommonCommand {
    private final SystemRepo repo;

    public Long add(SystemModifyArgs args) {
        System data = SystemConverter.toData(args);
        SystemEntity entity = SystemEntity.create().data(data);
        this.repo.save(entity);

        SystemAddedEvent event = SystemAddedEvent.of(entity.id(), args);
        SimpleSpringUtils.publishEvent(event);
        return entity.id();
    }

    public void update(Long id, SystemModifyArgs args) {
        System data = SystemConverter.toData(args);
        data.setId(id);

        SystemEntity entity = this.repo.findEntity(id).data(data);
        this.repo.save(entity);

        SystemUpdatedEvent event = SystemUpdatedEvent.of(id, args);
        SimpleSpringUtils.publishEvent(event);
    }

    public void removeById(Long id) {
        this.repo.deleteById(id);

        SystemRemovedEvent event = SystemRemovedEvent.of(id);
        SimpleSpringUtils.publishEvent(event);
    }

}
