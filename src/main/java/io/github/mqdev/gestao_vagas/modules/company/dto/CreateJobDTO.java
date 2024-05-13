package io.github.mqdev.gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String title;
    private String description;
    private String benefits;
    private String requirements;
    private String salary;
    private String location;
    private String level;
}
