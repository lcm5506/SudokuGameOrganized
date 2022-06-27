package sudoku.problemdomain;

import sudoku.constants.GameState;
import sudoku.gamelogic.SudokuUtilities;

public class SudokuGame {
	
	// gridState[i][j]  where i is the index of row and j is the index of column
	// gridState is array of arrays of row
	// gridState = {{row1: 1,2,3,4,5,6,7,8,9} {row2: 1,2,3,4,5,6,7,8,9} ....}
	
	// gridState[i][j] = 0 if it's empty or haven't been assigned any values yet.
	
	private int[][] gridState;
	private final int[][] initialGridState;
	private GameState gameState;
	
	public static final int GRID_SIZE = 9;
	
	public SudokuGame(int[][] gridState, GameState gameState) {
		this.gridState = gridState;
		this.initialGridState = gridState;
		this.gameState = gameState;
	}

	public int[][] getGridState() {
		return SudokuUtilities.copyOf(gridState);
	}

	public int[][] getInitialGridState() {
		return SudokuUtilities.copyOf(initialGridState);
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGridState(int[][] gridState) {
		this.gridState = gridState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
		
}
