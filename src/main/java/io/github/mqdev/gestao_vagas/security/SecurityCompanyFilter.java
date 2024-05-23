package io.github.mqdev.gestao_vagas.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.mqdev.gestao_vagas.providers.JWTCompanyProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCompanyProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {
            if (header != null) {
                System.out.println("Company token found: " + header);

                try {

                    var token = this.jwtProvider.validateToken(header);

                    if (token == null) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }

                    var roles = token.getClaim("roles").asList(String.class);

                    if (roles == null) {
                        roles = new ArrayList<>();
                    }

                    var authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();

                    request.setAttribute("company_id", token.getSubject());

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            token.getSubject(),
                            null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }

        }

        filterChain.doFilter(request, response);
    }

}
