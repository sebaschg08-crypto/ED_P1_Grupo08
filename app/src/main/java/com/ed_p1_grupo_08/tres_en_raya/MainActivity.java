package com.ed_p1_grupo_08.tres_en_raya;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EstadoCelda simboloHumano;
    private boolean empiezaHumano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reconocer botones pantalla principal
        Button btnX= findViewById(R.id.btnX);
        Button btnO= findViewById(R.id.btnO);
        Button btnHumano= findViewById(R.id.btnHumano);
        Button btnComputador= findViewById(R.id.btnComputador);
        Button btnJugar= findViewById(R.id.btnJugar);

        btnX.setOnClickListener(v ->{simboloHumano= EstadoCelda.X;});
        btnO.setOnClickListener(v ->{simboloHumano= EstadoCelda.O;});

        btnHumano.setOnClickListener(view -> {empiezaHumano=true;});
        btnComputador.setOnClickListener(view -> {empiezaHumano=false;});

        btnJugar.setOnClickListener(view -> {
            if (simboloHumano==null){
                Toast.makeText(this, "Selecciona un simbolo", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, JuegoActivity.class);

            intent.putExtra("simboloHumano", simboloHumano.name());
            intent.putExtra("empiezaHumano",empiezaHumano);

            startActivity(intent);

        });

    }
}