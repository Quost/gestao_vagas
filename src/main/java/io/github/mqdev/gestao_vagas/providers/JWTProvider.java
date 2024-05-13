package io.github.mqdev.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTProvider {

    @Value("${security.jwt.secret.company}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
}
