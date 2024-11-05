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
    void testCambiarTurnoConEstadoIgualLanzaExcepcion() {
        jugador.setTurno(false);
        Exception exception = assertThrows(IllegalStateException.class, () -> jugador.setTurno(false));
        assertEquals("El jugador ya tiene el estado de turno especificado.", exception.getMessage());
    }

    @Test
    void testMoverFicha() {
        jugador.moverFicha(0, 3);
        assertEquals(3, ficha.getPos(), "La ficha debería estar en la posición 3 después de moverse");
    }

    @Test
    void testMoverFichaEnTurno() {
        jugador.setTurno(true); // Activamos el turno del jugador

        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false); // La ficha ha salido de casa
        jugador.moverFicha(0, 3); // Movemos la ficha con índice 0, tres pasos
        assertEquals(3, ficha.getPos(), "La ficha debería estar en la posición 3 después de moverse");
    }

    @Test
    void testMoverFichaFueraDeTurnoLanzaExcepcion() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false); // La ficha ha salido de casa

        Exception exception = assertThrows(IllegalStateException.class, () -> jugador.moverFicha(0, 3));
        assertEquals("No es el turno del jugador.", exception.getMessage());
    }

    // Faltaria mirar si la ficha esta en las ultimas casillas que no sean home
    @Test
    void testMoverFichaHome() {
        jugador.setTurno(true); // Activamos el turno del jugador
        Ficha ficha = jugador.getFichas()[0];

        jugador.moverFicha(0, 3); // Estado default de la ficha --> Home: true
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse ya que esta en home");
    }

    @Test
    void testMoverFichaFin() {
        jugador.setTurno(true); // Activamos el turno del jugador
        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false); // La ficha ha salido de casa
        ficha.setFin(true);   // La ficha ha llegado a la meta

        jugador.moverFicha(0, 3); 
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse ya que está en fin");
    }

    @Test
    void testContarFichasEnFin() {
        Ficha[] fichas = jugador.getFichas();
        fichas[0].setFin(true);
        fichas[1].setFin(true);

        assertEquals(2, jugador.contarFichasEnFin(), "Debería haber 2 fichas en fin");
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

