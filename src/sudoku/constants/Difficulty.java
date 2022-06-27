package sudoku.constants;

public enum Difficulty {
	EASY(40),
	NORMAL(50),
	HARD(60),
	VERY_HARD(70),
	HARD_CORE(80);
	
	public final int difficulty;
	
	private Difficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
