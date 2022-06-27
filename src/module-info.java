module SudokuGameOrganized {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens sudoku to javafx.graphics, javafx.fxml;
}
