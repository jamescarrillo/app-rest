package com.jcode.app.utilities;

import java.util.List;

public class BeanPagination {

    private Long count_filter;
    private Integer count_pages;
    private List<?> list;

    public Long getCount_filter() {
        return count_filter;
    }

    public void setCount_filter(Long count_filter) {
        this.count_filter = count_filter;
    }

    public Integer getCount_pages() {
        return count_pages;
    }

    public void setCount_pages(Integer count_pages) {
        this.count_pages = count_pages;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BeanPagination [count_filter=" + count_filter + ", count_pages=" + count_pages + ", list=" + list + "]";
    }

}
