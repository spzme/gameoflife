package controller;

import java.util.ArrayList;

import model.*;

public class Game {
	private Field field;
	private Field previousField;
	private int generationCount;
	ArrayList<Tuple> differences;
	
	
	public Game(Field field) {
		this.field = field;
		previousField = field;
		generationCount = 1;
	}

	public Field nextGeneration() {
		differences = new ArrayList<Tuple>();
		generationCount++;
		previousField = field;
		Field newField = new Field(field.getRowCount(), field.getColumnCount(), field.getGUI());
		for (int i = 0; i < field.getRowCount(); i++) {
			for (int j = 0; j < field.getColumnCount(); j++) {
				boolean previousState = field.getAliveState(i, j);
				boolean newState = false;
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
					newState = false;
				}
				if (neighbours == 3) {
					// Live on to the next generation.
					// Or, become alive!
					newField.setAlive(i, j);
					newState = true;
				}
				if (neighbours == 2 && field.getAliveState(i, j)) {
					// Live on to the next generation
					newField.setAlive(i, j);
					newState = true;
				}
				if (neighbours > 3) {
					// Die because of overpopulation
					newField.setDead(i, j);
					newState = false;
				}
				if (newState != previousState){
					differences.add(new Tuple(i,j));
				}
			}
		}
		field = newField;
		return field;
	}

	public Field previousGeneration() {
		generationCount--;
		field = previousField;
		return field;
	}
	
	public Field getField() {
		return field;
	}
	
	public int getGenerationCount() {
		return generationCount;
	}
	
	public Tuple[] getDifferences(){
		Tuple[] diff = new Tuple[differences.size()];
		int i = 0;
		for(Tuple cellDiff : differences){
			diff[i] = cellDiff;
			i++;
		}
		return diff;
	}

}
