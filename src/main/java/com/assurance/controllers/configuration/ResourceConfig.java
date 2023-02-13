package com.assurance.controllers.configuration;


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
        registry.addResourceHandler("/pdf/*")
                .addResourceLocations(env.getProperty("assurance.resource.path"))
        ;
    }


}


