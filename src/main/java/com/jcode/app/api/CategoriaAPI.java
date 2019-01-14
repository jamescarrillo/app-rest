/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.api;

import com.jcode.app.dao.CategoriaDAO;
import com.jcode.app.dao.impl.CategoriaDAOImpl;
import com.jcode.app.model.Categoria;
import com.jcode.app.security.annotation.Secured;
import com.jcode.app.utilities.Utilities;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JamesCarrillo
 */
@Singleton
@Path("/categorias")
@Secured
public class CategoriaAPI {

    private static final Logger LOG = Logger.getLogger(CategoriaAPI.class.getName());

    private DataSource pool;

    private CategoriaDAO categoriaDAO;
    //@Context
    //private HttpServletRequest sr;

    public CategoriaAPI() {
        try {
            InitialContext cxt = new InitialContext();
            this.pool = (DataSource) cxt.lookup("java:/comp/env/jdbc/app-rest");
            if (pool != null) {
                LOG.info("DataSource Inicializado exitosamente");
            } else {
                LOG.info("Error al Inicializar DataSource");
            }
            this.categoriaDAO = new CategoriaDAOImpl(this.pool);
        } catch (NamingException ex) {
            Logger.getLogger(CategoriaAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Path("/paginate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response paginate(
            @QueryParam("nombre") String nombre,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size) throws Exception {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("FILTER", nombre);
        parameters.put("SQL_ORDERS", " ORDER BY NOMBRE ASC ");
        parameters.put("SQL_PAGINATION", " LIMIT " + size + " OFFSET " + (page - 1) * size);
        LOG.info(parameters.toString());
        //LOG.info(sr.getRemoteAddr());
        return Response.status(Response.Status.OK)
                .entity(this.categoriaDAO.getPagination(parameters))
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Categoria categoria) throws SQLException {
        LOG.info(categoria.toString());
        return Response.status(Response.Status.OK).entity(this.categoriaDAO.add(categoria, Utilities.getParametersDefaultBasic())).build();
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Categoria categoria) throws SQLException {
        LOG.info(categoria.toString());
        return Response.status(Response.Status.OK).entity(this.categoriaDAO.update(categoria, Utilities.getParametersDefaultBasic())).build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) throws SQLException {
        LOG.info(id.toString());
        return Response.status(Response.Status.OK).entity(this.categoriaDAO.delete(id, Utilities.getParametersDefaultBasic())).build();
    }

}
