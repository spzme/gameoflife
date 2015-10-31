package controller;

import java.util.ArrayList;

import model.*;

public class Game {
	private Field field;
	private Field previousField;
	private int generationCount;
	private Rule rule;
	ArrayList<Tuple> differences;
	
	
	public Game(Field field, Rule rule) {
		this.field = field;
		previousField = field;
		generationCount = 1;
		this.rule = rule;
	}

	public Field nextGeneration() {
		differences = new ArrayList<Tuple>();
		generationCount++;
		previousField = field;
		Field newField = new Field(field.getColumnCount(), field.getRowCount());
		for (int y = 0; y < field.getRowCount(); y++) {
			for (int x = 0; x < field.getColumnCount(); x++) {
				boolean previousState = field.getAliveState(x, y);
				boolean newState = false;
				int neighbours = 0;
				// top row
				if (y > 0) {
					// top mid
					if (field.getAliveState(x, y - 1)) {
						neighbours++;
					}
					// top left
					if (x > 0 && field.getAliveState(x - 1, y - 1)) {
						neighbours++;
					}
					// top right
					if (x < field.getColumnCount() - 1
							&& field.getAliveState(x + 1, y - 1)) {
						neighbours++;
					}
				}
				// mid row
				// mid left
				if (x > 0 && field.getAliveState(x - 1, y)) {
					neighbours++;
				}
				// mid right
				if (x < field.getColumnCount() - 1 && field.getAliveState(x + 1, y)) {
					neighbours++;
				}
				// bottom row
				if (y < field.getRowCount() - 1) {
					// bottom mid
					if (field.getAliveState(x, y + 1)) {
						neighbours++;
					}
					// bottom left
					if (x > 0 && field.getAliveState(x - 1, y + 1)) {
						neighbours++;
					}
					// bottom right
					if (x < field.getColumnCount() - 1
							&& field.getAliveState(x + 1, y + 1)) {
						neighbours++;
					}

				}
				// The amount of alive neighbours is determined now, determine
				// alive state of cell.
				if(field.getAliveState(x, y)){
					newState = rule.getLiveNextState(neighbours);
					
				} else {
					newState = rule.getDeadNextState(neighbours);
				}
				if (newState){
					newField.setAlive(x, y);
				}	else {
					newField.setDead(x, y);
				}
				if (newState != previousState){
					differences.add(new Tuple(x, y));
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
