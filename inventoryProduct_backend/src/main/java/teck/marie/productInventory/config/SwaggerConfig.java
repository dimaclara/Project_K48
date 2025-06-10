package teck.marie.productInventory.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI todoOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API for managing a product inventory")
                                .description(" Documentation API for managing product inventory with stock tracking ")
                                .version("1.0")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );


    }

}
