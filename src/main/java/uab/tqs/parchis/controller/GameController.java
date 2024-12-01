package uab.tqs.parchis.controller;

import uab.tqs.parchis.model.Game;
import uab.tqs.parchis.model.Jugador;
import uab.tqs.parchis.model.Tablero;
import uab.tqs.parchis.view.GameView;
import uab.tqs.parchis.model.Casilla;
import uab.tqs.parchis.model.Ficha;

public class GameController {
    private Game game;
    private GameView game_view;

    /**
     * Constructor del controlador del juego.
     * Precondiciones:
     * - `game` no debe ser nulo.
     * - `game_view` no debe ser nulo.
     * 
     * Postcondiciones:
     * - El objeto GameController se inicializa con los objetos `game` y `game_view`.
     */
    public GameController(Game game, GameView game_view) {
        assert game != null : "El objeto `game` no puede ser nulo.";
        assert game_view != null : "El objeto `game_view` no puede ser nulo.";
        
        this.game = game;
        this.game_view = game_view;
    }

      /**
     * Inicializa el juego con los jugadores y colores proporcionados.
     * Precondiciones:
     * - `jugadores` y `colores` no deben ser nulos.
     * - `jugadores.length` debe ser igual a `colores.length`.
     * - Debe haber entre 2 y 4 jugadores.
     * 
     * Postcondiciones:
     * - Los jugadores y sus fichas son inicializados correctamente.
     * - Las fichas se colocan en la casilla inicial (casa).
     */
    public void iniciarJuego(String[] jugadores, String[] colores) {
        if (jugadores.length != colores.length || jugadores.length > 4) {
            game_view.mostrarError("Debe haber entre 2 y 4 jugadores con colores únicos.");
            return;
        }

        for (int i = 0; i < jugadores.length; i++) {
            this.game.agregarJugador(jugadores[i], colores[i]);
        }

        Casilla casilla_casa = game.getTablero().getCasillaTablero(0);
        for (Jugador jugador : game.getJugadores()) {
            for (Ficha ficha : jugador.getFichas()) {
                casilla_casa.agregarFicha(ficha);
            }
        }

        game_view.mostrarMensaje("Juego inicializado correctamente.");
        actualizarVista();
    }

    /**
     * Lanza el dado y devuelve el valor obtenido.
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Se lanza el dado y se muestra el resultado en la vista.
     * - El valor del dado está en el rango 1-6.
     */
    public int lanzarDado() {
        int valor_dado = game.lanzarDado();
        game_view.mostrarMensaje("El valor del dado es: " + valor_dado);
        return valor_dado;
    }

    /**
     * Avanza al siguiente turno.
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - Si el juego ha terminado, muestra el ganador.
     * - Si no, avanza el turno al siguiente jugador.
     */
    public void avanzarTurno() {
        if (game.esFinDelJuego()) {
            game_view.mostrarMensaje("El juego ha terminado. El ganador es: " + game.obtenerGanador().getNombre());
            return;
        }

        game.avanzarTurno();
        game_view.mostrarMensaje("Turno avanzado. Ahora es el turno de: " + game.getJugadorActual().getNombre());
        actualizarVista();
    }

     /**
     * Comienza el turno del jugador actual, lanzando el dado y moviendo una ficha.
     * Precondiciones:
     * - El jugador actual debe tener al menos una ficha en casa (para poder moverla).
     * - Se debe lanzar el dado correctamente.
     * 
     * Postcondiciones:
     * - El jugador lanza el dado.
     * - Elige qué ficha mover y se mueve la ficha correspondiente.
     */
    public void empezarTurno() {
        Jugador jugador_actual = game.getJugadorActual();

        game_view.mostrarMensaje("El jugador " + jugador_actual.getNombre() + ", color " + jugador_actual.getColor() + ", lanza el dado.");
        int valor_dado = lanzarDado();

        game_view.mostrarMensaje("¿Que ficha desea mover? Las respuestas pueden ser 0, 1, 2 o 3.");
        int respuesta = game_view.esperarRespuesta();       // METODO DEL VIEW PARA QUE ESPERE A LA RESPUESTA DEL USUARIO, DONDE ESTE TIENE QUE ELEGIR ENTRE UN NUMERO DEL 0 AL 3

        Ficha ficha = jugador_actual.getFichas()[respuesta];

        if (ficha.getPos() == 0) {
            if (valor_dado == 5) {
                game_view.mostrarMensaje("Movimiento inicial de la ficha.");
                movimientoInicial(ficha);
                avanzarTurno();
                return;
            } else {
                game_view.mostrarMensaje("La ficha no se ha movido de casa.");
                avanzarTurno();
                return;
            }
        }

        game_view.mostrarMensaje("Moviendo la ficha.");
        moverFicha(ficha, valor_dado);

        avanzarTurno();
    }

