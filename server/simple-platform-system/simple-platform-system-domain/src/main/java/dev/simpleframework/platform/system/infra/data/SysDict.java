package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends BaseDataModel<SysDict> {

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
     * 外部信息
     */
    private Map<String, Item> items;

    public void addItem(String code, String name, String val) {
        if (this.items == null) {
            this.items = new LinkedHashMap<>();
        }
        Item item = new Item();
        item.setName(name);
        item.setVal(val);
        this.items.put(code, item);
    }

    @Data
    public static class Item {
        private String name;
        private String val;
    }

}
