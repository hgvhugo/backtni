package mx.bidgroup.tec.tni.nomibanco.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "NomiBanco API",
        version ="v1.0",
        description = "Documentacion NomiBanco API v1.0",
        contact = @Contact(
            name = "César Carmona",
            email = "cicinhocarmona06@gmail.com")
    )
    
)
public class OpenApiConfig {
    @Bean
	public OpenAPI customizeOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
		.addSecurityItem(new SecurityRequirement()
			.addList(securitySchemeName))
		.components(new Components()
			.addSecuritySchemes(securitySchemeName, new SecurityScheme()
			.name(securitySchemeName)
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT")));
		}
}
