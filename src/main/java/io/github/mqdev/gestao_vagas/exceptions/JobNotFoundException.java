package io.github.mqdev.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(){
        super("Vaga não encontrada");
    }    
}
