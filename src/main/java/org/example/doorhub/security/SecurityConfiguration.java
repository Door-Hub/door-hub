package org.example.doorhub.security;

import lombok.RequiredArgsConstructor;
import org.example.doorhub.jwt.JwtSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    final String MATCHERS =
            """
                    /swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/** ,
                    "/oauth2/**",
                    "/login/**",
                                        
                    "/auth/**",
                    "/notification/**",   
                    "/category/**",
                    "/book/**",
                    "/discount/**",
                    "/review/**"
                    "/api/files/**"
                                
                            """;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtSecurityFilter filter) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers(MATCHERS)
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
