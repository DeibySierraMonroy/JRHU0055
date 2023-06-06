package co.com.activos.jrhu0055.config;

import co.com.activos.jrhu0055.utiliti.CorsFilter;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("/api/incapacidades")
public class ApplicationConfig extends Application{
    
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.com.activos.jrhu0055.controller.IncapacidadController.class);
        resources.add(co.com.activos.jrhu0055.utiliti.CorsFilter.class);
    }

}
