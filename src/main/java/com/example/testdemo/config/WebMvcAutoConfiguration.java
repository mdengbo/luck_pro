/*
package com.example.testdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
public class WebMvcAutoConfiguration {

    @Bean
    public SecurityProperties.User user() {
        return new SecurityProperties.User();
    }

    @Bean
    public LocaleResolver localeResolver() {
        System.out.println("bean localeResolver");
       return new AcceptHeaderLocaleResolver();
    }

}
*/
