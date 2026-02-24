package org.ebuitrago.smartOrderAIProject.msvc_gateway.auth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Convertidor personalizado de JWT que extrae los roles definidos
 * en el claim "realm_access" y los transforma en autoridades
 * compatibles con Spring Security.
 * Permite mapear los roles del token (por ejemplo, provenientes de Keycloak)
 * a instancias de {@link GrantedAuthority} con el prefijo "ROLE_".
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * Convierte un JWT en una colección de autoridades reconocidas
     * por Spring Security.
     *
     * @param jwt token JWT autenticado
     * @return colección de {@link GrantedAuthority} basada en los roles
     *         contenidos en el claim "realm_access"
     */
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess == null || realmAccess.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<String> roles = (Collection<String>) realmAccess.get("roles");

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }
}
