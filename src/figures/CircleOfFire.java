package figures;

public class CircleOfFire extends Figure {
	
	public CircleOfFire() {
		rows = 11;
		columns = 11;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[5][6] = true;
			result[5][4] = true;
			result[4][5] = true;
			result[6][5] = true;
			result[3][5] = true;
			result[7][5] = true;
			result[2][5] = true;
			result[8][5] = true;
			result[1][5] = true;
			result[9][5] = true;
			result[0][5] = true;
			result[10][5] = true;
			result[5][3] = true;
			result[5][7] = true;
			result[5][2] = true;
			result[5][8] = true;
			result[5][1] = true;
			result[5][9] = true;
			result[4][10] = true;
			result[6][10] = true;
			result[4][0] = true;
			result[6][0] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}
	
	public String toString() {
		return "Circle of fire (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}

}
