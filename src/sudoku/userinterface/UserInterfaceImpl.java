package sudoku.userinterface;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sudoku.constants.Difficulty;
import sudoku.constants.GameState;
import sudoku.constants.Messages;
import sudoku.gamelogic.SudokuGameGenerator;
import sudoku.gamelogic.SudokuGameSolution;
import sudoku.gamelogic.SudokuGameValidator;
import sudoku.problemdomain.Coordinate;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract.EventListener;

public class UserInterfaceImpl implements IUserInterfaceContract.View, EventHandler<ActionEvent>{

	IUserInterfaceContract.EventListener listener;
	
	private Stage stage;
	private Group root;
	
	private final HashMap<Coordinate,TextField> textFieldMap;
	
	private final int WINDOW_WIDTH = 630;
	private final int WINDOW_HEIGHT = 700;
	private final int SQUARE_WIDTH = 70;
	private final int SQUARE_HEIGHT = 70;
	private final int BUTTON_HEIGHT = 50;
	private final int BUTTON_WIDTH = 100;
	
	public UserInterfaceImpl(Stage stage) {
		this.stage = stage;
		textFieldMap = new HashMap<>();
		
		root = new Group();
		buildUI(root);
		
	}

	private void buildUI(Group myGroup) {
		drawBackGround(myGroup);
		//drawBoard(myGroupRoot);
		drawLines(myGroup);
		drawToolBar(myGroup);
		drawTextFields(myGroup);
		stage.show();
		
	}

	private void drawTextFields(Group myGroup) {
		
		for (int x=0; x<SudokuGame.GRID_SIZE; x++) {
			for (int y=0; y<SudokuGame.GRID_SIZE; y++) {
				SudokuTextField textField = new SudokuTextField(x,y);
				textFieldStyling(textField);
				textFieldMap.put(new Coordinate(x,y), textField);
				myGroup.getChildren().add(textField);
			}
		}		
	}
	
	private void textFieldStyling(SudokuTextField textField) {
		int startX = 0;
		int startY = 70;
		int delta = 70;
		int x = textField.getX();
		int y = textField.getY();
		Font textFieldFont = new Font("Airal",25);
		
		textField.setFont(textFieldFont);
		textField.setAlignment(Pos.CENTER);
		
		textField.setPrefSize(SQUARE_WIDTH, SQUARE_HEIGHT);
		
		textField.setLayoutX(startX+delta*x);
		textField.setLayoutY(startY+delta*y);
		
		textField.setBackground(Background.EMPTY);
		
	}

	private void drawToolBar(Group myGroup) {
		ToolBar myToolBar = new ToolBar();
		
		
		
		// reset Button, check Button, save Button, solve Button		
		// Save Button Implementation
		Button saveButton = new Button("New");
		buttonStyling(saveButton);
		myToolBar.getItems().add(saveButton);
		
		// Reset Button Implementation
		Button resetButton = new Button("Reset");
		buttonStyling(resetButton);
		myToolBar.getItems().add(resetButton);
		
		// Check Button Implementation
		Button checkButton = new Button("Check");
		buttonStyling(checkButton);
		myToolBar.getItems().add(checkButton);
		
		// Solve Button Implementation
		Button solveButton = new Button("Solve");
		buttonStyling(solveButton);
		myToolBar.getItems().add(solveButton);
		
		
		myGroup.getChildren().add(myToolBar);		
	}
	
	private void buttonStyling(Button button) {
		Font buttonFont = new Font("Arial", 15);
		
		button.setFont(buttonFont);
		button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		button.setOnAction(this);		
	}

	private void drawLines(Group myGroup) {
		// Draw horizontal lines
		int startXh = 0;
	
		for (int startYh = 70; startYh<=WINDOW_HEIGHT; startYh+=SQUARE_HEIGHT) {
			Line line = new Line(startXh,startYh,WINDOW_WIDTH,startYh);
			// Make sure to setStrokeWidth = 3.0 for 3, 6 lines
			if ((startYh-70)/SQUARE_HEIGHT%3 == 0)
				line.setStrokeWidth(3.0);
			
			myGroup.getChildren().add(line);
		}

		// Draw vertical lines
		int startYv = 70;
		for (int startXv =0; startXv<=WINDOW_WIDTH; startXv+=SQUARE_WIDTH) {
			Line line = new Line (startXv,startYv,startXv,WINDOW_HEIGHT);
			// Make sure to setStorkeWidth = 3.0 for 3, 6 lines
			if (startXv/SQUARE_WIDTH%3 ==0)
				line.setStrokeWidth(3.0);
			
			myGroup.getChildren().add(line);
		}
		
		
		
	}
	
