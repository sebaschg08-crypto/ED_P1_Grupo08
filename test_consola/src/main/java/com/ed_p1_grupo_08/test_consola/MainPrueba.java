package com.ed_p1_grupo_08.test_consola;
import java.util.Random;
import java.util.Scanner;

public class MainPrueba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Jugador jugador1 = new Jugador(EstadoCelda.X, "Humano", false);
        Jugador jugador2 = new Jugador(EstadoCelda.O, "Computadora", true);        Random generadorAzar = new Random();
        Jugador jugadorQueEmpieza;
        if (generadorAzar.nextBoolean()) {
            jugadorQueEmpieza = jugador1;
            System.out.println("¡Sorteo finalizado! Empieza: " + jugador1.getNombre());
        } else {
            jugadorQueEmpieza = jugador2;
            System.out.println("¡Sorteo finalizado! Empieza: " + jugador2.getNombre());
        }
        ControladorJuego controlador = new ControladorJuego(jugador1, jugador2, jugadorQueEmpieza);

        System.out.println("¡Iniciando partida de Tres en Raya!");
        controlador.getTablero().imprimirConsola();

        while (!controlador.juegoTerminado()) {

            if (!controlador.getJugadorActual().isEsComputadora()) {
                System.out.println("Tu turno. Ingresa una posicion libre (0-8): ");
                int posicion = scanner.nextInt();

                boolean jugadaValida = controlador.jugarTurnoHumano(posicion);

                if (jugadaValida) {
                    controlador.getTablero().imprimirConsola();
                } else {
                    System.out.println("Movimiento invalido. La casilla está ocupada o no existe.");
                }

            } else {
                System.out.println("La computadora esta calculando su mejor jugada...");
                controlador.jugarTurnoPC();
                controlador.getTablero().imprimirConsola();
            }
        }

        System.out.println("\n--- FIN DEL JUEGO ---");
        if (controlador.verificarEmpate()) {
            System.out.println("Es un empate!");
        } else {
            String oponente = controlador.obtenerGanador();
            System.out.println("El ganador es:" + oponente);
        }

        scanner.close();
    }
}
