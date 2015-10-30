package figures;

public class Pulsar extends Figure {
	
	public Pulsar() {
		this.rows = 15;
		this.columns = 15;
	}

	@Override
	public boolean[][] getField(int columns, int rows) {
		boolean[][] result = new boolean[columns][rows];
		if (rows >= this.rows && columns >= this.columns) {
			result[3][1] = true;
			result[4][1] = true;
			result[5][1] = true;
			result[9][1] = true;
			result[10][1] = true;
			result[11][1] = true;
			
			result[1][3] = true;
			result[1][4] = true;
			result[1][5] = true;
			result[6][3] = true;
			result[6][4] = true;
			result[6][5] = true;
			result[8][3] = true;
			result[8][4] = true;
			result[8][5] = true;
			result[13][3] = true;
			result[13][4] = true;
			result[13][5] = true;
			
			result[3][6] = true;
			result[4][6] = true;
			result[5][6] = true;
			result[9][6] = true;
			result[10][6] = true;
			result[11][6] = true;
			
			result[3][8] = true;
			result[4][8] = true;
			result[5][8] = true;
			result[9][8] = true;
			result[10][8] = true;
			result[11][8] = true;
			
			result[1][9] = true;
			result[1][10] = true;
			result[1][11] = true;
			result[6][9] = true;
			result[6][10] = true;
			result[6][11] = true;
			result[8][9] = true;
			result[8][10] = true;
			result[8][11] = true;
			result[13][9] = true;
			result[13][10] = true;
			result[13][11] = true;
			
			result[3][13] = true;
			result[4][13] = true;
			result[5][13] = true;
			result[9][13] = true;
			result[10][13] = true;
			result[11][13] = true;
			System.out.println("Done drawing " + this.toString());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Pulsar (" + String.valueOf(columns) + "X" + String.valueOf(rows) + ")";
	}

}
