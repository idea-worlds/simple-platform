package dev.simpleframework.platform.system.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysDictModifyArgs {

    /**
     * 编码
     */
    @NotBlank(message = "{blank.code}")
    private String code;
    /**
     * 名称
     */
    @NotBlank(message = "{blank.name}")
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 字典项
     */
    @Valid
    @NotNull(message = "{blank.item}")
    @NotEmpty(message = "{blank.item}")
    private List<Item> items;

    public List<Item> getItems() {
        return items == null ? Collections.emptyList() : items;
    }

    @Data
    public static class Item {
        /**
         * 编码
         */
        @NotBlank(message = "{blank.code}")
        private String code;
        /**
         * 名称
         */
        @NotBlank(message = "{blank.name}")
        private String name;
        /**
         * 值
         */
        @NotBlank(message = "{blank.val}")
        private String val;
    }

}
