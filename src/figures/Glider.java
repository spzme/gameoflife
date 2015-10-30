package figures;

public class Glider extends Figure {

	public boolean[][] getField(int rows, int columns) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= 3 && columns >= 3) {
			result[2][0] = true;
			result[2][1] = true;
			result[2][2] = true;
			result[0][1] = true;
			result[1][2] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}

	public String toString() {
		return "Glider";
	}
}
