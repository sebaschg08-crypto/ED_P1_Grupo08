package com.ed_p1_grupo_08.test_consola;

public class Jugador {
    private EstadoCelda simbolo;
    private String nombre;
    private boolean esComputadora;

    public Jugador(EstadoCelda simbolo, String nombre, boolean esComputadora) {
        this.simbolo = simbolo;
        this.nombre = nombre;
        this.esComputadora = esComputadora;
    }

    public EstadoCelda getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(EstadoCelda simbolo) {
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
