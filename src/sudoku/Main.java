package sudoku;
	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.constants.Difficulty;
import sudoku.gamelogic.SudokuGameGenerator;
import sudoku.gamelogic.SudokuGameValidator;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.UserInterfaceImpl;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			UserInterfaceImpl ui = new UserInterfaceImpl(primaryStage);
			SudokuGame newGame = SudokuGameGenerator.generateNewSudokuGame(Difficulty.HARD);
			ui.updateBoard(newGame);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
