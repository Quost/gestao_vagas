package io.github.mqdev.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void authCompany(String username, String password) throws AuthenticationException {
        var company = companyRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Company not found"));
        
        if (!passwordEncoder.matches(password, company.getPassword())) {
            throw new AuthenticationException();
        }
    }
}
