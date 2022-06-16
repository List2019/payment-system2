package com.demo.service;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class AuthService {

    private final DiscoveryClient discoveryClient;
    @Value("${auth.service.name}")
    private String serviceName;
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public void getJwtToken() {
        getServiceUrl().ifPresent(uri -> {
            HashMap<String, String> creds = new HashMap<>();
            creds.put("username", "admin");
            creds.put("password", "password");
            HttpEntity<Map<String, String>> request = new HttpEntity<>(creds);
            ResponseEntity<String> response = restTemplate.postForEntity(uri + "/login", request, String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            String access_token = jsonObject.getString("access_token");
            try {
                JWT parse = JWTParser.parse(access_token);
                if (parse instanceof SignedJWT) {
                    System.out.println(((SignedJWT) parse).getPayload().toString());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private Optional<URI> getServiceUrl() {
        return discoveryClient.getInstances(serviceName).stream().findFirst().map(ServiceInstance::getUri);
    }


}
