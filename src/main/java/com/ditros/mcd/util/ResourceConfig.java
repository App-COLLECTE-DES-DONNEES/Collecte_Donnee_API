package com.ditros.mcd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/public/images/accident/crash/*")
                .addResourceLocations(env.getProperty("image.accident.crash.path.file"))
        ;
        registry.addResourceHandler("/public/images/accident/vehicles/*")
                .addResourceLocations(env.getProperty("image.accident.vehicle.path.file"))
        ;
        registry.addResourceHandler("/public/images/accident/persons/*")
                .addResourceLocations(env.getProperty("image.accident.person.path.file"))
        ;
        registry.addResourceHandler("/public/images/accident/drawing/*")
                .addResourceLocations(env.getProperty("image.accident.draw.path.file"))
        ;
        registry.addResourceHandler("/public/images/accident/vehicles/documents/*")
                .addResourceLocations(env.getProperty("image.accident.vehicle.document.path.file"))
        ;
        registry.addResourceHandler("/public/images/accident/persons/documents/*")
                .addResourceLocations(env.getProperty("image.accident.person.path.file"))
        ;

    }


}

