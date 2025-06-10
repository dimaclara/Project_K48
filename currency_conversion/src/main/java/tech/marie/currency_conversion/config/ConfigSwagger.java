package tech.marie.currency_conversion.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API de Conversion de Devises")
                        .description("API RESTful permettant de convertir des montants entre différentes devises en utilisant des taux de change en temps réel")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Développeur")
                                .email("contact@example.com")));
    }
}
