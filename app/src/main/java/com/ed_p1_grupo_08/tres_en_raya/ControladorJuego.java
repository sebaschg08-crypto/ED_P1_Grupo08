package com.ed_p1_grupo_08.tres_en_raya;

public class ControladorJuego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private MinimaxAI ia;
    public ControladorJuego(Jugador j1, Jugador j2, Jugador empieza) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActual = empieza;
        this.tablero = new Tablero();

        if (j1.isEsComputadora() || j2.isEsComputadora()) {
            Jugador pc = j1.isEsComputadora() ? j1 : j2;
            Jugador humano = j1.isEsComputadora() ? j2 : j1;
            this.ia = new MinimaxAI(pc, humano);
        }

    }
}
