package dev.simpleframework.platform.system.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysDictResponse {

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
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

    public void addItem(String code, String name, Integer sortNo) {
        Item item = new Item(code, name, sortNo);
        this.items.add(item);
        this.items.sort((o1, o2) -> o2.getSortNo().compareTo(o1.getSortNo()));
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        /**
         * 编码
         */
        private String code;
        /**
         * 名称
         */
        private String name;
        /**
         * 序号（越大越前）
         */
        private Integer sortNo;

        public Item(String code, String name, Integer sortNo) {
            this.code = code;
            this.name = name;
            this.sortNo = sortNo;
        }
    }

}
