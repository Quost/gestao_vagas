package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.CandidateNotFoundException;
import io.github.mqdev.gestao_vagas.exceptions.JobNotFoundException;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;
import io.github.mqdev.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException());

        this.jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException());
    }
}
