package com.ed_p1_grupo_08.tres_en_raya;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private TextView resumen;
    private TextView txtTableroFinal; // Nueva variable
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        boolean empate = getIntent().getBooleanExtra("empate", false);
        String resultado = getIntent().getStringExtra("resultado");

        String tableroF = getIntent().getStringExtra("tableroFinal");

        resumen = findViewById(R.id.resultado);
        txtTableroFinal = findViewById(R.id.tableroFinal);
        btnRegresar = findViewById(R.id.regresar);

        if (!empate) {
            String simboloGanador = getIntent().getStringExtra("simboloGanador");
            resumen.setText(resultado + " (" + simboloGanador + ")");
        } else {
            resumen.setText(resultado);
        }

        if (tableroF != null) {
            txtTableroFinal.setText(tableroF);
        }

        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}