package org.ebuitrago.smartOrderAIProject.msvc.orders.config;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


/**
 * Configuración personalizada para clientes Feign.
 *
 * Define un interceptor que propaga automáticamente el token JWT
 * del usuario autenticado en la cabecera Authorization de las
 * solicitudes salientes hacia otros microservicios.
 */
@Configuration
public class FeignConfig {

    /**
     * Interceptor que añade el header Authorization con el token Bearer
     * obtenido del contexto de seguridad actual.
     *
     * Permite mantener la autenticación entre microservicios cuando
     * se utiliza Spring Security con JWT.
     *
     * @return RequestInterceptor configurado
     */
    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwt) {

                String token = jwt.getToken().getTokenValue();

                requestTemplate.header("Authorization", "Bearer " + token);
            }

        };
    }

}
