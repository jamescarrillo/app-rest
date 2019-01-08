/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.exception;

import java.util.Date;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    /*400*/
    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDetails(new Date(), "Solicitud Incorrecta", "El Recurso solicitado no cuenta con los parámetros correctos. " + e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
