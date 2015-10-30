package figures;

public class Blinker extends Figure {

	public Blinker() {
		rows = 3;
		columns = 3;
	}
	
	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[1][1] = true;
			result[0][1] = true;
			result[2][1] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Blinker (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}

}
