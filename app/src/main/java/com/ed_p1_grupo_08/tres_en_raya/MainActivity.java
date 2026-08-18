package com.ed_p1_grupo_08.tres_en_raya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EstadoCelda simboloHumano = EstadoCelda.X;
    private boolean empiezaHumano = true;
    private int modoJuego = 2;

    private int colorActivo = Color.parseColor("#FF6200EE");
    private int colorInactivo = Color.LTGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtElegirSimbolo = findViewById(R.id.ElegirSimbolo);

        Button btnX = findViewById(R.id.btnX);
        Button btnO = findViewById(R.id.btnO);

        Button btnHumano = findViewById(R.id.btnHumano);
        Button btnComputador = findViewById(R.id.btnComputador);

        Button btnHvsH = findViewById(R.id.btnHvsH);
        Button btnHvsPC = findViewById(R.id.btnHvsPC);
        Button btnPCvsPC = findViewById(R.id.btnPCvsPC);

        Button btnJugar = findViewById(R.id.btnJugar);
        Button btnCargar = findViewById(R.id.btnCargar);

        // ESTADO INICIAL
        actualizarGrupoDosBotones(btnX, btnO, true);
        actualizarGrupoDosBotones(btnHumano, btnComputador, true);
        actualizarGrupoTresBotones(btnHvsH, btnHvsPC, btnPCvsPC, 2);
        actualizarTextosSegunModo(2, txtElegirSimbolo, btnHumano, btnComputador);

        // EVENTOS
        btnX.setOnClickListener(v -> {
            simboloHumano = EstadoCelda.X;
            actualizarGrupoDosBotones(btnX, btnO, true);
        });
        btnO.setOnClickListener(v -> {
            simboloHumano = EstadoCelda.O;
            actualizarGrupoDosBotones(btnX, btnO, false);
        });

        btnHumano.setOnClickListener(view -> {
            empiezaHumano = true;
            actualizarGrupoDosBotones(btnHumano, btnComputador, true);
        });
        btnComputador.setOnClickListener(view -> {
            empiezaHumano = false;
            actualizarGrupoDosBotones(btnHumano, btnComputador, false);
        });

        btnHvsH.setOnClickListener(v -> {
            modoJuego = 1;
            actualizarGrupoTresBotones(btnHvsH, btnHvsPC, btnPCvsPC, 1);
            actualizarTextosSegunModo(1, txtElegirSimbolo, btnHumano, btnComputador);
        });
        btnHvsPC.setOnClickListener(v -> {
            modoJuego = 2;
            actualizarGrupoTresBotones(btnHvsH, btnHvsPC, btnPCvsPC, 2);
            actualizarTextosSegunModo(2, txtElegirSimbolo, btnHumano, btnComputador);
        });
        btnPCvsPC.setOnClickListener(v -> {
            modoJuego = 3;
            actualizarGrupoTresBotones(btnHvsH, btnHvsPC, btnPCvsPC, 3);
            actualizarTextosSegunModo(3, txtElegirSimbolo, btnHumano, btnComputador);
        });

        // ACCIÓN JUGAR NUEVA PARTIDA
        btnJugar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
            intent.putExtra("simboloHumano", simboloHumano.name());
            intent.putExtra("empiezaHumano", empiezaHumano);
            intent.putExtra("modoJuego", modoJuego);
            startActivity(intent);
        });

        // ACCIÓN CARGAR PARTIDA GUARDADA
        btnCargar.setOnClickListener(v -> {
            // Abrimos la memoria interna de la app
            SharedPreferences prefs = getSharedPreferences("TresEnRayaDatos", MODE_PRIVATE);
            String estadoGuardado = prefs.getString("partidaGuardada", null);

            if (estadoGuardado != null) {
                // Si existe una partida, la enviamos al JuegoActivity
                Intent intent = new Intent(MainActivity.this, JuegoActivity.class);
                intent.putExtra("estadoGuardado", estadoGuardado);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No hay ninguna partida guardada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // MÉTODOS AUXILIARES
    private void actualizarTextosSegunModo(int modo, TextView txtSimbolo, Button btnHumano, Button btnComputador) {
        if (modo == 1) {
            txtSimbolo.setText("Símbolo de Jugador 1:");
            btnComputador.setText("Jugador 1");
            btnHumano.setText("Jugador 2");
        } else if (modo == 2) {
            txtSimbolo.setText("Elige tu símbolo:");
            btnComputador.setText("Computadora");
            btnHumano.setText("Usted");
        } else if (modo == 3) {
            txtSimbolo.setText("Símbolo de PC 1:");
            btnComputador.setText("PC 1");
            btnHumano.setText("PC 2");
        }
    }

    private void actualizarGrupoDosBotones(Button b1, Button b2, boolean seleccionPrimero) {
        if (seleccionPrimero) {
            b1.setBackgroundColor(colorActivo);
            b2.setBackgroundColor(colorInactivo);
        } else {
            b1.setBackgroundColor(colorInactivo);
            b2.setBackgroundColor(colorActivo);
        }
    }

    private void actualizarGrupoTresBotones(Button b1, Button b2, Button b3, int opcionActiva) {
        b1.setBackgroundColor(colorInactivo);
        b2.setBackgroundColor(colorInactivo);
        b3.setBackgroundColor(colorInactivo);

        if (opcionActiva == 1) b1.setBackgroundColor(colorActivo);
        else if (opcionActiva == 2) b2.setBackgroundColor(colorActivo);
        else if (opcionActiva == 3) b3.setBackgroundColor(colorActivo);
    }
}