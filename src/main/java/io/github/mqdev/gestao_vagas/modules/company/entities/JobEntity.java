package io.github.mqdev.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(description = "Título da vaga", example = "Desenvolvedor Java")
    private String title;

    @Column(length = 1000)
    @NotBlank(message = "Essa vaga requer 3 anos de experiência em Java")
    @Schema(description = "Descrição da vaga", example = "Desenvolvimento de aplicações Java")
    private String description;

    @Column(length = 1000)
    @Schema(description = "Benefícios da vaga", example = "Vale transporte, vale refeição")
    private String benefits;

    @Column(length = 1000)
    @Schema(description = "Requisitos da vaga", example = "Conhecimento em Java, Spring Boot")
    private String requirements;

    @Schema(description = "Salário da vaga", example = "R$ 5.000,00")
    private String salary;

    @Schema(description = "Localização da vaga", example = "São Paulo")
    private String location;

    @NotBlank(message = "Level should not be blank")
    @Schema(description = "Nível da vaga", example = "Sênior")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "company_id")
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
