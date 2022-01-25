package ua.com.alevel.hw_9_web_jpa.persistence.datatable;


import ua.com.alevel.hw_9_web_jpa.persistence.entity.BaseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataTableResponse<ENTITY extends BaseEntity> {

    private List<ENTITY> items;
    private long itemsSize;
    private Map<Object, Object> otherParamMap;
    private int currentPage;
    private int pageSize;
    private String sort;
    private String order;

    public DataTableResponse() {
        this.currentPage = 0;
        this.pageSize = 10;
        this.itemsSize = 0;
        this.items = Collections.emptyList();
        this.otherParamMap = Collections.emptyMap();
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getItemsSize() {
        return itemsSize;
    }

    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    public List<ENTITY> getItems() {
        return items;
    }

    public void setItems(List<ENTITY> items) {
        this.items = items;
    }

    public Map<Object, Object> getOtherParamMap() {
        return otherParamMap;
    }

    public void setOtherParamMap(Map<Object, Object> otherParamMap) {
        this.otherParamMap = otherParamMap;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}