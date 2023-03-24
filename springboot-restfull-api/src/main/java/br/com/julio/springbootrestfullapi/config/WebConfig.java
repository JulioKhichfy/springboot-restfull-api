package br.com.julio.springbootrestfullapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){

        //VIA EXTENSION: localhost>8080/api/person/v1.xml DEPRECATED

        // VIA QUERY PARAM: localhost>8080/api/person/v1?mediaType=xml
        /*configurer.favorParameter(true) // aceita parametros
                .parameterName("mediaType").ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("XML", MediaType.APPLICATION_XML);*/

        // VIA HEADER: localhost>8080/api/person/v1
        configurer.favorParameter(false) // nao aceita parametros
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("XML", MediaType.APPLICATION_XML);

    }
}
