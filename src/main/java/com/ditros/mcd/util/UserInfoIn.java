package com.ditros.mcd.util;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


public class UserInfoIn {

    public static String getUserId(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        String userIdByToken = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
            AccessToken accessToken = kp.getKeycloakSecurityContext().getToken();
            userIdByToken = accessToken.getSubject();
        }
        return userIdByToken;

    }


    public static String getUserName(String name, HttpServletRequest request, String serverUrl, String realm)  {

        KeycloakSecurityContext context ;
        Keycloak keycloak;
        Optional<UserRepresentation> userResource;
        if(request != null){
            context = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
            keycloak= KeycloakBuilder
                    .builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .authorization(context.getTokenString())
                    .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
                    .build();
            userResource = keycloak.realm(realm).users().search(name).stream().findFirst();
            if(userResource.isPresent())
                return userResource.get().getUsername();
            else
                return "";
        }
        else
            return "Yvan's Job";
    }


}
