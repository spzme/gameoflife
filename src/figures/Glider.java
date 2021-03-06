package figures;

public class Glider extends Figure {
	
	public Glider() {
		rows = 3;
		columns = 3;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[2][0] = true;
			result[2][1] = true;
			result[2][2] = true;
			result[0][1] = true;
			result[1][2] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}

	@Override
	public String toString() {
		return "Glider (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}
}
