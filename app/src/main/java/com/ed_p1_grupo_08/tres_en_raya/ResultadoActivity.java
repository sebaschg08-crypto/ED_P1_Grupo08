package com.ed_p1_grupo_08.tres_en_raya;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private TextView resumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        boolean empate = getIntent().getBooleanExtra("empate", false);
        String resultado = getIntent().getStringExtra("resultado");

        resumen = findViewById(R.id.resultado);

        if (!empate) {
            String simboloGanador = getIntent().getStringExtra("simboloGanador");
            resumen.setText(resultado + " (" + simboloGanador + ")");
        } else {
            resumen.setText(resultado);
        }
    }
}