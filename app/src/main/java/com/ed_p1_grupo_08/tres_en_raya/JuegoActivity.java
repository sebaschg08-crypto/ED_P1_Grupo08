package com.ed_p1_grupo_08.tres_en_raya;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoActivity extends AppCompatActivity {

    private ControladorJuego controlador;
    private Button[] botonesTablero;
    private TextView textoTurno;

    private Button btnGuardar;
    private Button btnAnalizar;
    private Button btnRecomendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        String simboloTxt = getIntent().getStringExtra("simboloHumano");
        boolean empiezaHumano = getIntent().getBooleanExtra("empiezaHumano", true);
        int modoJuego = getIntent().getIntExtra("modoJuego", 2);

        if (simboloTxt == null) simboloTxt = "X";
        EstadoCelda simboloJ1 = EstadoCelda.valueOf(simboloTxt);
        EstadoCelda simboloJ2 = (simboloJ1 == EstadoCelda.O) ? EstadoCelda.X : EstadoCelda.O;

        Jugador p1 = null;
        Jugador p2 = null;
        Jugador empieza = null;

        if (modoJuego == 1) { // Humano vs Humano
            p1 = new Jugador(simboloJ1, "Jugador 1", false);
            p2 = new Jugador(simboloJ2, "Jugador 2", false);
            // Si empiezaHumano es falso (botón izquierdo), empieza Jugador 1.
            // Si es verdadero (botón derecho), empieza Jugador 2.
            empieza = empiezaHumano ? p2 : p1;

        } else if (modoJuego == 2) {
            p1 = new Jugador(simboloJ1, "Usted", false);
            p2 = new Jugador(simboloJ2, "Computador", true);
            empieza = empiezaHumano ? p1 : p2;

        } else if (modoJuego == 3) {
            p1 = new Jugador(simboloJ1, "PC 1", true);
            p2 = new Jugador(simboloJ2, "PC 2", true);
            empieza = empiezaHumano ? p2 : p1;
        }

        controlador = new ControladorJuego(p1, p2, empieza);

        iniciarInterfaz();

        // Si el jugador que empieza es una computadora, dispara su turno automáticamente
        if (controlador.getJugadorActual().isEsComputadora()) {
            ejecutarTurnoComputadoraConRetraso();
        }
    }

    private void iniciarInterfaz() {
        textoTurno = findViewById(R.id.textoTurno);
        botonesTablero = new Button[9];

        botonesTablero[0] = findViewById(R.id.btn0); botonesTablero[1] = findViewById(R.id.btn1);
        botonesTablero[2] = findViewById(R.id.btn2); botonesTablero[3] = findViewById(R.id.btn3);
        botonesTablero[4] = findViewById(R.id.btn4); botonesTablero[5] = findViewById(R.id.btn5);
        botonesTablero[6] = findViewById(R.id.btn6); botonesTablero[7] = findViewById(R.id.btn7);
        botonesTablero[8] = findViewById(R.id.btn8);

        btnGuardar = findViewById(R.id.guardar);
        btnAnalizar = findViewById(R.id.analizar);
        btnRecomendar = findViewById(R.id.recomendar);

        for (int i = 0; i < 9; i++) {
            final int pos = i;
            botonesTablero[i].setOnClickListener(view -> manejarTurno(pos));
        }

        btnGuardar.setOnClickListener(v -> guardarPartida());
        btnAnalizar.setOnClickListener(v -> abrirPantallaAnalisis());
        btnRecomendar.setOnClickListener(v -> recomendarJugada());

        actualizarTablero();
    }

    private void actualizarTablero() {
        EstadoCelda[] celdas = controlador.getTablero().getCeldas();

        for (int i = 0; i < 9; i++) {
            botonesTablero[i].setBackgroundColor(Color.parseColor("#FF6200EE")); // Restaurar color morado original
            if (celdas[i] == EstadoCelda.X) {
                botonesTablero[i].setText("X");
            } else if (celdas[i] == EstadoCelda.O) {
                botonesTablero[i].setText("O");
            } else {
                botonesTablero[i].setText("");
            }
        }

        if (!controlador.juegoTerminado()) {
            textoTurno.setText("Turno de: " + controlador.getJugadorActual().getNombre() +
                    " (" + controlador.getJugadorActual().getSimbolo() + ")");
        }
    }

    private void manejarTurno(int posicion) {
        if (controlador.juegoTerminado() || controlador.getJugadorActual().isEsComputadora()) return;

        boolean jugadaValida = controlador.jugarTurnoHumano(posicion);
        if (!jugadaValida) return;

        actualizarTablero();

        if (controlador.juegoTerminado()) {
            finalizarJuego();
            return;
        }

        if (controlador.getJugadorActual().isEsComputadora()) {
            ejecutarTurnoComputadoraConRetraso();
        }
    }

    private void ejecutarTurnoComputadoraConRetraso() {
        textoTurno.setText("Calculando...");

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!controlador.juegoTerminado()) {
                controlador.jugarTurnoPC();
                actualizarTablero();

                if (controlador.juegoTerminado()) {
                    finalizarJuego();
                } else if (controlador.getJugadorActual().isEsComputadora()) {
                    // Si el siguiente jugador también es PC (Modo PC vs PC), crear un bucle visual
                    ejecutarTurnoComputadoraConRetraso();
                }
            }
        }, 1000);
    }

    private void recomendarJugada() {
        if (controlador.juegoTerminado() || controlador.getJugadorActual().isEsComputadora()) return;

        Jugador actual = controlador.getJugadorActual();
        EstadoCelda simOponente = (actual.getSimbolo() == EstadoCelda.X) ? EstadoCelda.O : EstadoCelda.X;

        MinimaxAI iaTemp = new MinimaxAI(actual, new Jugador(simOponente, "Op", true));

        Tablero mejorTablero = iaTemp.obtenerMejorJugada(controlador.getTablero());

        if (mejorTablero != null) {
            EstadoCelda[] celdasActuales = controlador.getTablero().getCeldas();
            EstadoCelda[] celdasNuevas = mejorTablero.getCeldas();

            for (int i = 0; i < 9; i++) {
                // Buscamos qué posición cambió la IA
                if (celdasActuales[i] == EstadoCelda.VACIO && celdasNuevas[i] != EstadoCelda.VACIO) {
                    botonesTablero[i].setBackgroundColor(Color.YELLOW);
                    Toast.makeText(this, "Te recomiendo jugar en esta casilla", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    private void guardarPartida() {
        String estado = controlador.exportarEstado();
        Toast.makeText(this, "Estado exportado: " + estado, Toast.LENGTH_LONG).show();
    }

    private void abrirPantallaAnalisis() {
        Intent intent = new Intent(JuegoActivity.this, AnalisisActivity.class);
        startActivity(intent);
    }

    private void finalizarJuego() {
        Intent intent = new Intent(JuegoActivity.this, ResultadoActivity.class);
        boolean empate = controlador.verificarEmpate();
        intent.putExtra("empate", empate);

        if (!empate) {
            Jugador ganador = controlador.obtenerGanador();
            intent.putExtra("resultado", "Ganador: " + ganador.getNombre());
            intent.putExtra("simboloGanador", ganador.getSimbolo().name());
        } else {
            intent.putExtra("resultado", "¡Es un Empate!");
        }

        startActivity(intent);
        finish();
    }
}