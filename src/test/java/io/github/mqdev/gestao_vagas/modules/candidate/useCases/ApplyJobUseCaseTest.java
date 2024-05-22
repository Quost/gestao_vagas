package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import io.github.mqdev.gestao_vagas.exceptions.CandidateNotFoundException;
import io.github.mqdev.gestao_vagas.exceptions.JobNotFoundException;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateEntity;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;
import io.github.mqdev.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobUseCaseTest {

    @InjectMocks
    private ApplyJobUseCase applyJobUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply for a job if the candidate does not exist")
    public void shouldNotBeAbleToApplyForAJobIfTheCandidateDoesNotExist() {
        try {
            applyJobUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CandidateNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for a job if the job does not exist")
    public void shouldNotBeAbleToApplyForAJobIfTheJobDoesNotExist() {
        var candidateId = java.util.UUID.randomUUID();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));

        try {
            applyJobUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

}
