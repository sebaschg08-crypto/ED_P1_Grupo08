package com.ed_p1_grupo_08.tres_en_raya;
import java.util.List;
public class MinimaxAI {
    // no instancia de calculadora
    private Jugador jugadorPC;
    private Jugador oponente;

    public MinimaxAI(Jugador jugadorPC, Jugador oponente) {
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
            int minUtilidadFamilia;

            // evaluamos si gano la pc o si esta llena
            if (jugadaPC.hayGanador(jugadorPC.getSimbolo()) || jugadaPC.isFull()){
                minUtilidadFamilia = CalculadoraUtilidad.evaluar(jugadaPC, jugadorPC, oponente);

            } else {
                List<Tablero> posiblesJugadasOponente = jugadaPC.generarSucesores(oponente);
                minUtilidadFamilia = Integer.MAX_VALUE;

                for (Tablero jugadaOponente : posiblesJugadasOponente) {
                    TreeNode<Tablero> nodoOponente = new TreeNode<>(jugadaOponente);

                    int utilidad = CalculadoraUtilidad.evaluar(jugadaOponente, jugadorPC, oponente);
                    nodoOponente.setUtilidad(utilidad);
                    nodoPC.addHijo(nodoOponente);

                    if (utilidad < minUtilidadFamilia) {
                        minUtilidadFamilia = utilidad;
                    }
                }
            }

            nodoPC.setUtilidad(minUtilidadFamilia);

            if (minUtilidadFamilia > maxUtilidadMinima) {
                maxUtilidadMinima = minUtilidadFamilia;
                mejorJugada = jugadaPC;
            }
        }

        return mejorJugada;
    }
}
