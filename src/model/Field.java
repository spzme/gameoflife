package model;

public class Field {
	private boolean[][] cells;
	private int rows;
	private int columns;
	private int aliveCellCount;

	public Field(int columns, int rows) {
		this.rows = rows;
		this.columns = columns;
		cells = new boolean[columns][rows];
	}

	public void setAlive(int x, int y, boolean live) {
		if (live) {
			setAlive(x, y);
		} else {
			setDead(x, y);
		}
	}

	public void setAlive(int x, int y) {
		if (cells[x][y] == false) {
			aliveCellCount++;
		}
		cells[x][y] = true;
	}

	public void setDead(int x, int y) {
		if (cells[x][y] == true) {
			aliveCellCount--;
		}
		cells[x][y] = false;
	}

	public boolean getAliveState(int x, int y) {
		return cells[x][y];
	}

	public int getRowCount() {
		return rows;
	}

	public int getColumnCount() {
		return columns;
	}

	public void printField() {
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < rows; m++) {
			for (int n = 0; n < columns; n++) {
				sb.append(cells[n][m] ? "1" : "0");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public int getAliveCellCount() {
		return aliveCellCount;
	}

	public void shiftLeft(int amount) {
		shift(-amount, 0);
	}

	public void shiftRight(int amount) {
		shift(amount, 0);
	}

	public void shiftUp(int amount) {
		shift(0, amount);
	}

	public void shiftDown(int amount) {
		shift(0, -amount);
	}

	private void shift(int xAmount, int yAmount) {
		boolean[][] result = new boolean[columns][rows];
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				if (x + xAmount >= columns || x + xAmount < 0
						|| y + yAmount >= rows || y + yAmount < 0) {
					result[x][y] = false;
				} else {
					result[x][y] = cells[x + xAmount][y + yAmount];
				}
			}
		}
		cells = result;
	}
}
