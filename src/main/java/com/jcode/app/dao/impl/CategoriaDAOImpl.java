/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.dao.impl;

import com.jcode.app.dao.CategoriaDAO;
import com.jcode.app.dao.SQLCloseable;
import com.jcode.app.model.Categoria;
import com.jcode.app.utilities.BeanCrud;
import com.jcode.app.utilities.BeanPagination;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author JamesCarrillo
 */
public class CategoriaDAOImpl implements CategoriaDAO {

    private static final Logger LOG = Logger.getLogger(CategoriaDAOImpl.class.getName());
    
    private final DataSource pool;
    
    public CategoriaDAOImpl(DataSource pool) {
        this.pool = pool;
    }

    @Override
    public BeanPagination getPagination(HashMap<String, Object> parameters, Connection conn) throws SQLException {
        BeanPagination beanPagination = new BeanPagination();
        List<Categoria> list = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement("SELECT COUNT(IDCATEGORIA) AS COUNT FROM CATEGORIA WHERE "
                    + "LOWER(NOMBRE) LIKE CONCAT('%',?,'%')");
            pst.setString(1, String.valueOf(parameters.get("FILTER")));
            LOG.info(pst.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                beanPagination.setCount_filter(rs.getLong("COUNT"));
                if (rs.getInt("COUNT") > 0) {
                    pst = conn.prepareStatement("SELECT * FROM CATEGORIA WHERE "
                            + "LOWER(NOMBRE) LIKE CONCAT('%',?,'%') "
                            + String.valueOf(parameters.get("SQL_ORDERS")) + " " + parameters.get("SQL_PAGINATION"));
                    pst.setString(1, String.valueOf(parameters.get("FILTER")));
                    LOG.info(pst.toString());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Categoria categoria = new Categoria();
                        categoria.setIdcategoria(rs.getInt("IDCATEGORIA"));
                        categoria.setNombre(rs.getString("NOMBRE"));
                        list.add(categoria);
                    }
                }
            }
            beanPagination.setList(list);
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return beanPagination;
    }

    @Override
    public BeanCrud getPagination(HashMap<String, Object> parameters) throws SQLException {
        BeanCrud beanCrud = new BeanCrud();
        try (Connection conn = pool.getConnection()) {
            beanCrud.setBeanPagination(getPagination(parameters, conn));
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BeanCrud add(Categoria t, HashMap<String, Object> parameters) throws SQLException {
        BeanCrud beanCrud = new BeanCrud();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT(IDCATEGORIA) AS COUNT FROM CATEGORIA WHERE NOMBRE = ?");
            pst.setString(1, t.getNombre());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareStatement("INSERT INTO CATEGORIA(NOMBRE) VALUES(?)");
                    pst.setString(1, t.getNombre());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    beanCrud.setMessageServer("ok");
                    beanCrud.setBeanPagination(getPagination(parameters, conn));
                } else {
                    beanCrud.setMessageServer("No se registró, ya existe una Categoria con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BeanCrud update(Categoria t, HashMap<String, Object> parameters) throws SQLException {
        BeanCrud beanCrud = new BeanCrud();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT(IDCATEGORIA) AS COUNT FROM CATEGORIA WHERE NOMBRE = ? AND IDCATEGORIA != ?");
            pst.setString(1, t.getNombre());
            pst.setInt(2, t.getIdcategoria());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareStatement("UPDATE CATEGORIA SET NOMBRE = ? WHERE IDCATEGORIA = ?");
                    pst.setString(1, t.getNombre());
                    pst.setInt(2, t.getIdcategoria());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    beanCrud.setMessageServer("ok");
                    beanCrud.setBeanPagination(getPagination(parameters, conn));
                } else {
                    beanCrud.setMessageServer("No se modificó, ya existe una Categoria con el nombre ingresado");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public BeanCrud delete(Long id, HashMap<String, Object> parameters) throws SQLException {
        BeanCrud beanCrud = new BeanCrud();
        PreparedStatement pst;
        ResultSet rs;
        try (Connection conn = this.pool.getConnection();
                SQLCloseable finish = conn::rollback;) {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("SELECT COUNT(IDCATEGORIA) AS COUNT FROM PRODUCTO WHERE IDCATEGORIA = ?");
            pst.setInt(1, id.intValue());
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt("COUNT") == 0) {
                    pst = conn.prepareStatement("DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?");
                    pst.setInt(1, id.intValue());
                    LOG.info(pst.toString());
                    pst.executeUpdate();
                    conn.commit();
                    beanCrud.setMessageServer("ok");
                    beanCrud.setBeanPagination(getPagination(parameters, conn));
                } else {
                    beanCrud.setMessageServer("No se eliminó, existe una Producto asociado a esta Categoria");
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return beanCrud;
    }

    @Override
    public Categoria getForId(Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
