package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.UserAlreadyExistsException;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateEntity;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
            .ifPresent(candidate -> {
                throw new UserAlreadyExistsException("Username or email already in use");
            });
        return this.candidateRepository.save(candidateEntity);
    }
}