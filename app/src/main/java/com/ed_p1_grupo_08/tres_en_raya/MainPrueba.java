package com.ed_p1_grupo_08.tres_en_raya;
import java.util.Scanner;
public class MainPrueba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Jugador humano = new Jugador(EstadoCelda.X, "Humano", false);
        Jugador pc = new Jugador(EstadoCelda.O, "Computadora", true);

        ControladorJuego controlador = new ControladorJuego(humano, pc, humano);

        System.out.println("¡Iniciando partida de Tres en Raya!");
        controlador.getTablero().imprimirConsola();

        while (!controlador.juegoTerminado()) {

            if (!controlador.getJugadorActual().isEsComputadora()) {
                System.out.println("Tu turno. Ingresa una posición libre (0-8): ");
                int posicion = scanner.nextInt();

                boolean jugadaValida = controlador.jugarTurnoHumano(posicion);

                if (jugadaValida) {
                    controlador.getTablero().imprimirConsola();
                } else {
                    System.out.println("Movimiento inválido. La casilla está ocupada o no existe.");
                }

            } else {
                System.out.println("La computadora está calculando su mejor jugada...");
                controlador.jugarTurnoPC();
                controlador.getTablero().imprimirConsola();
            }
        }

        System.out.println("\n--- FIN DEL JUEGO ---");
        if (controlador.verificarEmpate()) {
            System.out.println("¡Es un empate!");
        } else {
            String oponente = controlador.obtenerGanador();
            System.out.println("¡El ganador es:" + oponente);
        }

        scanner.close();
    }
}
