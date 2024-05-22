package io.github.mqdev.gestao_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrimeiroTeste {

    @Test
    public void deve_ser_possivel_somar_dois_inteiros(){
        int a = 1;
        int b = 2;
        int resultado = sum(a, b);
        assertEquals(3, resultado);
    }

    @Test
    public void deve_ser_possivel_somar_dois_inteiros_com_negativos(){
        int a = -1;
        int b = -2;
        int resultado = sum(a, b);
        assertEquals(-3, resultado);
    }

    @Test
    public void deve_ser_possivel_somar_dois_inteiros_com_um_negativo(){
        int a = -1;
        int b = 2;
        int resultado = sum(a, b);
        assertEquals(1, resultado);
    }

    @Test
    public void deve_ser_possivel_somar_dois_inteiros_com_um_zero(){
        int a = 0;
        int b = 2;
        int resultado = sum(a, b);
        assertEquals(2, resultado);
    }

    @Test
    public void deve_ser_possivel_somar_dois_inteiros_com_dois_zeros(){
        int a = 0;
        int b = 0;
        int resultado = sum(a, b);
        assertEquals(0, resultado);
    }

    public static int sum(int a, int b) {
        return a + b;
    }    
}
