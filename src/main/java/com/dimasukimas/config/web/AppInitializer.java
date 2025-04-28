package com.dimasukimas.config.web;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter(
                "hiddenHttpMethodFilter", new HiddenHttpMethodFilter());

        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
    }

}
