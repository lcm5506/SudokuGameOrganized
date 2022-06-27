package sudoku.problemdomain;

import java.util.Objects;

public class Coordinate {
	
	private final int x;
	private final int y;
	
	public Coordinate(int x, int y) {		
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object a) {
		if (this == a) return true;
		if (a == null || getClass() != a.getClass()) return false;
		
		Coordinate b = (Coordinate) a;
		
		return (b.getX() == this.x && 
				b.getY() == this.y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
