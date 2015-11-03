package figures;

public class None extends Figure {

	public None() {
		rows = 0;
		columns = 0;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		return new boolean[columns][rows];
	}

	@Override
	public String toString() {
		return "None";
	}
}
