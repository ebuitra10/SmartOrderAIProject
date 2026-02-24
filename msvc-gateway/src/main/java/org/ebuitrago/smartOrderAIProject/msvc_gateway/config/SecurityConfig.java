package org.ebuitrago.smartOrderAIProject.msvc_gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.ebuitrago.smartOrderAIProject.msvc_gateway.auth.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Configuración de seguridad para la aplicación basada en Spring Security WebFlux.
 * Define la protección de los endpoints mediante autenticación JWT,
 * deshabilita CSRF y configura un convertidor personalizado para
 * mapear los roles del token a autoridades de Spring Security.
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    /**
     * Constructor que inyecta el convertidor personalizado de JWT.
     *
     * @param jwtAuthConverter convertidor que transforma los roles del token en autoridades
     */
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    /**
     * Configura la cadena de filtros de seguridad reactiva.
     * - Deshabilita CSRF.
     * - Permite acceso público a los endpoints de actuator.
     * - Requiere autenticación para cualquier otra petición.
     * - Configura el servidor de recursos OAuth2 basado en JWT.
     *
     * @param http configuración reactiva de seguridad
     * @return SecurityWebFilterChain configurado
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor()))
                )
                .build();
    }

    /**
     * Adaptador reactivo que integra el convertidor personalizado de roles
     * dentro del flujo de autenticación JWT.
     *
     * @return convertidor reactivo de JWT a AbstractAuthenticationToken
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtAuthConverter);

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}