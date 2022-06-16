package com.auth;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static io.micronaut.http.HttpStatus.OK;
import static io.micronaut.http.HttpStatus.UNAUTHORIZED;
import static io.micronaut.http.MediaType.TEXT_PLAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
class AuthServiceTest {

    @Inject
    @Client("/")
    HttpClient client;
    @Inject
    AppClient appClient;

    @Test
    void accessingASecuredUrlWithoutAuthenticatingReturnsUnauthorized() {
        HttpClientResponseException e = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/").accept(TEXT_PLAIN));
        });

        assertEquals(UNAUTHORIZED, e.getStatus());
    }

    @Test
    void uponSuccessfulAuthenticationAJsonWebTokenIsIssuedToTheUser() throws ParseException {
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "password");
        HttpRequest<?> request = HttpRequest.POST("/login", creds);
        HttpResponse<BearerAccessRefreshToken> rsp = client.toBlocking().exchange(request, BearerAccessRefreshToken.class);
        assertEquals(OK, rsp.getStatus());

        BearerAccessRefreshToken bearerAccessRefreshToken = rsp.body();
        assertEquals("admin", bearerAccessRefreshToken.getUsername());
        assertNotNull(bearerAccessRefreshToken.getAccessToken());
        assertTrue(JWTParser.parse(bearerAccessRefreshToken.getAccessToken()) instanceof SignedJWT);

        String accessToken = bearerAccessRefreshToken.getAccessToken();
        HttpRequest<?> requestWithAuthorization = HttpRequest.GET("/")
                .accept(TEXT_PLAIN)
                .bearerAuth(accessToken);
        HttpResponse<String> response = client.toBlocking().exchange(requestWithAuthorization, String.class);

        assertEquals(OK, rsp.getStatus());
        assertEquals("admin", response.body());
    }

    @Test
    void verifyJwtAuthenticationWorksWithDeclarativeClient() throws ParseException {
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "password");
        BearerAccessRefreshToken loginRsp = appClient.login(creds);

        assertNotNull(loginRsp);
        assertNotNull(loginRsp.getAccessToken());
        assertTrue(JWTParser.parse(loginRsp.getAccessToken()) instanceof SignedJWT);

        String msg = appClient.home("Bearer " + loginRsp.getAccessToken());
        assertEquals("admin", msg);
    }

}