package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mqdev.gestao_vagas.exceptions.CandidateNotFoundException;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;
import io.github.mqdev.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new CandidateNotFoundException();
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();

        return candidateDTO;
    }
}
