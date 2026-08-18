package com.ed_p1_grupo_08.tres_en_raya;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JuegoActivity extends AppCompatActivity {

    private ControladorJuego controlador;
    private Button[] botonesTablero;
    private TextView textoTurno;

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

        TextView textoEstado= findViewById(R.id.textoTurno);
        textoEstado.setText("Turno de: "+ controlador.getJugadorActual().getNombre());

        iniciarInterfaz();

        if (controlador.getJugadorActual().isEsComputadora()){
            controlador.jugarTurnoPC();
            actualizarTablero();
        }

    }

    private void iniciarInterfaz(){
        textoTurno= findViewById(R.id.textoTurno);

        botonesTablero= new Button[9];
        botonesTablero[0]= findViewById(R.id.btn0); botonesTablero[1]= findViewById(R.id.btn1);
        botonesTablero[2]= findViewById(R.id.btn2); botonesTablero[3]= findViewById(R.id.btn3);
        botonesTablero[4]= findViewById(R.id.btn4); botonesTablero[5]= findViewById(R.id.btn5);
        botonesTablero[6]= findViewById(R.id.btn6); botonesTablero[7]= findViewById(R.id.btn7);
        botonesTablero[8]= findViewById(R.id.btn8);

        configurarEventosTablero();
        actualizarTablero();

    }

    private void actualizarTablero(){
        EstadoCelda[] celdas= controlador.getTablero().getCeldas();

        for (int i= 0; i<9; i++){
            if (celdas[i]==EstadoCelda.X){ botonesTablero[i].setText("X");}
            else if (celdas[i]==EstadoCelda.O){ botonesTablero[i].setText("O");}
            else {botonesTablero[i].setText("");}
        }
        if (!controlador.juegoTerminado()){
            textoTurno.setText(
                    "Turno de: "+
                            controlador.getJugadorActual().getNombre()+
                            " ("+controlador.getJugadorActual().getSimbolo()+")");
        }
    }

    private void configurarEventosTablero(){
        for (int i= 0; i<9; i++){
            final int pos= i;

            botonesTablero[i].setOnClickListener(view -> {manejarTurno(pos);});

        }

    }

    private void manejarTurno(int posicion){
        if (controlador.juegoTerminado()) return;

        if(controlador.getJugadorActual().isEsComputadora()) return;

        boolean jugadaValida= controlador.jugarTurnoHumano(posicion);

        if (!jugadaValida) return;

        actualizarTablero();

        if (controlador.juegoTerminado()){ finalizarJuego(); return;}

        controlador.jugarTurnoPC();

        actualizarTablero();
        if (controlador.juegoTerminado()) {finalizarJuego();}
    }

    private void finalizarJuego(){
        Intent intent= new Intent(JuegoActivity.this, ResultadoActivity.class);
        if (!controlador.verificarEmpate()){
            Jugador ganador= controlador.obtenerGanador();
            intent.putExtra("resultado","Ganador: "+ganador.getNombre());
            intent.putExtra("simboloGanador", ganador.getSimbolo().name());
            intent.putExtra("empate",controlador.verificarEmpate());
        }
        else {intent.putExtra("resultado","Empate");}

        startActivity(intent);
        finish();
    }

}