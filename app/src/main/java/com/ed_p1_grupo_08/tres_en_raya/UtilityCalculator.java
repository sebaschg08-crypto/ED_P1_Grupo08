package com.ed_p1_grupo_08.tres_en_raya;


public class UtiliyCalculator {
  public static int calculateP(Tablero tablero, char Jugador) { 
    char opponent = ( Jugador == 'X') ? 'O' : 'X' ;
    char[][] matrix = Tablero.getMatrix();
    int pCount = 0;

    for (int r = 0; r < 3; r++) {
      if (matrix[r][0] != opponent && matrix[r][1] != opponent && matrix[r][2] != opponent ) {
        pCount++;
      }
    } // evaluar 3 filas

    for (int c = 0; c < 3; c++) {  
      if (matrix[0][c] != opponent && matrix[1][c] != opponent && matrix[2][c] != opponent ) {
        pCount++;
      }
    }  //evaluar 3 columnas
    
     if (matrix[0][0] != opponent  && matrix[1][1] != opponent && matrix[2][2] != opponent ) {
       pCount++;
     }

    if (matrix[0][2] != opponent  && matrix[1][1] != opponent && matrix[2][0] != opponent ) {
       pCount++;
    }

    return pCount;
  }

  public static int calculateUtility
