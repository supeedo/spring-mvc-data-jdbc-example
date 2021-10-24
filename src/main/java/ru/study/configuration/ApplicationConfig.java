package ru.study.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan("ru.study")
public class ApplicationConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public ApplicationConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


}
