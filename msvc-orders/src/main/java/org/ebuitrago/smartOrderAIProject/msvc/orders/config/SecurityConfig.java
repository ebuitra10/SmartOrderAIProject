package org.ebuitrago.smartOrderAIProject.msvc.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Configuración de seguridad para el microservicio de órdenes.
 * Define autenticación basada en JWT como Resource Server OAuth2,
 * aplica control de acceso por rol según el método HTTP y
 * establece una política de sesión stateless.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     * Reglas aplicadas:
     * - GET /orders/** → accesible para roles USER y ADMIN
     * - POST /orders/** → solo ADMIN
     * - PUT /orders/** → solo ADMIN
     * - DELETE /orders/** → solo ADMIN
     * - Cualquier otra petición requiere autenticación
     * Además:
     * - Deshabilita CSRF
     * - Configura política STATELESS
     * - Habilita autenticación mediante JWT
     *
     * @param http configuración HTTP de Spring Security
     * @return SecurityFilterChain configurado
     * @throws Exception si ocurre un error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(HttpMethod.GET, "/orders/**", "/orders")
                                        .hasAnyRole("USER", "ADMIN")
                                        .requestMatchers(HttpMethod.POST, "/orders/**", "/orders")
                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT, "/orders/**", "/orders")
                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/orders/**", "/orders")
                                        .hasRole("ADMIN")
                                        .anyRequest().authenticated())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth ->
                        oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

        return http.build();
    }

    /**
     * Convertidor personalizado que extrae los roles del claim
     * "realm_access" del JWT y los transforma en autoridades
     * con el prefijo "ROLE_" requerido por Spring Security.
     *
     * @return JwtAuthenticationConverter configurado
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");

            if (realmAccess == null) {
                return Collections.emptyList();
            }

            Collection<String> roles = (Collection<String>) realmAccess.get("roles");

            return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .collect(Collectors.toList());
        });

        return converter;
    }
}