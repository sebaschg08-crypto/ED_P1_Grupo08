package com.ed_p1_grupo_08.tres_en_raya;

import java.util.List;
import java.util.ArrayList;

public class Tablero {

    private EstadoCelda[] celdas;
    private int ocupadas;

    public Tablero(){
        celdas= new EstadoCelda[9];
        for (int i= 0; i<celdas.length; i++) {
            celdas[i] = EstadoCelda.VACIO;
        }
        ocupadas = 0;
    }

    public EstadoCelda[] getCeldas(){return this.celdas;}
    public Tablero(EstadoCelda[] c, int o){ // para clonar tablero
        this.celdas = c.clone();
        this.ocupadas = o;
    }
    public boolean isFull(){return ocupadas==9;}

    public Tablero clonarTablero(){return new Tablero(this.celdas, this.ocupadas);}

    public List<Tablero> generarSucesores(Jugador j){
            List<Tablero> resultado = new ArrayList<>();
            if (!isFull()){ //se puede agregar
                EstadoCelda c = j.getSimbolo();

                for (int i= 0; i< celdas.length; i++){
                    Tablero clon= clonarTablero();
                    if (celdas[i]==EstadoCelda.VACIO){clon.getCeldas()[i]= c;}
                }
            }

            return resultado;
    }

}
