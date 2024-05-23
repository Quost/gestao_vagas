package io.github.mqdev.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
    @Schema(description = "Título da vaga", example = "Desenvolvedor Java", required = true)
    private String title;
    @Schema(description = "Descrição da vaga", example = "Desenvolvimento de aplicações Java")
    private String description;
    @Schema(description = "Benefícios da vaga", example = "Vale transporte, vale refeição")
    private String benefits;
    @Schema(description = "Requisitos da vaga", example = "Conhecimento em Java, Spring Boot")
    private String requirements;
    @Schema(description = "Salário da vaga", example = "R$ 5.000,00")
    private String salary;
    @Schema(description = "Localização da vaga", example = "São Paulo")
    private String location;
    @Schema(description = "Nível da vaga", example = "Júnior")
    private String level;
}
