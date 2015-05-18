package br.grupointegrado.geladaonline.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jettison.JettisonFeature;

/**
 * Classe responsável por configurar o serviço REST através do JAX-RS (Jersey)
 */
@javax.ws.rs.ApplicationPath("/*")
public class ApplicationConfig extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "br.grupointegrado.geladaonline.services");

        return properties;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new JettisonFeature());

        return singletons;
    }

}
