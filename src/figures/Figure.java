package figures;

import model.Field;

public abstract class Figure {
	public Field field;
	
	public abstract boolean[][] getField(int rows, int columns);
}
