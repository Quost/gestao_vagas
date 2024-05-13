package io.github.mqdev.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.EntityAlreadyExistsException;
import io.github.mqdev.gestao_vagas.modules.company.entities.CompanyEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(company -> {
                throw new EntityAlreadyExistsException("Username or email already in use");
            });

        companyEntity.setPassword(this.passwordEncoder.encode(companyEntity.getPassword()));
        return this.companyRepository.save(companyEntity);
    }
}
