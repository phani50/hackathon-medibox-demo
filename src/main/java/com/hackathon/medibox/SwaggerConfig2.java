package com.hackathon.medibox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig2 {

    public static final String MEDIBOX_TAG = "Medibox Apis";
    public static final String EMAIL_TAG = "Send Email Notifications";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("Medi Box Service")
                        .description("Apis provided to medibox application")
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build()
                .tags(new Tag(MEDIBOX_TAG, ""), new Tag(EMAIL_TAG, ""));
    }
}
