package com.ed_p1_grupo_08.tres_en_raya;

public class ControladorJuego {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;

    public ControladorJuego(Jugador j1, Jugador j2, Jugador empieza) {
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActual = empieza;
        this.tablero = new Tablero();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public boolean juegoTerminado() {
        EstadoCelda j1 = jugador1.getSimbolo();
        EstadoCelda j2 = jugador2.getSimbolo();
        boolean alguienGano = (this.tablero.hayGanador(j1) || this.tablero.hayGanador(j2));
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
        if (jugadorActual.isEsComputadora() && !juegoTerminado()) {

            Jugador oponente = (jugadorActual == jugador1) ? jugador2 : jugador1;

            MinimaxAI iaActual = new MinimaxAI(jugadorActual, oponente);

            Tablero mejorTablero = iaActual.obtenerMejorJugada(tablero);
            if (mejorTablero != null) {
                this.tablero = mejorTablero;
                cambiarTurno();
            }
        }
    }

    public boolean verificarEmpate() {
        EstadoCelda j1 = jugador1.getSimbolo();
        EstadoCelda j2 = jugador2.getSimbolo();
        boolean alguienGano = (this.tablero.hayGanador(j1) || this.tablero.hayGanador(j2));

        return this.tablero.isFull() && !alguienGano;
    }

    public Jugador obtenerGanador() {
        return jugadorActual == jugador1 ? jugador2 : jugador1;
    }

    public String exportarEstado() {
        StringBuilder sb = new StringBuilder();

        sb.append(jugador1.getSimbolo()).append(",")
                .append(jugador1.getNombre()).append(",")
                .append(jugador1.isEsComputadora()).append(";");

        sb.append(jugador2.getSimbolo()).append(",")
                .append(jugador2.getNombre()).append(",")
                .append(jugador2.isEsComputadora()).append(";");

        sb.append(jugadorActual.getSimbolo()).append(";");

        EstadoCelda[] celdas = tablero.getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            sb.append(celdas[i].name());
            if (i < celdas.length - 1) sb.append(",");
        }

        sb.append(";").append(tablero.getOcupadas());

        return sb.toString();
    }

    public static ControladorJuego cargarEstado(String datosGuardados) {
        String[] partes = datosGuardados.split(";");

        String[] p1Datos = partes[0].split(",");
        Jugador j1 = new Jugador(EstadoCelda.valueOf(p1Datos[0]), p1Datos[1], Boolean.parseBoolean(p1Datos[2]));

        String[] p2Datos = partes[1].split(",");
        Jugador j2 = new Jugador(EstadoCelda.valueOf(p2Datos[0]), p2Datos[1], Boolean.parseBoolean(p2Datos[2]));

        EstadoCelda turnoSimbolo = EstadoCelda.valueOf(partes[2]);
        Jugador turnoActual = (j1.getSimbolo() == turnoSimbolo) ? j1 : j2;

        ControladorJuego controladorRestaurado = new ControladorJuego(j1, j2, turnoActual);

        String[] celdasDatos = partes[3].split(",");
        EstadoCelda[] celdasRestauradas = new EstadoCelda[9];
        for (int i = 0; i < 9; i++) {
            celdasRestauradas[i] = EstadoCelda.valueOf(celdasDatos[i]);
        }
        int ocupadasRestauradas = Integer.parseInt(partes[4]);

        controladorRestaurado.tablero = new Tablero(celdasRestauradas, ocupadasRestauradas);

        return controladorRestaurado;
    }
}
