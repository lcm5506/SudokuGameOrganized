package sudoku.gamelogic;

import java.util.List;

import sudoku.problemdomain.Coordinate;
import sudoku.problemdomain.SudokuGame;

public class SudokuGameSolution {
	public static boolean getSolution(int[][] grid){
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				if (grid[i][j] == 0) {
					List<Integer> validNumbers = SudokuGameValidator.getValidNumbers(grid, new Coordinate(i,j));
					for (Integer myInt: validNumbers) {
						grid[i][j] = myInt;
						if (getSolution(grid))
							return true;
						else
							grid[i][j] = 0;
					}
					return false;
					
				}
				
				
			}
		}
		System.out.println("");
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				System.out.print(grid[j][i] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		return true;
	}

}
