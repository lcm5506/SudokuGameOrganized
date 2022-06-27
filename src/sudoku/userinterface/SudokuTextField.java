package sudoku.userinterface;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class SudokuTextField extends TextField{
	private final int x;
	private final int y;
	

	public SudokuTextField(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.setText("");
		UnaryOperator<Change> integerFilter = change -> {
			String input = change.getControlNewText();
			
			return (input.matches("[1-9]{0,1}")) ? change:null;
		};
		this.setTextFormatter(new TextFormatter<String>(integerFilter));
		
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	
	
	

}
