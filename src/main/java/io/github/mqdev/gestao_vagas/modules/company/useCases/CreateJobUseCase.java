package io.github.mqdev.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.CompanyNotFoundException;
import io.github.mqdev.gestao_vagas.modules.company.entities.JobEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import io.github.mqdev.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository
                .findById(jobEntity
                .getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException());
        return this.jobRepository.save(jobEntity);
    }

}
