package com.ed_p1_grupo_08.tres_en_raya;


public class UtiliyCalculator {
  public static int calculateP(Tablero tablero, char Jugador) {
    char opponent = ( Jugador == 'X') ? 'O' : 'X' ;
    char[][] matrix = Tablero.getMatrix();
    int pCount = 0;
