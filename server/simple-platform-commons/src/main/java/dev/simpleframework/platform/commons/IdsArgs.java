package dev.simpleframework.platform.commons;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class IdsArgs implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> ids;

}
