package com.ed_p1_grupo_08.tres_en_raya;

public class Tablero {

    EstadoCelda[] celdas;
    int ocupadas;

    public Tablero(){
        celdas= new EstadoCelda[9];
        for (int i= 0; i<celdas.length; i++) {
            celdas[i] = EstadoCelda.VACIO;
            ocupadas = 0;
        }
    }
    public Tablero(EstadoCelda[] c, int o){ // para clonar tablero
        this.celdas = c.clone();
        this.ocupadas = o;
    }
    public boolean isFull(){return ocupadas==9;}

    public Tablero clonarTablero(){return new Tablero(this.celdas, this.ocupadas);}


}
