package com.learning.facturas.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Ricard on 08/07/2018.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //En el handler le decimos el path interno de la aplicacion
        //En el Locations le decimos las rutas reales completas
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/C:/temp/uploads/");
    }
}
