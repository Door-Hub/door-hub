package org.example.doorhub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Oauth2Config {


    @Bean
    public SecurityFilterChain securityAuth(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers("/").permitAll();
                            registry.anyRequest().authenticated();
                        }
                )
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();


    }
}
