package io.github.mqdev.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\S+", message = "Username should not have spaces")
    @NotBlank(message = "Username should not be blank")
    private String username;

    @Length(min = 6,max=100, message = "Password should have at least 6 characters")
    private String password;
    
    private String description;
    private String website;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
