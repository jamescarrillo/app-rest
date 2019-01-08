/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.exception;

import java.util.Date;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
public class InternalServerErrorExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    /*500*/
    @Override
    public Response toResponse(Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDetails(new Date(), "Error Interno en el servidor", e.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

}
