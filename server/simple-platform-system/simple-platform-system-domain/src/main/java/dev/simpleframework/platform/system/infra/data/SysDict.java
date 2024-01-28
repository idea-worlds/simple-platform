package dev.simpleframework.platform.system.infra.data;

import dev.simpleframework.platform.commons.BaseDataModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    /**
     * 序号（越大越前）
     */
    private Integer sortNo;

    public void addItem(String code, String name, Integer sortNo) {
        if (this.items == null) {
            this.items = new LinkedHashMap<>();
        }
        this.items.put(code, new Item(name, sortNo));
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String name;
        private Integer sortNo;

        public Item(String name, Integer sortNo) {
            this.name = name;
            this.sortNo = sortNo;
        }
    }

}
