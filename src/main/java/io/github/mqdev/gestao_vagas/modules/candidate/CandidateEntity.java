package io.github.mqdev.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Schema(description = "Nome do candidato", example = "João da Silva", required = true)
    private String name;

    @Email(message = "Email should be valid")
    @Schema(description = "Email do candidato", example = "example@email.com", required = true)
    private String email;

    @Pattern(regexp = "\\S+", message = "Username should not have spaces")
    @NotBlank(message = "Username should not be blank")
    @Schema(description = "Username do candidato", example = "joaosilva", required = true)
    private String username;

    @Length(min = 6,max=100, message = "Password should have at least 6 characters")
    @Schema(description = "Senha do candidato", example = "js123456", minLength = 6, maxLength = 100, required = true)
    private String password;

    @Schema(description = "Breve descrição do candidato", example = "Desenvolvedor Java", required = true)
    private String description;

    @Column(name = "cv")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
