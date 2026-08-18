package com.ed_p1_grupo_08.tres_en_raya;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AnalisisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis);

        TextView analisisTexto = findViewById(R.id.analisisJugada);

        if (MinimaxAI.ultimoArbolGenerado != null && MinimaxAI.ultimoArbolGenerado.getRaiz() != null) {

            StringBuilder reporte = new StringBuilder();
            reporte.append("La computadora analizó las siguientes opciones:\n\n");

            List<TreeNode<Tablero>> opciones = MinimaxAI.ultimoArbolGenerado.getRaiz().getHijos();

            if (opciones.isEmpty()) {
                reporte.append("No hubo opciones que evaluar.");
            } else {
                for (int i = 0; i < opciones.size(); i++) {
                    TreeNode<Tablero> nodo = opciones.get(i);
                    reporte.append("▶ Opción ").append(i + 1).append(":\n");

                    reporte.append("Puntaje (Utilidad): ").append(nodo.getUtilidad()).append("\n");

                    reporte.append(nodo.getDato().comoString()).append("\n");
                }
            }

            analisisTexto.setText(reporte.toString());

        } else {
            analisisTexto.setText("Aún no hay análisis disponible.\n" +
                    "Deja que la computadora haga al menos un movimiento para analizar sus decisiones.");
        }
    }
}