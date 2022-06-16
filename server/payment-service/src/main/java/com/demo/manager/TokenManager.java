package com.demo.manager;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenManager {

    private static final Logger logger = LogManager.getLogger(TokenManager.class);

    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        try {
            var jwt = JWTParser.parse(token);
            boolean isTokenExpired = jwt.getJWTClaimsSet().getExpirationTime().before(new Date());
            String jwtUsername = jwt.getJWTClaimsSet().getStringClaim("user");
            return jwt instanceof SignedJWT && !isTokenExpired && jwtUsername.equals(userDetails.getUsername());
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public Optional<String> getUsernameFromToken(String token) {
        Optional<String> username = Optional.empty();
        try {
            var jwt = JWTParser.parse(token);
            String user = jwt.getJWTClaimsSet().getStringClaim("user");
            username = Optional.ofNullable(user);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return username;
    }
}
