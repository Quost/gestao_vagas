package io.github.mqdev.gestao_vagas.modules.candidate;

import java.util.UUID;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String description;
    private String curriculum;
}
