package io.github.mqdev.gestao_vagas.modules.candidate.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCandidateResponseDTO {
    private String access_token;
    private Long expires_at;
    private List<String> roles;
}
