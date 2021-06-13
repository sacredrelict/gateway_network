package com.omikheev.testapp.gateways.network.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger configuration class
 *
 * @author Oleg Mikheev
 * @since 13.06.2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Bean for init Swagger Docket object.
     *
     * @return Docket object.
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.omikheev.testapp.gateways.network.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    /**
     * Store Swagger description.
     *
     * @return info for Swagger.
     */
    private ApiInfo metaData() {
        return new ApiInfo(
                "Gateways Network REST API",
                "Gateways Network REST API online documentation",
                "1.0",
                "Terms of service",
                new Contact("omikheev", "http://github.com/", "http://github.com/"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
    }
}
