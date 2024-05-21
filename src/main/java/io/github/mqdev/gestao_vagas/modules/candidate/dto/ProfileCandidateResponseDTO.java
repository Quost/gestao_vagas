package io.github.mqdev.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    private UUID id;
    @Schema(description = "Nome do candidato", example = "João da Silva")
    private String name;
    @Schema(description = "Email do candidato", example = "example@email.com")
    private String email;
    @Schema(description = "Username do candidato", example = "joaosilva")
    private String username;
    @Schema(description = "Descrição do candidato", example = "Desenvolvedor Java")
    private String description;
}
