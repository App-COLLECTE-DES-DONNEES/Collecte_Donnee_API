package com.ditros.mcd.security;

import com.ditros.mcd.util.UserInfoIn;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@NoArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Environment environment;

    @Override
    public Optional<String> getCurrentAuditor() {

        String keycloakId = UserInfoIn.getUserId(request);

        return Optional.ofNullable(keycloakId);
    }

}
