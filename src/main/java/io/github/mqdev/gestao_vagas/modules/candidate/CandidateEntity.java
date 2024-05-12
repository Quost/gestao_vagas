package io.github.mqdev.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\S+", message = "Username should not have spaces")
    private String username;

    @Length(min = 6,max=100, message = "Password should have at least 6 characters")
    private String password;
    private String description;
    private String curriculum;
}
