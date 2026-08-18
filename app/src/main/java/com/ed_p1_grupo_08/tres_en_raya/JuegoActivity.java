package com.ed_p1_grupo_08.tres_en_raya;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JuegoActivity extends AppCompatActivity {

    private ControladorJuego controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        String simboloTxt= getIntent().getStringExtra("simboloHumano");
        boolean empiezaHumano= getIntent().getBooleanExtra("empiezaHumano", true);

        EstadoCelda simboloHumano= EstadoCelda.valueOf(simboloTxt);
        EstadoCelda simboloPC;

        simboloPC= simboloHumano==EstadoCelda.O ? EstadoCelda.X : EstadoCelda.O;

        Jugador humano= new Jugador(simboloHumano, "Humano", false);
        Jugador pc= new Jugador(simboloPC, "Computador", true);

        Jugador empieza;
        empieza= empiezaHumano ? humano : pc;

        controlador= new ControladorJuego(humano, pc, empieza);

        iniciarInterfaz();

        if (controlador.getJugadorActual().isEsComputadora()){
            controlador.jugarTurnoPC();
            actualizarTablero();
        }

    }

    private void iniciarInterfaz(){}

    private void actualizarTablero(){}

}