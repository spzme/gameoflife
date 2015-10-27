package game;

import model.*;
import views.*;

public class Game {
	private Field field;
	private NewGUI gui;

	private int freq; // how many generations should be computed every second?
	private long previousGenerationTime;

	public Game(NewGUI gui, Field field) {
		this.gui = gui;
		this.field = field;
	}

	public Field nextGeneration() {
		Field newField = new Field(field.getRows(), field.getColumns());
		for (int i = 0; i < field.rows; i++) {
			for (int j = 0; j < field.columns; j++) {
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
					if (j < field.columns - 1
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
				if (j < field.columns - 1 && field.getAliveState(i, j + 1)) {
					neighbours++;
				}
				// bottom row
				if (i < field.rows - 1) {
					// bottom mid
					if (field.getAliveState(i + 1, j)) {
						neighbours++;
					}
					// bottom left
					if (j > 0 && field.getAliveState(i + 1, j - 1)) {
						neighbours++;
					}
					// bottom right
					if (j < field.columns - 1
							&& field.getAliveState(i + 1, j + 1)) {
						neighbours++;
					}

				}
				// The amount of alive neighbours is determined now, determine
				// alive state of cell.

				if (neighbours <= 2) {
					// Die because of underpopulation.
					newField.setDead(i, j);
				}
				if (neighbours == 2 || neighbours == 3) {
					// Live on to the next generation.
					// Or, become alive!
					newField.setAlive(i, j);
				}
				if (neighbours > 3) {
					// Die because of overpopulation
					newField.setDead(i, j);
				}

			}
		}
		field = newField;
		return newField;

	}

	public void setFrequency(int frequency) {
		freq = frequency;
	}
	
	public Field getField() {
		return field;
	}

}