    /**
     * Realiza el movimiento inicial de una ficha desde la casilla de casa hasta la casilla correspondiente.
     * Precondiciones:
     * - La ficha no debe estar en la casilla final (debe estar en casa).
     * - El color de la ficha debe ser válido.
     * 
     * Postcondiciones:
     * - La ficha se mueve a la casilla correspondiente según su color.
     * - La ficha ya no está en casa.
     */
    public void movimientoInicial(Ficha ficha) {
        Tablero tablero = game.getTablero();
        Casilla casilla_casa = tablero.getCasillaTablero(0);
        Casilla casilla = null;
        if (ficha.getColor() == "Amarillo") {
            casilla = tablero.getCasillaTablero(5);
            casilla.agregarFicha(ficha);
            ficha.mover(5);
            ficha.setHome(false);
        } else if (ficha.getColor() == "Azul") {
            casilla = tablero.getCasillaTablero(22);
            casilla.agregarFicha(ficha);
            ficha.mover(22);
            ficha.setHome(false);
        } else if (ficha.getColor() == "Rojo") {
            casilla = tablero.getCasillaTablero(39);
            casilla.agregarFicha(ficha);
            ficha.mover(39);
            ficha.setHome(false);
        } else if (ficha.getColor() == "Verde") {
            casilla = tablero.getCasillaTablero(56);
            casilla.agregarFicha(ficha);
            ficha.mover(56);
            ficha.setHome(false);
        }

        casilla_casa.quitarFicha(ficha);
    }

