package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DadoTest {

    private Dado dado;

    @BeforeEach
    void setUp() {
        dado = new Dado();
    }

    @Test
    void testTirar() {
        int resultado = dado.tirar();

        assertTrue(resultado >= 1 && resultado <= 6, "El número debería estar entre 1 y 6, pero fue: " + resultado);
    }
}