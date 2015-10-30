package figures;

public class Toad extends Figure {
	
	public Toad() {
		this.rows = 4;
		this.columns = 4;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[0][2] = true;
			result[1][2] = true;
			result[2][2] = true;
			result[1][1] = true;
			result[2][1] = true;
			result[3][1] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Toad (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}

}
