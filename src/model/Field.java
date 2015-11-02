package model;

import java.util.ArrayList;

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

	public ArrayList<Tuple> shiftLeft(int amount) {
		return shift(-amount, 0);
	}

	public ArrayList<Tuple> shiftRight(int amount) {
		return shift(amount, 0);
	}

	public ArrayList<Tuple> shiftUp(int amount) {
		return shift(0, amount);
	}

	public ArrayList<Tuple> shiftDown(int amount) {
		return shift(0, -amount);
	}

	private ArrayList<Tuple> shift(int xAmount, int yAmount) {
		boolean[][] result = new boolean[columns][rows];
		ArrayList<Tuple> differences = new ArrayList<Tuple>();
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				boolean oldState = cells[x][y];
				if (x + xAmount >= columns || x + xAmount < 0
						|| y + yAmount >= rows || y + yAmount < 0) {
					result[x][y] = false;
				} else {
					result[x][y] = cells[x + xAmount][y + yAmount];
				}
				if(oldState != result[x][y]){
					differences.add(new Tuple(x,y));
				}
			}
		}
		cells = result;
		return differences;
	}
}
