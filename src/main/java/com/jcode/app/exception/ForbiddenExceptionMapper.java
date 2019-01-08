/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.exception;

import java.util.Date;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    /*403*/
    @Override
    public Response toResponse(ForbiddenException e) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new ErrorDetails(new Date(), "Recurso inaccesible", "Usted no tiene permisos para acceder a este recurso. " + e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
