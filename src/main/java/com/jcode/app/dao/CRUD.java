/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.dao;

import com.jcode.app.utilities.BeanCrud;
import com.jcode.app.utilities.BeanPagination;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author JamesCarrillo
 * @param <T>
 */
public interface CRUD<T> {

    public BeanPagination getPagination(HashMap<String, Object> parameters, Connection conn) throws SQLException;

    public BeanCrud getPagination(HashMap<String, Object> parameters) throws SQLException;

    public BeanCrud add(T t, HashMap<String, Object> parameters) throws SQLException;

    public BeanCrud update(T t, HashMap<String, Object> parameters) throws SQLException;

    public BeanCrud delete(Long id, HashMap<String, Object> parameters) throws SQLException;

    public T getForId(Long id) throws SQLException;
}
