package controller;

import model.*;

public class Game {
	private Field field;
	private Field previousField;
	
	public Game(Field field) {
		this.field = field;
		previousField = field;
	}

	public void nextGeneration() {
		previousField = field;
		Field newField = new Field(field.getRowCount(), field.getColumnCount(),  field.getGUI());
		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				newField.insertCellAt(i, j, new Cell(field.getGUI()));
				int neighbours = 0;
				// top row
				if (i > 0) {
					// top mid
					if (field.getAliveState(i - 1, j)) {
						neighbours++;
					}
					// top left
					if (j > 0 && field.getAliveState(i - 1, j - 1)) {
						neighbours++;
					}
					// top right
					if (j < field.getColumnCount() - 1
							&& field.getAliveState(i - 1, j + 1)) {
						neighbours++;
					}
				}
				// mid row
				// mid left
				if (j > 0 && field.getAliveState(i, j - 1)) {
					neighbours++;
				}
				// mid right
				if (j < field.getColumnCount() - 1 && field.getAliveState(i, j + 1)) {
					neighbours++;
				}
				// bottom row
				if (i < field.getRowCount() - 1) {
					// bottom mid
					if (field.getAliveState(i + 1, j)) {
						neighbours++;
					}
					// bottom left
					if (j > 0 && field.getAliveState(i + 1, j - 1)) {
						neighbours++;
					}
					// bottom right
					if (j < field.getColumnCount() - 1
							&& field.getAliveState(i + 1, j + 1)) {
						neighbours++;
					}

				}
				// The amount of alive neighbours is determined now, determine
				// alive state of cell.

				if (neighbours < 2) {
					// Die because of underpopulation.
					newField.setDead(i, j);
				}
				if (neighbours == 3) {
					// Live on to the next generation.
					// Or, become alive!
					newField.setAlive(i, j);
				}
				if (neighbours == 2 && field.getAliveState(i, j)) {
					// Live on to the next generation
					newField.setAlive(i, j);
				}
				if (neighbours > 3) {
					// Die because of overpopulation
					newField.setDead(i, j);
				}

			}
		}
		field = newField;
	}

	public void previousGeneration(){
		field =  previousField;
	}
	
	public Field getField() {
		return field;
	}

}
