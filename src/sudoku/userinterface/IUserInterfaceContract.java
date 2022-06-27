package sudoku.userinterface;

import sudoku.problemdomain.SudokuGame;

public interface IUserInterfaceContract {
	interface View {
		void updateBoard(SudokuGame game);
		void updateCell(int x, int y, int value);
		void setListener(IUserInterfaceContract.EventListener l);
		int[][] getCurrentBoard();
		int[][] getInitialBoard();
		
	}
	
	interface EventListener {
		void onSudokuInput(int x, int y, int value);
		void onSaveClick();
		void onResetClick();
		void onSolveClick(int[][] gridState);
		void onDialogClick();
		
	}

}
