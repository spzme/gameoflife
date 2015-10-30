package model;

public class Field {
	private boolean[][] alive;
	private int rows;
	private int columns;
	private int aliveCellCount;
	
	public Field(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		alive = new boolean[rows][columns];
	}
	
	public void setAlive(int x, int y, boolean live){
		if(live){
			setAlive(x,y);
		} else {
			setDead(x,y);
		}
	}
	
	public void setAlive(int x, int y){
		if (alive[x][y] == false){
			aliveCellCount++;
		}
		alive[x][y] = true;
	}
	
	public void setDead(int x, int y){
		if(alive[x][y]==true){
			aliveCellCount--;
		}
		alive[x][y] = false;
	}
	
	public boolean getAliveState(int x, int y){
		return alive[x][y];
	}
	
	public int getRowCount() {
		return rows;
	}
	
	public int getColumnCount() {
		return columns;
	}
	
	public void printField() {
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < rows; m++){
			for (int n = 0; n < columns; n++){
				sb.append(alive[m][n] ? "1" : "0");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public int getAliveCellCount(){
		return aliveCellCount;
	}
}
