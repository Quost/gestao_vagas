package io.github.mqdev.gestao_vagas.modules.utils;

import java.util.UUID;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;


@Service
public class TestUtils {

    @Value("${security.jwt.secret.candidate}")
    private String candidateSecretKeyInjected;

    @Value("${security.jwt.secret.company}")
    private String companySecretKeyInjected;

    @Value("${security.jwt.issuer}")
    private String issuerInjected;

    private static String candidateSecretKey;
    private static String companySecretKey;
    private static String issuer;

    @PostConstruct
    public void init() {
        candidateSecretKey = candidateSecretKeyInjected;
        companySecretKey = companySecretKeyInjected;
        issuer = issuerInjected;
    }

    public static String objectToJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID candidateId, UUID companyId) {
        
        var expiresIAt = 3600000L;

        var algorithm = Algorithm.HMAC256(candidateId != null ? candidateSecretKey : companySecretKey);

        var token = JWT.create().withIssuer(issuer)
                .withSubject(candidateId != null ? candidateId.toString() : companyId.toString())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + expiresIAt))
                .withClaim("roles", Arrays.asList(candidateId != null ? "CANDIDATE" : "COMPANY"))
                .sign(algorithm);

        return token;
    }
}
