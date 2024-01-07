package dev.simpleframework.platform.integration.crud;

import dev.simpleframework.crud.annotation.DataOperateUser;
import dev.simpleframework.crud.helper.DataFillStrategy;
import dev.simpleframework.platform.commons.CommonUtils;
import org.springframework.stereotype.Component;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Component
@SuppressWarnings("unchecked")
public class DataOperateUserFillStrategy implements DataFillStrategy {

    @Override
    public <R> R get(Object o) {
        try {
            return (R) CommonUtils.getLoginUserId();
        } catch (Exception ignore) {
            return null;
        }
    }

    @Override
    public Class<?> support() {
        return DataOperateUser.class;
    }

    @Override
    public FillType type() {
        return FillType.NULL;
    }

}
