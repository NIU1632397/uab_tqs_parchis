package uab.tqs.parchis.view;

import java.util.Scanner;

import uab.tqs.parchis.model.Tablero;

public class GameView {

    public void mostrarMensaje(String mensaje) {
        System.out.println("[Mensaje]: " + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("[Error]: " + mensaje);
    }

    public void mostrarTablero(Tablero tablero) {
        System.out.println("[Tablero actualizado]");
        System.out.println(tablero.toString());
    }

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
