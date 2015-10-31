package figures;

public class GliderGun extends Figure {
	
	public GliderGun() {
		rows = 11;
		columns = 36;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[0][4] = true;
			result[0][5] = true;
			result[1][4] = true;
			result[1][5] = true;
			result[10][4] = true;
			result[10][5] = true;
			result[10][6] = true;
			result[11][3] = true;
			result[11][7] = true;
			result[12][2] = true;
			result[12][8] = true;
			result[13][2] = true;
			result[13][8] = true;
			result[14][5] = true;
			result[15][3] = true;
			result[15][7] = true;
			result[16][5] = true;
			result[17][5] = true;
			result[16][4] = true;
			result[16][6] = true;
			result[20][4] = true;
			result[20][3] = true;
			result[20][2] = true;
			result[21][4] = true;
			result[21][3] = true;
			result[21][2] = true;
			result[22][1] = true;
			result[22][5] = true;
			result[24][1] = true;
			result[24][5] = true;
			result[24][0] = true;
			result[24][6] = true;
			result[34][2] = true;
			result[34][3] = true;
			result[35][2] = true;
			result[35][3] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "GliderGun (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}

}
