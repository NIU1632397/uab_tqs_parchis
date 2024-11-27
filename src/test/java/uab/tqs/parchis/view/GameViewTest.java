package uab.tqs.parchis.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testMostrarTablero() {
        Tablero tablero = new Tablero();

        game_view.mostrarTablero(tablero);

        String salida = outputStream.toString();
        assertTrue(salida.contains("[Tablero actualizado]"), "No hay Tablero actualizado");
        assertTrue(salida.contains("Tablero Principal"), "No hay tablero principal");
        assertTrue(salida.contains("Tablero Final"), "No hay tablero final");
        assertTrue(salida.contains("CasillaCasa"), "No hay casilla casa");
        // Assert para poder visualizar el tablero
        // assertEquals("", salida);
        assertTrue(salida.contains("CasillaNormal"), "No hay casilla normal");
        assertTrue(salida.contains("CasillaSegura"), "No hay casilla segura");
        assertTrue(salida.contains("CasillaFinal"), "No hay casilla final");
    }

    @Test
    public void testEsperarRespuesta() {
        // Simular entrada de usuario
        String simulatedInput = "42\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

            int resultado = game_view.esperarRespuesta();
            assertEquals(42, resultado);

        } finally {
            System.setIn(originalIn);
        }
    }
}
