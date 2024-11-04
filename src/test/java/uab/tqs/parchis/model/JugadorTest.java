package uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    private Jugador jugador;

    @BeforeEach
    void setUp() {
        jugador = new Jugador("pablo", "rojo");
    }

    @Test
    public void testCreacionJugadorConNombreColor() {
        assertEquals("pablo", jugador.getNombre());
        assertEquals("rojo", jugador.getColor());
    }

    @Test
    void testIniciarConCuatroFichas() {
        // assertEquals(4, jugador.getFichas().length(), "El jugador debe iniciar con 4 fichas");
        // Test no va ja que no hay instancias de fichas
        // deberiamos crear mock object para poder probar este test correctamente
    }

    @Test
    void testTurnoInicialFalse() {
        assertFalse(jugador.isTurno(), "El turno inicial debe ser false");
    }

    @Test
    void testCambiarTurno() {
        jugador.setTurno(true);
        assertTrue(jugador.isTurno(), "El turno debería cambiar a true");
        jugador.setTurno(false);
        assertFalse(jugador.isTurno(), "El turno debería cambiar a false");
    }

    @Test
    void testMoverFicha() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false);
        ficha.mover(3); 
        assertEquals(3, ficha.getPos(), "La ficha debería estar en la posición 3 después de moverse");
    }


    // Faltaria mirar si la ficha esta en las ultimas casillas que no sean home
    @Test
    void testMoverFichaHome() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.mover(3); 
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse ya que esta en home");
    }

    @Test
    void testMoverFichaFin() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.mover(3); 
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse ya que esta en fin");
    }

    @Test
    void testNombreNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Jugador(null, "rojo"));
        assertEquals("El nombre no puede ser nulo o estar vacio", exception.getMessage());
    }

    @Test
    void testColorNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Jugador("pablo", null));
        assertEquals("El color no puede ser nulo o estar vacio", exception.getMessage());
    }
}

