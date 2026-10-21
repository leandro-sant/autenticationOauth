package com.fiap.autenticationOauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/private")
    public String privateRoute(@AuthenticationPrincipal OidcUser principal) {
        return String.format(
                "<h1>Rota privada</h1>" +
                        "<h3> Principal: %s</h3>" +
                        "<h3> e:mail: %s</h3>" +
                        "<h3> Authorits: %s</h3>" +
                        "<h3> JWT: %s</h3>",
                principal,
                principal.getAttribute("email"),
                principal.getAuthorities(),
                principal.getIdToken().getTokenValue()
        );
    }

    @GetMapping("/public")
    public String userPage() {
        return "<h1>USER, você está acessando a área geral do app!</h1>";
    }

    @GetMapping("/jwt")
    public String privateJwt(@AuthenticationPrincipal Jwt jwt) {
        return
                """
                        <h1>Rota privada</h1>
                        <h3> Principal: %s</h3>
                        <h3> e:mail: %s</h3>
                        <h3> JWT: %s</h3>
                        """.formatted(
                        jwt.getClaims(),
                        jwt.getClaim("email"),
                        jwt.getTokenValue()
                );

    }


}
