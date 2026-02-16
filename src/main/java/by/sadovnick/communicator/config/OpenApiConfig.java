package by.sadovnick.communicator.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API обработки данных в БД",
                description = "Спокойно делай свое дело",
                version = "1.0.0",
                contact = @Contact(
                        name = "Dark team",
                        email = "dark@mail.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8085",
                        description = "local"
                )
        }
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi coreOpenApi() {
        return GroupedOpenApi
                .builder()
                .group("Dark-API")
                .pathsToMatch("/**")
                .build();
    }
}
