/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.app.conf;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author JamesCarrillo
 */
@ApplicationPath("/api")
public class AppConfiguration extends ResourceConfig {

    public AppConfiguration() {
        packages("com.jcode.app");
        register(JacksonFeature.class);
        //register(JacksonJaxbJsonProvider.class);
        register(RolesAllowedDynamicFeature.class);
    }

}
