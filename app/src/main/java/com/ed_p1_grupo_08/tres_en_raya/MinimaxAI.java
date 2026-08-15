package com.ed_p1_grupo_08.tres_en_raya;
import java.util.List;
public class MinimaxAI {
    private CalculadoraUtilidad calculadora;
    private Jugador jugadorPC;
    private Jugador oponente;

    public MinimaxAI(Jugador jugadorPC, Jugador oponente) {
        this.calculadora = new CalculadoraUtilidad();
        this.jugadorPC = jugadorPC;
        this.oponente = oponente;
    }

    public Tablero obtenerMejorJugada(Tablero tableroActual) {
        Tree<Tablero> arbol = new Tree<>(tableroActual);

        List<Tablero> posiblesJugadasPC = tableroActual.generarSucesores(jugadorPC);

        Tablero mejorJugada = null;
        int maxUtilidadMinima = Integer.MIN_VALUE;

        for (Tablero jugadaPC : posiblesJugadasPC) {
            TreeNode<Tablero> nodoPC = new TreeNode<>(jugadaPC);
            arbol.getRaiz().addHijo(nodoPC);

            List<Tablero> posiblesJugadasOponente = jugadaPC.generarSucesores(oponente);

            int minUtilidadFamilia = Integer.MAX_VALUE;


    }
}
