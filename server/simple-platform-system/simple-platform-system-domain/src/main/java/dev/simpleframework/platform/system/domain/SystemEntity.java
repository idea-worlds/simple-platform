package dev.simpleframework.platform.system.domain;

import dev.simpleframework.platform.system.infra.data.System;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 示例：领域模型
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemEntity {

    private boolean isNew = true;
    private Long id;
    private System data;

    public static SystemEntity create() {
        return new SystemEntity();
    }

    public static SystemEntity create(Long id, System data) {
        SystemEntity entity = new SystemEntity();
        entity.isNew = false;
        entity.id = id;
        entity.data = data;
        return entity;
    }

    public Long id() {
        if (this.id == null && this.data != null) {
            this.id = this.data.getId();
        }
        return this.id;
    }

    public System data() {
        return this.data;
    }

    public SystemEntity data(System data) {
        data.setId(this.id);
        if(this.data == null) {
            this.data = data;
            return this;
        }
        // todo set fields
        // this.data = data;
        return this;
    }

    public boolean isNew() {
        return this.isNew;
    }

}
