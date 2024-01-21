package org.example.doorhub.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring 6 Swagger 2 Annotation Example")
                        .description("Spring 6 Swagger Simple Application")
                        .version("${api.version}")
                        .contact(new Contact()
                                .name("DOOR HUB DEVELOPERS")
                                .email("rikxsiboyevzaxa@gmail.com")
                                .email("diyorbek2110@gmail.com")
                                .email("mamadiyevsayyod5@gmail.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://github.com/Door-Hub/door-hub"))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080/")
                                .description("Production")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth", "google        Auth").addList("githubAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSecuritySchemes("googleAuth", new SecurityScheme()
                                .name("googleAuth")
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .tokenUrl("https://accounts.google.com/o/oauth2/token")
                                                .authorizationUrl("https://accounts.google.com/o/oauth2/auth")
                                                .scopes(new Scopes().addString("openid", "email"))
                                        )
                                )

                        )
                        .addSecuritySchemes("githubAuth", new SecurityScheme()
                                .name("githubAuth")
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .tokenUrl("https://github.com/login/oauth/access_token")
                                                .authorizationUrl("https://github.com/login/oauth/authorize")
                                                .scopes(new Scopes().addString("read:user", "user:email"))
                                        )
                                )
                        )
                );
    }

}
