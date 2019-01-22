/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.security.filter;

import com.jcode.app.exception.ErrorDetails;
import com.jcode.app.security.annotation.Secured;
import com.jcode.app.model.Usuario;
import com.jcode.app.security.MyApplicationSecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JamesCarrillo
 */
@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class RestSecurityFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(RestSecurityFilter.class.getName());
    public static final Key KEY = MacProvider.generateKey();

    public RestSecurityFilter() {
    }

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        LOG.info("VA IMPRIMIR URI INFO XD");
        LOG.info(crc.getUriInfo().getPath());
        LOG.info(crc.getUriInfo().getBaseUri().getHost());
        try {
            LOG.info("Inicializando Filter");
            String authorizationHeader = crc.getHeaderString(HttpHeaders.AUTHORIZATION);
            //LOG.info(authorizationHeader);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring("Bearer".length()).trim();
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
               
                Usuario usuario = new Usuario();
                usuario.setNombre(claims.getBody().getSubject());
                String roles = (String) claims.getBody().get("roles");
                usuario.setRoles(Arrays.asList(roles.split(",")));
                
                MyApplicationSecurityContext secContext = new MyApplicationSecurityContext(usuario, crc.getSecurityContext().isSecure());
                crc.setSecurityContext(secContext);

                //List<String> menus = (java.util.ArrayList) claims.getBody().get("menus");
                //LOG.info(String.valueOf(menus.size()));
            } else {
                throw new NotAuthorizedException("Bearer");
            }
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            LOG.info("Ingresó exception token");
            //crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorDetails(LocalDateTime.now(), "ERROR ", "DASDASHK")).build());
            throw new NotAuthorizedException("Bearer");
        }
    }

}
