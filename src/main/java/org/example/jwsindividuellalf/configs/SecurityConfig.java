package org.example.jwsindividuellalf.configs;

import org.example.jwsindividuellalf.converters.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

@Autowired
public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/api/v2/posts","/api/v2/post/**")
                                        .authenticated()
                                .requestMatchers("/api/v2/newpost","/api/v2/updatepost")
                                        .hasRole("Blog_USER")
                                .requestMatchers("/api/v2/deletepost/**")
                                        .hasAnyRole("Blog_ADMIN","Blog_USER")
                                .requestMatchers("/api/v2/count")
                                        .hasRole("Blog_ADMIN")
                                .anyRequest().denyAll()
                )
                .oauth2ResourceServer(oauth2->
                        oauth2
                                .jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }
}
