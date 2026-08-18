package com.ed_p1_grupo_08.tres_en_raya;

import java.util.List;
import java.util.ArrayList;

public class Tablero {

    private EstadoCelda[] celdas;
    private int ocupadas;
    private static final int[][] lineas= {
            // se almacenan las posibles filas, columnas y diagonales del tablero
            {0,1,2}, // fila 1
            {3,4,5}, // fila 2
            {6,7,8}, // fila 3

            {0,3,6}, // columna 1
            {1,4,7}, // columna 2
            {2,5,8}, // columna 3

            {0,4,8}, // diagonal 1
            {2,4,6} // diagonal 2
    };

    public Tablero(){
        celdas= new EstadoCelda[9];
        for (int i= 0; i<celdas.length; i++) {
            celdas[i] = EstadoCelda.VACIO;
        }
        ocupadas = 0;
    }

    public Tablero(EstadoCelda[] c, int o){ // para clonar tablero
        this.celdas = c.clone();
        this.ocupadas = o;
    }
    public boolean isFull(){return ocupadas==9;}

    public boolean colocarFicha(int pos, EstadoCelda sim){
        if (pos<0 || pos>= celdas.length || isFull()) return false;
        if (celdas[pos]==EstadoCelda.VACIO) {celdas[pos]= sim; ocupadas++; return true;}
        return false;
    }

    public Tablero clonarTablero(){return new Tablero(this.celdas, this.ocupadas);}

    public List<Tablero> generarSucesores(Jugador j){
            List<Tablero> resultado = new ArrayList<>();
            if (!isFull()){ //se puede agregar
                EstadoCelda c = j.getSimbolo();

                for (int i= 0; i< celdas.length; i++){
                    Tablero clon= clonarTablero();
                    if (clon.colocarFicha(i,c)) {resultado.add(clon);}

                }
            }
            return resultado;
    }

    public int contarLineasDisponibles(EstadoCelda simbolo){
        int contar= 0;
        EstadoCelda oponente= simbolo==EstadoCelda.O ? EstadoCelda.X : EstadoCelda.O;
        // evaluar que cada posible linea no este ocupada por el oponente
        for (int[] linea : lineas){
            if (celdas[linea[0]]!=oponente && celdas[linea[1]]!=oponente && celdas[linea[2]]!=oponente) contar++;
        }
        return contar;
    }

    public boolean hayGanador(EstadoCelda jugador){
        // se gana si un jugador hace alguna linea
        boolean ganador= false;
        int i=0;
        while (!ganador && i < lineas.length) {
            int[] linea = lineas[i];
            if (celdas[linea[0]]==jugador && celdas[linea[1]]==jugador && celdas[linea[2]]==jugador) ganador=true;
            i++;
        }
        return ganador;
    }
    public void imprimirConsola() {
        System.out.println();
        for (int i = 0; i < celdas.length; i++) {
            String simbolo = (celdas[i] == EstadoCelda.VACIO) ? String.valueOf(i) : celdas[i].toString();
            System.out.print(" " + simbolo + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
                if (i < 8) System.out.println("---+---+---");
            } else {
                System.out.print("|");
            }
        }
        System.out.println();
    }
    public EstadoCelda[] getCeldas() {
        return celdas;
    }

    public int getOcupadas() {
        return ocupadas;
    }
    public String comoString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < celdas.length; i++) {
            String simbolo = (celdas[i] == EstadoCelda.VACIO) ? "-" : celdas[i].toString();
            sb.append(" ").append(simbolo).append(" ");

            if ((i + 1) % 3 == 0) {
                sb.append("\n");
            } else {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
