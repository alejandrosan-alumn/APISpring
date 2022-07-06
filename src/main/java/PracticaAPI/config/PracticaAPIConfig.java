package PracticaAPI.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PracticaAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("PracticaAPI API")
                        .description("Ejemplo de API REST")
                        .contact(new Contact()
                                .name("Alejandro Sánchez de la Fuente")
                                .email("alejandro.sanchez.we@gmail.com")
                                .url("https://datos.codeandcoke.com"))
                        .version("1.0"));
    }
}
