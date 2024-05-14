package io.github.mqdev.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.github.mqdev.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.jwt.secret.company}")
    private String secretKey;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO authCompany(String username, String password) throws AuthenticationException {
        var company = companyRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Company not found"));

        var passwordsMatch = passwordEncoder.matches(password, company.getPassword());
        if (!passwordsMatch) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer(issuer)
                .withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var expiration = Instant.now().plus(Duration.ofHours(2)).toEpochMilli();

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_at(expiration)
                .build();

        return authCompanyResponseDTO;
    }
}
