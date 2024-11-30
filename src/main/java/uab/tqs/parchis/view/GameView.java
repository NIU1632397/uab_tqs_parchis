package uab.tqs.parchis.view;

import java.util.Scanner;

import uab.tqs.parchis.model.Tablero;

public class GameView {

    /**
     * Muestra un mensaje en la consola.
     * Precondiciones:
     * - `mensaje` no debe ser nulo.
     * 
     * Postcondiciones:
     * - El mensaje se muestra correctamente en la consola con el formato adecuado.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println("[Mensaje]: " + mensaje);
    }

    /**
     * Muestra un mensaje de error en la consola.
     * Precondiciones:
     * - `mensaje` no debe ser nulo.
     * 
     * Postcondiciones:
     * - El mensaje de error se muestra correctamente en la consola con el formato adecuado.
     */
    public void mostrarError(String mensaje) {
        System.out.println("[Error]: " + mensaje);
    }

    /**
     * Muestra el tablero actualizado en la consola.
     * Precondiciones:
     * - `tablero` no debe ser nulo. El objeto Tablero debe estar inicializado.
     * 
     * Postcondiciones:
     * - El tablero se muestra correctamente en la consola con el formato adecuado.
     */
    public void mostrarTablero(Tablero tablero) {
        System.out.println("[Tablero actualizado]");
        System.out.println(tablero.toString());
    }

    /**
     * Espera una respuesta del usuario (un número entero).
     * Precondiciones:
     * - Ninguna.
     * 
     * Postcondiciones:
     * - La respuesta del usuario debe ser un número entero.
     * - Si la respuesta es válida, el número se devuelve.
     * - Si la respuesta no es válida, se sigue pidiendo al usuario una entrada válida.
     */
    public int esperarRespuesta() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0; // Valor por defecto
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("La ficha a mover es la:");
                String input = scanner.nextLine();
                numero = Integer.parseInt(input); // Intenta convertir a entero
                entradaValida = true; // Si tiene éxito, se considera válida
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        }
        return numero;
    }
}