    /**
     * Mueve una ficha en el tablero.
     * Precondiciones:
     * - La ficha no debe haber llegado a la meta.
     * - La cantidad de pasos debe ser positiva.
     * 
     * Postcondiciones:
     * - La ficha se mueve la cantidad indicada de pasos, respetando las casillas bloqueadas y la meta.
     */
    public void moverFicha(Ficha ficha, int cantidad) {
        Jugador jugador_actual = game.getJugadorActual();
        Tablero objeto_tablero = game.getTablero();         // ESTA VARIABLE TABLERO CONTIENE EL TABLERO PRINCIPAL Y EL TABLERO FINAL, DONDE AMBOS SON LISTAS DE CASILLAS

        // ESTO ES EL MOVIMIENTO DE LA FICHA POR EL TABLERO
        int iterador_posicion = 1;
        int posicion_ficha = ficha.getPos();

        Casilla casilla_anterior = objeto_tablero.getCasillaTablero(posicion_ficha);
        casilla_anterior.quitarFicha(ficha);
        Casilla casilla_siguiente = null;

        while (iterador_posicion <= cantidad) {
            // LOGICA PARA PASAR DEL TABLERO PRINCIPAL AL TABLERO FINAL
            if (jugador_actual.getColor() == "Amarillo" && casilla_anterior.getNumero() == 68) {
                cantidad = cantidad - iterador_posicion;
                iterador_posicion = 0;
                posicion_ficha = 0;
                ficha.setEstaFinal(true);
            } else if (jugador_actual.getColor() == "Azul" && casilla_anterior.getNumero() == 17) {
                cantidad = cantidad - iterador_posicion;
                iterador_posicion = 0;
                posicion_ficha = 0;
                ficha.setEstaFinal(true);
            } else if (jugador_actual.getColor() == "Rojo" && casilla_anterior.getNumero() == 34) {
                cantidad = cantidad - iterador_posicion;
                iterador_posicion = 0;
                posicion_ficha = 0;
                ficha.setEstaFinal(true);
            } else if (jugador_actual.getColor() == "Verde" && casilla_anterior.getNumero() == 51) {
                cantidad = cantidad - iterador_posicion;
                iterador_posicion = 0;
                posicion_ficha = 0;
                ficha.setEstaFinal(true);
            }

            if (ficha.getEstaFinal()) {
                game_view.mostrarMensaje("Ficha en el final");
                casilla_siguiente = objeto_tablero.getCasillaTableroFinal(posicion_ficha + iterador_posicion);
            } else {
                casilla_siguiente = objeto_tablero.getCasillaTablero(posicion_ficha + iterador_posicion);
            }

            if (casilla_siguiente.getNumero() == 8 && ficha.getEstaFinal()){
                game_view.mostrarMensaje("La ficha acaba de llegar al final");
                // PARA SIMPLIFICARLO HAGO QUE AUNQUE SE PASE PUES QUE LLEGUE AL FINAL Y YA.
                game_view.mostrarMensaje("¿Que ficha quiere que se le sumen 10 posiciones? Las opciones validas son 0, 1, 2 o 3.");
                int respuesta = game_view.esperarRespuesta();       // METODO DEL VIEW PARA QUE ESPERE A LA RESPUESTA DEL USUARIO, DONDE ESTE TIENE QUE ELEGIR ENTRE UN NUMERO DEL 0 AL 3
                Ficha nueva_ficha = jugador_actual.getFichas()[respuesta];
                moverFicha(nueva_ficha, 10);

                casilla_siguiente.agregarFicha(ficha);
                ficha.mover(iterador_posicion);
                ficha.setFin(true);
            break;
            }

            // LOGICA PARA DEJAR DE AVANZAR SI LA FICHA SE ENCUENTRA CON UN BLOQUEO POR PARTE DE OTRAS FICHAS
            if (casilla_siguiente.getBloqueo()) {
                game_view.mostrarMensaje("La ficha se ha topado con una casilla bloqueada por fichas enemigas.");
                casilla_anterior.agregarFicha(ficha);
                ficha.mover(iterador_posicion-1);
                break;
            }

            // LOGICA PARA TERMINAR DE MOVER LA FICHA
            if (iterador_posicion == cantidad) {
                game_view.mostrarMensaje("La ficha ha terminado de moverse.");
                casilla_siguiente.agregarFicha(ficha);
                ficha.mover(iterador_posicion);
                if (casilla_siguiente.getFichas().size() != 1 && !casilla_siguiente.getSeguro()) {
                    // LOGICA PARA COMER UNA FICHA ENEMIGA
                    for (Ficha ficha_ya_en_casilla : casilla_siguiente.getFichas()) {
                        if (ficha_ya_en_casilla.getColor() != ficha.getColor()) {
                            game_view.mostrarMensaje("¿Que ficha quiere que se le sumen 10 posiciones? Las opciones validas son 0, 1, 2 o 3.");
                            int respuesta = game_view.esperarRespuesta();       // METODO DEL VIEW PARA QUE ESPERE A LA RESPUESTA DEL USUARIO, DONDE ESTE TIENE QUE ELEGIR ENTRE UN NUMERO DEL 0 AL 3
                            Ficha nueva_ficha = jugador_actual.getFichas()[respuesta];
                            moverFicha(nueva_ficha, 20);
                        }
                    }
                }
            } else {
                game_view.mostrarMensaje("La ficha se mueve una casilla.");
                casilla_anterior = casilla_siguiente;
            }

            // LOGICA PARA PASAR DEL 68(FINAL TABLERO) AL 1(INICIO TABLERO)
            if (casilla_anterior.getNumero() == 68) {
                game_view.mostrarMensaje("La ficha pasa del final al principio");
                ficha.mover(iterador_posicion);
                cantidad = cantidad - iterador_posicion;
                iterador_posicion = 0;
                posicion_ficha = 0;
            }

            iterador_posicion++;
        }
    }

    /**
     * Actualiza la vista del juego.
     * Precondiciones:
     * - `game_view` no debe ser nulo. El controlador necesita una vista válida para actualizar el tablero.
     * - `game` debe estar correctamente inicializado y contener un tablero válido.
     * 
     * Postcondiciones:
     * - El tablero debe ser mostrado en la vista actualizada.
     */
    public void actualizarVista() {
        game_view.mostrarTablero(game.getTablero());
    }
}