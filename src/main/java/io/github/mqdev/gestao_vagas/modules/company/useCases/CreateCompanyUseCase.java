package io.github.mqdev.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.EntityAlreadyExistsException;
import io.github.mqdev.gestao_vagas.modules.company.entities.CompanyEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(company -> {
                throw new EntityAlreadyExistsException("Username or email already in use");
            });
        return this.companyRepository.save(companyEntity);
    }
}
