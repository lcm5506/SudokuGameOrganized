package sudoku.gamelogic;

import sudoku.problemdomain.SudokuGame;

public class SudokuUtilities {
	
	public static int[][] copyOf(int[][] gridState) {
		int[][] newGridState = new int[SudokuGame.GRID_SIZE][SudokuGame.GRID_SIZE];
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				newGridState[i][j] = gridState[i][j];
			}
		}
		
		return newGridState;
	}

}
