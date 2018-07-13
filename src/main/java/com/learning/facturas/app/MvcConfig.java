package com.learning.facturas.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * Created by Ricard on 08/07/2018.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //En el handler le decimos el path interno de la aplicacion
        //En el Locations le decimos las rutas reales completas
        //registry.addResourceHandler("/uploads/**")
        //        .addResourceLocations("file:/C:/temp/uploads/");

        //Resource Handler apuntando a una carpeta en la raiz del proyecto
        String uploadsPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadsPath);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error_403").setViewName("error_403");
    }
}
