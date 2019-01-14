/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.api;

import com.jcode.app.security.filter.RestSecurityFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JamesCarrillo
 */
@Path("/usuarios")
public class UsuarioAPI {

    private static final Logger LOG = Logger.getLogger(UsuarioAPI.class.getName());

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("login") String login, @FormParam("password") String password) {
        List<String> menus = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            menus.add("menu " + i);
        }
        String token = issueToken(login, "ADMIN", menus);
        HashMap<String, Object> JSON = new HashMap<>();
        JSON.put("token", token);
        JSON.put("usuario", "James Carrillo");
        JSON.put("items", "producto,categorias,marcas");
        JSON.put("roles", "admin");
        JSON.put("menus", menus);
        return Response.status(Response.Status.OK).entity(JSON).build();
    }

    private String issueToken(String login, String roles, List<String> menus) {
        String jwtToken = Jwts.builder()
                .claim("roles", roles)
                .claim("menus", menus)
                .setSubject(login)
                .setIssuer("http://www.infointernet.es")
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, RestSecurityFilter.KEY)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
