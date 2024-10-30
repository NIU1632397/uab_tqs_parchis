package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void testCreacionJugadorConNombreColor() {
        Jugador jugador = new Jugador("pablo", "rojo");
        assertEquals("pablo", jugador.getNombre());
        assertEquals("rojo", jugador.getColor());
    }
}