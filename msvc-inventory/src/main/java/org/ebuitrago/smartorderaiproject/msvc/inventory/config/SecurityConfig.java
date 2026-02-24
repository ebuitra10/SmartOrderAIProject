package org.ebuitrago.smartorderaiproject.msvc.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
 * Configuración de seguridad basada en Spring Security para aplicaciones
 * con arquitectura Servlet (MVC).
 * Define autenticación mediante JWT como Resource Server OAuth2,
 * política stateless, deshabilita CSRF y configura un convertidor
 * personalizado para mapear los roles del token a autoridades
 * reconocidas por Spring Security.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configura la cadena principal de filtros de seguridad.
     * - Deshabilita CSRF.
     * - Requiere autenticación para los endpoints /inventory/**.
     * - Establece política de sesión STATELESS.
     * - Configura autenticación basada en JWT.
     *
     * @param http configuración de seguridad HTTP
     * @return SecurityFilterChain configurado
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/inventory/**").authenticated()
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