package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.NotBlank;
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
    private List<Item> items;
    /**
     * 序号（越大越前）
     */
    private Integer sortNo;

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
         * 序号（越大越前）
         */
        private Integer sortNo;

        public Integer getSortNo() {
            return sortNo == null ? 0 : sortNo;
        }
    }

}
