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
        assertEquals(4, jugador.getFichas().length, "El jugador debe iniciar con 4 fichas");
    }

    @Test
    void testNombreFichas() {
        Ficha[] fichas = jugador.getFichas();
        for (int i = 0; i < fichas.length; i++) {
            assertEquals("rojo"+i, fichas[i].getName(), "El nombre de las fichas no es correcto");
        }
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
    void testMoverFichaEnTurno() {
        jugador.setTurno(true); // Activamos el turno del jugador

        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false); // La ficha ha salido de casa
        jugador.moverFicha(0, 3); // Movemos la ficha con índice 0, tres pasos
        assertEquals(3, ficha.getPos(), "La ficha debería estar en la posición 3 después de moverse");
    }

    @Test
    void testMoverFichaLanzaExcepcion() {
        // Caso 1: Índice fuera de rango
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> jugador.moverFicha(-1, 3));
        assertEquals("Índice de ficha fuera de rango.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> jugador.moverFicha(4, 3)); // Índice > 3
        assertEquals("Índice de ficha fuera de rango.", exception2.getMessage());

        // Caso 2: Número de pasos negativo
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> jugador.moverFicha(0, -3));
        assertEquals("El número de pasos no puede ser negativo.", exception3.getMessage());

        // Caso 3: No es el turno del jugador
        Ficha ficha = jugador.getFichas()[0];
        ficha.setHome(false); // La ficha ha salido de casa
        Exception exception4 = assertThrows(IllegalStateException.class, () -> jugador.moverFicha(0, 3));
        assertEquals("No es el turno del jugador.", exception4.getMessage());

        // Caso 4: Movimiento válido
        jugador.setTurno(true); // Activamos el turno del jugador
        ficha.setHome(false);   // La ficha ha salido de casa
        jugador.moverFicha(0, 3); // Movemos la ficha con índice 0 tres pasos
        assertEquals(3, ficha.getPos(), "La ficha debería estar en la posición 3 después de moverse");
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
        assertEquals(1, jugador.contarFichasEnFin(), "Debería haber 2 fichas en fin");
        fichas[1].setFin(true);
        assertEquals(2, jugador.contarFichasEnFin(), "Debería haber 2 fichas en fin");
        fichas[2].setFin(true);
        assertEquals(3, jugador.contarFichasEnFin(), "Debería haber 2 fichas en fin");
        fichas[3].setFin(true);
        assertEquals(4, jugador.contarFichasEnFin(), "Debería haber 2 fichas en fin");
    }

    @Test
    void testNombreNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Jugador(null, "rojo"));
        assertEquals("El nombre no puede ser nulo o estar vacio.", exception.getMessage());
    }

    @Test
    void testColorNuloLanzaExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Jugador("pablo", null));
        assertEquals("El color no puede ser nulo o estar vacio.", exception.getMessage());
    }

    @Test
    void testMoverCercaDelFinal() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.setPos(67);  // Colocamos la ficha cerca del final
        ficha.mover(2);    // Intentamos moverla 2 pasos
        assertEquals(1, ficha.getPos(), "La ficha debería volver al inicio del tablero después de pasar la posición 68.");
    }

    @Test
    void testMoverMasDe68Pasos() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.setPos(68);  // Colocamos la ficha en la posición 68
        ficha.mover(5);    // Intentamos moverla 5 pasos
        assertEquals(5, ficha.getPos(), "La ficha debería volver a la posición 5 después de pasar la posición 68.");
    }

    @Test
    void testNoMoverCuandoEstaEnMeta() {
        Ficha ficha = jugador.getFichas()[0];
        ficha.setFin(true);  // La ficha ha llegado a la meta
        ficha.mover(5);      // Intentamos moverla
        assertEquals(0, ficha.getPos(), "La ficha no debería moverse si ya ha llegado a la meta.");
    }
}