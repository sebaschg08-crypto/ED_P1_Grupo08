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
    public Tablero getTablero() {
        return tablero;
    }
    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public boolean juegoTerminado(){
        EstadoCelda j1= jugador1.getSimbolo();
        EstadoCelda j2= jugador2.getSimbolo();
        boolean alguienGano= (this.tablero.hayGanador(j1)||this.tablero.hayGanador(j2));
        return this.tablero.isFull() || alguienGano;
    }
    private void cambiarTurno() {
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }
    public boolean jugarTurnoHumano(int posicion) {
        if (juegoTerminado() || jugadorActual.isEsComputadora()) {
            return false;
        }
        if (tablero.colocarFicha(posicion, jugadorActual.getSimbolo())) {
            cambiarTurno();
            return true;
        } else {
            return false;
        }
    }
    public void jugarTurnoPC() {
        if (ia != null && jugadorActual.isEsComputadora() && !juegoTerminado()) {
            Tablero mejorTablero = ia.obtenerMejorJugada(tablero);
            if (mejorTablero != null){
                this.tablero = mejorTablero;
                cambiarTurno();
            }
        }
    }
    public boolean verificarEmpate(){
        return this.tablero.isFull();
    }
    public String obtenerGanador(){
        Jugador ganador= jugadorActual == jugador1 ? jugador2 : jugador1;
        return ganador.getNombre()+" ("+ganador.getSimbolo()+")";
    }
}

