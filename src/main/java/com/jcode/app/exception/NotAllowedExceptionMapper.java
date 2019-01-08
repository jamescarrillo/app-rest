/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.exception;

import java.util.Date;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
public class NotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {

    /*405*/
    @Override
    public Response toResponse(NotAllowedException e) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED)
                .entity(new ErrorDetails(new Date(), "Método no permitido", e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

}
