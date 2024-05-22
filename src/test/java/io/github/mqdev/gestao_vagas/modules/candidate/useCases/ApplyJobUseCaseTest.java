package io.github.mqdev.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import io.github.mqdev.gestao_vagas.exceptions.CandidateNotFoundException;
import io.github.mqdev.gestao_vagas.exceptions.JobNotFoundException;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateEntity;
import io.github.mqdev.gestao_vagas.modules.candidate.CandidateRepository;
import io.github.mqdev.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import io.github.mqdev.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import io.github.mqdev.gestao_vagas.modules.company.entities.JobEntity;
import io.github.mqdev.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobUseCaseTest {

    @InjectMocks
    private ApplyJobUseCase applyJobUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

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

    @Test
    @DisplayName("Should be able to apply for a job")
    public void shouldBeAbleToApplyForAJob() {
        var candidateId = java.util.UUID.randomUUID();
        var jobId = java.util.UUID.randomUUID();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        var applyJobEntity = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .id(UUID.randomUUID())
                .build();

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobEntity);

        var result = applyJobUseCase.execute(candidateId, jobId);

        assertThat(result)
                .isNotNull()
                .hasFieldOrPropertyWithValue("candidateId", candidateId)
                .hasFieldOrPropertyWithValue("jobId", jobId)
                .hasFieldOrProperty("id");

        assertNotNull(result.getId());
    }

}
