package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.modules.company.entities.JobEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;
    
    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContaining(filter);
    }
}
