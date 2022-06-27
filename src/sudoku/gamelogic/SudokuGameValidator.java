package sudoku.gamelogic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sudoku.problemdomain.Coordinate;
import sudoku.problemdomain.SudokuGame;

public class SudokuGameValidator {
	
	static List<Integer> completeList = List.of(1,2,3,4,5,6,7,8,9);
	
	public static List<Integer> getNumbersInRow(int[][] grid, int rowNumber){
		
		return Arrays.stream(grid[rowNumber]).boxed().filter(x -> x!=0).toList();
	}
	
	public static List<Integer> getNumbersInCol(int[][] grid, int colNumber){
		return Arrays.stream(grid).map(x -> x[colNumber]).filter(x -> x!=0).toList();
	}
	
	public static List<Integer> getNumbersInQuad(int[][] grid, int row, int col){
		List<Integer> numbersInQuad = new ArrayList<Integer>();
		int quadY = row/3*3;
		int quadX = col/3*3;
		for (int i = quadY; i < quadY+3; i++) {
			for (int j = quadX; j < quadX+3; j++) {
				if (grid[i][j] != 0)
					numbersInQuad.add(grid[i][j]);
			}
		}
		
		return numbersInQuad;
	}
	
	// create copy of list and work with the copy so that we don't mutate the list accidentally
	// returns true if there is a duplicate
	public static boolean checkDuplicate(List<Integer> list) {
		
		List<Integer> copyOfList = List.copyOf(list);
		for (int i = 1; i <= SudokuGame.GRID_SIZE; i++) {
            if (Collections.frequency(copyOfList, i) > 1) 
            	return true;
        }		
		return false;
	}
	
	public static void removeZero(List<Integer> list) {		
		list.removeAll(Arrays.asList(0));
	}
	
	public static boolean equals(List<Integer> a, List<Integer> b) {
		if (a == null || b == null)
			return false;
		if (a == b)
			return true;
		for (Object o: a) {
			if (!b.contains(o))
				return false;
		}
		return true;
	}
	
	public static boolean isRowsComplete(int[][]grid) {		
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			List<Integer> numbersInRow = getNumbersInRow(grid,i);
			if (checkDuplicate(numbersInRow))
				return false;
			if (! equals(completeList, numbersInRow)) {
				return false;
			}			
		}
		return true;
	}
	
	public static boolean isColsComplete(int[][]grid) {
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			List<Integer> numbersInCol = getNumbersInCol(grid,i);
			if (checkDuplicate(numbersInCol))
				return false;
			if (! equals(completeList, numbersInCol)) {
				return false;
			}			
		}
		return true;
	}
	
	public static boolean isQuadsComplete(int[][]grid) {
		for (int i=0; i<SudokuGame.GRID_SIZE; i+=3) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j+=3) {
				List<Integer> numbersInQuad = getNumbersInQuad(grid,i,j);
				if (checkDuplicate(numbersInQuad))
					return false;				
				if (! equals(completeList, numbersInQuad)) {
					return false;
				}	
			}					
		}
		return true;
	}
	
	
	// returns true if the GridState of game is complete and valid.
	public static boolean isComplete(int[][] grid) {
		
		//int[][] grid = game.getGridState();
		return isRowsComplete(grid) &&
				isColsComplete(grid) &&
				isQuadsComplete(grid);
	}
	
	// returns list of valid numbers for the given coordinate i.e. no duplicates in row, column, and quadrant
	public static List<Integer> getValidNumbers(int[][]grid, Coordinate c) {
		List<Integer>copyOfCompleteList = new ArrayList<Integer>(completeList);
		//System.out.println(c.getX()+","+c.getY());
		List<Integer> numbersInRow = getNumbersInRow(grid,c.getX());
		//System.out.println(numbersInRow);
		copyOfCompleteList.removeAll(numbersInRow);
		List<Integer> numbersInCol = getNumbersInCol(grid,c.getY());
		//System.out.println(numbersInCol);
		copyOfCompleteList.removeAll(numbersInCol);
		List<Integer> numbersInQuad = getNumbersInQuad(grid,c.getX(),c.getY());
		//System.out.println(numbersInQuad);
		copyOfCompleteList.removeAll(numbersInQuad);
		return copyOfCompleteList;
	}
	

}