	/*
	private void drawBoard(Group myGroupRoot) {
		Rectangle rect = new Rectangle(0,70,WINDOW_WIDTH,WINDOW_HEIGHT);
		rect.setStrokeWidth(3);
		
	}
	*/

	private void drawBackGround(Group myGroup) {
		Scene scene = new Scene(myGroup,WINDOW_WIDTH,WINDOW_HEIGHT);
		scene.setFill(Color.WHITE);
		stage.setScene(scene);
		
	}

	@Override
	public void updateBoard(SudokuGame game) {
		int[][] grid = game.getGridState();
		for (int x=0; x<SudokuGame.GRID_SIZE; x++) {
			for (int y=0; y<SudokuGame.GRID_SIZE; y++) {
				int value = grid[x][y];
				TextField textField = textFieldMap.get(new Coordinate(x,y));
				if (value==0)
					textField.setText("");
				else
					textField.setText(value+"");
				
				// if we are populating new game, set the opacity of values generated by 'new game' at 0.7
				// user values will be of opacity 1.0
				// this is so that users can distinguish their inputs from starting values of new game
				if (game.getGameState()==GameState.NEW) {
					if (value!=0) {
						textField.setOpacity(0.7);
						textField.setEditable(false);
					} else {
						textField.setOpacity(1.0);
						textField.setEditable(true);
					}
				}
			}
		}
	}

	@Override
	public void updateCell(int x, int y, int value) {
		TextField textField = textFieldMap.get(new Coordinate(x,y));
		if (value==0)
			textField.setText("");
		else
			textField.setText(value+"");
	}

	@Override
	public void setListener(IUserInterfaceContract.EventListener l) {
		this.listener=l;
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource().getClass() == Button.class) {
			
			if (((Button)e.getSource()).getText() == "Check") {
				String msg = SudokuGameValidator.isComplete(getCurrentBoard()) ? Messages.GAME_COMPLETE:"INVALID GRID";
				Alert alert = new Alert(AlertType.INFORMATION, msg);
				alert.showAndWait();
			}
			
			if (((Button)e.getSource()).getText() == "Reset") {
				for (Coordinate c: textFieldMap.keySet()) {
					TextField textField =textFieldMap.get(c); 
					if (textField.isEditable())
						textField.setText("");
				}
			}
			
			if (((Button)e.getSource()).getText() == "Solve"){
				System.out.println(SudokuGameSolution.getSolution(getInitialBoard()));
			}
			
			if (((Button)e.getSource()).getText() == "New"){
				updateBoard(SudokuGameGenerator.generateNewSudokuGame(Difficulty.NORMAL));
			}
		}
	}

	@Override
	public int[][] getCurrentBoard() {
		int[][] currentGrid = new int[SudokuGame.GRID_SIZE][SudokuGame.GRID_SIZE];
		for (Coordinate c: textFieldMap.keySet()) {
			int x = c.getX();
			int y = c.getY();
			String stringValue = textFieldMap.get(c).getText();
			int value;
			if (stringValue=="")
				value = 0;
			else
				value = Integer.parseInt(stringValue);
			currentGrid[x][y]=value;
		}
		System.out.println("");
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				System.out.print(currentGrid[j][i] + " ");
			}
			System.out.println("0");
		}
		System.out.println("");
		return currentGrid;
	}
	
	@Override
	public int[][] getInitialBoard() {
		int[][] currentGrid = new int[SudokuGame.GRID_SIZE][SudokuGame.GRID_SIZE];
		for (Coordinate c: textFieldMap.keySet()) {
			int x = c.getX();
			int y = c.getY();
			if (! textFieldMap.get(c).isEditable()) {
				String stringValue = textFieldMap.get(c).getText();
				int value;
				if (stringValue=="")
					value = 0;
				else
					value = Integer.parseInt(stringValue);
				currentGrid[x][y]=value;
			} else {
				currentGrid[x][y]=0;
			}
				
			
		}
		System.out.println("");
		for (int i=0; i<SudokuGame.GRID_SIZE; i++) {
			for (int j=0; j<SudokuGame.GRID_SIZE; j++) {
				System.out.print(currentGrid[j][i] + " ");
			}
			System.out.println("0");
		}
		System.out.println("");
		return currentGrid;
	}
	

}
