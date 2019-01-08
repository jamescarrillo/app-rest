/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.utilities;

import java.util.List;

/**
 *
 * @author JamesCarrillo
 */
public class RequestPagination {

    private List<MapField> filters;
    private Integer page;
    private Integer size;

    public List<MapField> getFilters() {
        return filters;
    }

    public void setFilters(List<MapField> filters) {
        this.filters = filters;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    
}
