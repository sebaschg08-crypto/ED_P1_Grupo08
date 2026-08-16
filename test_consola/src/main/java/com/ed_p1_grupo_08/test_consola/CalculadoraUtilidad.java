package com.ed_p1_grupo_08.test_consola;


public class CalculadoraUtilidad {
  public static int evaluar(Tablero tablero, Jugador jugador, Jugador oponente) {
    EstadoCelda sJugador = jugador.getSimbolo();
    EstadoCelda sOponente = oponente.getSimbolo();

    int pJugador = tablero.contarLineasDisponibles(sJugador);
    int pOponente = tablero.contarLineasDisponibles(sOponente);

    return pJugador - pOponente;

  }
}

