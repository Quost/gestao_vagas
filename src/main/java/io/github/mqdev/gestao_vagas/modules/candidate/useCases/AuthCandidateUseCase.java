package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;
import io.github.mqdev.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import io.github.mqdev.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthCandidateUseCase {

    @Value("${security.jwt.secret.candidate}")
    private String secretKey;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
            throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Username or password incorrect");
                });

        var passwordsMatch = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordsMatch) {
            throw new AuthenticationException("Username or password incorrect");
        }

        var roles = Arrays.asList("CANDIDATE");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiration = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
                .withIssuer(secretKey)
                .withSubject(candidate.getId().toString())
                .withClaim("roles", roles)
                .withExpiresAt(expiration)
                .sign(algorithm);

        var authCandidateResponseDTO = AuthCandidateResponseDTO
                .builder()
                .access_token(token)
                .expires_at(expiration.toEpochMilli())
                .roles(roles)
                .build();

        return authCandidateResponseDTO;

    }
}
