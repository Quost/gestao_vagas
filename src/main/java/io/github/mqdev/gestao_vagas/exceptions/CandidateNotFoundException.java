package io.github.mqdev.gestao_vagas.exceptions;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(){
        super("Candidato não encontrado");
    }    
}
