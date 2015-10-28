package model;

public class Field {
	private Cell[][] cells;
	private int rows;
	private int columns;
	
	public Field(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		cells = new Cell[columns][rows];
	}
	
	public void setAlive(int x, int y, boolean alive) {
		cells[x][y].setAlive(alive);
	}
	
	public void setAlive(int x, int y){
		cells[x][y].setAlive();
	}
	
	public void setDead(int x, int y){
		cells[x][y].setDead();
	}
	
	public boolean getAliveState(int x, int y){
		return cells[x][y].isAlive();
	}
	
	public int getRowCount() {
		return rows;
	}
	
	public int getColumnCount() {
		return columns;
	}
	
	public void printField() {
		StringBuilder sb = new StringBuilder();
		for (int m =0; m < rows; m++){
			for (int n = 0; n < columns; n++){
				sb.append(cells[m][n].isAlive() ? "1" : "0");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public void insertCellAt(int x, int y, Cell c){
		cells[x][y] = c;
	}
}
