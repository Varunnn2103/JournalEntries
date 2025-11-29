package com.security.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomSwagger(){
        return new OpenAPI()
                .info(
                        new Info().title("JournalEntry Web Application")
                                .description("By Varun K.")
                )
                .servers(Arrays.asList(new Server().url("http://localhost:8080").description("local")
                        ,new Server().url("http://localhost:8082").description("live")))
                .tags(Arrays.asList(
                        new Tag().name("Public API"),
                        new Tag().name("User API"),
                        new Tag().name("Admin User"),
                        new Tag().name("Journal Entry Apis")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
