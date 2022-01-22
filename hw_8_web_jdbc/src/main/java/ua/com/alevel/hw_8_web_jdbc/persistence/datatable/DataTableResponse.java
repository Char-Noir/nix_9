package ua.com.alevel.hw_8_web_jdbc.persistence.datatable;

import ua.com.alevel.logic.entity.BaseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataTableResponse<ENTITY extends BaseEntity> {

    private List<ENTITY> items;
    private long itemsSize;
    private Map<Object, Object> otherParamMap;

    public DataTableResponse() {
        items = Collections.emptyList();
        otherParamMap = Collections.emptyMap();
        itemsSize = 0;
    }

    public List<ENTITY> getItems() {
        return items;
    }

    public void setItems(List<ENTITY> items) {
        this.items = items;
    }

    public long getItemsSize() {
        return itemsSize;
    }

    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    public Map<Object, Object> getOtherParamMap() {
        return otherParamMap;
    }

    public void setOtherParamMap(Map<Object, Object> otherParamMap) {
        this.otherParamMap = otherParamMap;
    }
}