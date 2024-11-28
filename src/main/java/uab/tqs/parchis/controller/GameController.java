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

    public GameController(Game game, GameView game_view) {
        this.game = game;
        this.game_view = game_view;
    }

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

    public int lanzarDado() {
        int valor_dado = game.lanzarDado();
        game_view.mostrarMensaje("El valor del dado es: " + valor_dado);
        return valor_dado;
    }

    public void avanzarTurno() {
        if (game.esFinDelJuego()) {
            game_view.mostrarMensaje("El juego ha terminado. El ganador es: " + game.obtenerGanador().getNombre());
            return;
        }

        game.avanzarTurno();
        game_view.mostrarMensaje("Turno avanzado. Ahora es el turno de: " + game.getJugadorActual().getNombre());
        actualizarVista();
    }

    public void empezarTurno() {
        Jugador jugador_actual = game.getJugadorActual();

        game_view.mostrarMensaje("El jugador " + jugador_actual.getNombre() + "lanza el dado.");
        int valor_dado = lanzarDado();

        game_view.mostrarMensaje("¿Que ficha desea mover? Las respuestas pueden ser 0, 1, 2 o 3.");
        int respuesta = game_view.esperarRespuesta();       // METODO DEL VIEW PARA QUE ESPERE A LA RESPUESTA DEL USUARIO, DONDE ESTE TIENE QUE ELEGIR ENTRE UN NUMERO DEL 0 AL 3

        Ficha ficha = jugador_actual.getFichas()[respuesta];

        if (ficha.getPos() == 0 && valor_dado == 5){
            movimientoInicial(ficha);
        } else {
            moverFicha(ficha, valor_dado);
        }

        avanzarTurno();
    }

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
        actualizarVista();
    }

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
        actualizarVista();
    }

    public void actualizarVista() {
        game_view.mostrarTablero(game.getTablero());
    }
}
