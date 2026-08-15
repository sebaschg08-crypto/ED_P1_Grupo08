package com.ed_p1_grupo_08.tres_en_raya;

public class Tablero {

    int[] celdas;
    int ocupadas;

    public Tablero(){
        celdas= new int[9];
        ocupadas= 0;
    }
    public Tablero(int[] c, int o){ // para clonar tablero
        celdas= c;
        ocupadas= o;
    }
    public boolean isFull(){return ocupadas==9;}

    public Tablero clonarTablero(){return new Tablero(this.celdas, this.ocupadas);}


}
