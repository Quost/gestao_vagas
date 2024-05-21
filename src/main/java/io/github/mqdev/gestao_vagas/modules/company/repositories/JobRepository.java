package io.github.mqdev.gestao_vagas.modules.company.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.mqdev.gestao_vagas.modules.company.entities.JobEntity;
import java.util.List;
import java.util.UUID;


public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContaining(String description);

}
