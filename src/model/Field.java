package model;

public class Field {
	private boolean[][] cells;
	public int rows;
	public int columns;
	
	public Field(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		cells = new boolean[columns][rows];
	}
	
	public void setAlive(int x, int y, boolean alive) {
		cells[x][y] = alive;
	}
	
	public void setAlive(int x, int y){
		cells[x][y] = true;
	}
	
	public void setDead(int x, int y){
		cells[x][y] = false;
	}
	
	public boolean getAliveState(int x, int y){
		return cells[x][y];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public void printField() {
		StringBuilder sb = new StringBuilder();
		for (boolean[] bb : cells) {
			for (boolean b : bb) {
				sb.append(b ? "1" : "0");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}
