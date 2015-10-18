package model;

public class Field {
	private Cell[][] cells;
	public int rows;
	public int columns;
	
	
	public Field(int rows, int columns){
		cells = new Cell[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	
	public Cell getCell(int x, int y){
		return cells[x][y];
	}
	
	public void insertCellAt(int x, int y, Cell c){
		cells[x][y] = c;
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
	
}
