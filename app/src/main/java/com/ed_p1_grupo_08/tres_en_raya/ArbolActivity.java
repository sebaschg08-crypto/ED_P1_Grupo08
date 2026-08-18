package com.ed_p1_grupo_08.tres_en_raya;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArbolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arbol);

        TextView arbolTexto = findViewById(R.id.arbolGenerado);
    }
}
