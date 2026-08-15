package com.ed_p1_grupo_08.tres_en_raya;

public class Jugador {
    private char simbolo;
    private String nombre;
    private boolean esComputadora;

    public Jugador(char simbolo, String nombre, boolean esComputadora) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.esComputadora = esComputadora;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsComputadora() {
        return esComputadora;
    }

    public void setEsComputadora(boolean esComputadora) {
        this.esComputadora = esComputadora;
    }
}
