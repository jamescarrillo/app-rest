/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    @Override
    public Response toResponse(JsonProcessingException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorDetails(LocalDateTime.now(), "Solicitud Incorrecta, ultimito xs", "El Recurso solicitado no cuenta con los parámetros correctos. " + e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
