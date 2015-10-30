package figures;

import model.Field;

public abstract class Figure {
	public Field field;
	public int rows;
	public int columns;
	
	public abstract boolean[][] getField(int columns, int rows);
}
