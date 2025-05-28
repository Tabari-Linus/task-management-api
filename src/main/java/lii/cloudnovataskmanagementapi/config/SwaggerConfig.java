package lii.cloudnovataskmanagementapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI taskManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cloud nova Task Management API")
                        .description("A comprehensive REST API for managing tasks ")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("CloudNova Inc.")));
    }
}
