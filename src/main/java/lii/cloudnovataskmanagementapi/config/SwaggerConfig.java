package lii.cloudnovataskmanagementapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI taskManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cloud nova Task Management API")
                        .description("It uses the Cloud nova Task Management API. A java springboot Rest application that serves endpoints for implementing task CRUD and statistics")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("CloudNova Inc.")));
    }
}
