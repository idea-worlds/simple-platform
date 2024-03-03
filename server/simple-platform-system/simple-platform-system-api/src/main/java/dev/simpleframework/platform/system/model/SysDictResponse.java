package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.util.Comparator;
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

    public void addItem(String code, String name, String val) {
        Item item = new Item();
        item.setCode(code);
        item.setName(name);
        item.setVal(val);
        this.items.add(item);
        this.items.sort(Comparator.comparing(Item::getCode));
    }

    @Data
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
         * 值
         */
        private String val;
    }

}
