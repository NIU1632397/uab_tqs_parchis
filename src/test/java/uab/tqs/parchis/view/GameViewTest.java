package uab.tqs.parchis.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uab.tqs.parchis.model.Jugador;
import uab.tqs.parchis.model.Tablero;

public class GameViewTest {
    
    private GameView game_view;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        game_view = new GameView();

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    void testMostrarMensaje() {
        String mensaje = "Inicio del juego";
        game_view.mostrarMensaje(mensaje);

        String salida = outputStream.toString();
        assertTrue(salida.contains("[Mensaje]: Inicio del juego"));
    }

    @Test
    void testMostrarError() {
        String mensajeError = "Error al inicializar el juego.";
        game_view.mostrarError(mensajeError);

        String salida = outputStream.toString();
        assertTrue(salida.contains("[Error]: Error al inicializar el juego."));
    }

    @Test
    void testActualizarTablero() {
        Tablero tablero = new Tablero();

        game_view.actualizarTablero(tablero);

        String salida = outputStream.toString();
        assertTrue(salida.contains("[Tablero actualizado]"));
        assertTrue(salida.contains("Tablero Principal"));
        assertTrue(salida.contains("Tablero Final"));
        assertTrue(salida.contains("CasillaCasa"));
        assertTrue(salida.contains("CasillaNormal"));
        assertTrue(salida.contains("CasillaSegura"));
        assertTrue(salida.contains("CasillaFinal"));
    }

    // @Test
    // void testActualizarJugadores() {
    //     Jugador jugador1 = new Jugador("Jugador 1", "amarillo");
    //     Jugador jugador2 = new Jugador("Jugador 2", "azul");

    //     game_view.actualizarJugadores(List.of(jugador1, jugador2));

    //     // HACE FALTA AÑADIR LA FUNCION toString() A LA CLASE JUGADOR
    //     String salida = outputStream.toString();
    //     assertTrue(salida.contains("Jugador 1 (Color: amarillo)"));
    //     assertTrue(salida.contains("Jugador 2 (Color: azul)"));
    // }
}
