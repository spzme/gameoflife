package controller;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import model.*;
public class Game {
	private Field field;
	private Field previousField;
	private int generationCount;
	private Rule rule;
	ArrayList<Tuple> differences;
	Gyro gyro;
	Lock l;
	public Game(Field field, Rule rule) {
		this.field = field;
		previousField = field;
		generationCount = 1;
		gyro = new Gyro();
		l = new ReentrantLock();
		this.rule = rule;
	}
	public Field nextGeneration() {
		l.lock();
		System.out.println("field locked by nextGeneration");
		try {
			differences = new ArrayList<Tuple>();
			generationCount++;
			previousField = field;
			Field newField = new Field(field.getColumnCount(),
					field.getRowCount());
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
					if (x < field.getColumnCount() - 1
							&& field.getAliveState(x + 1, y)) {
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
					// The amount of alive neighbours is determined now,
					// determine
					// alive state of cell.
					if (field.getAliveState(x, y)) {
						newState = rule.getLiveNextState(neighbours);
					} else {
						newState = rule.getDeadNextState(neighbours);
					}
					if (newState) {
						newField.setAlive(x, y);
					} else {
						newField.setDead(x, y);
					}
					if (newState != previousState) {
						differences.add(new Tuple(x, y));
					}
				}
			}
			newField.printField();
			field = newField;
		} finally {
			l.unlock();
			System.out.println("field unlocked by nextGeneration");
		}
		System.out.println("Field returned by nextGeneration");
		// field.printField();
		return field;
	}
	public void receiveInputFromGyro() {
		gyro.receiveInput();
	}
	public void clearGyroInputHistory() {
		gyro.clearInputHistory();
	}
	// Alter the field based on a gyro rule that is applicable at the moment.
	public Field applyGyroRule() {
		l.lock();
		System.out.println("Field locked by gyroRule");
		try {
			differences = new ArrayList<Tuple>();
			previousField = field;
			Field newField = new Field(field.getColumnCount(),
					field.getRowCount());
			GyroRule rule = gyro.checkMovement();
			switch (rule) {
			case TILT_LEFT:
				System.out.println("TILT_LEFT");
				break;
			case TILT_FRONT_LEFT:
				System.out.println("TILT_FRONT_LEFT");
				break;
			case TILT_BACK_LEFT:
				System.out.println("TILT_BACK_LEFT");
				break;
			case TILT_RIGHT:
				System.out.println("TILT_RIGHT");
				break;
			case TILT_FRONT_RIGHT:
				System.out.println("TILT_FRONT_RIGHT");
				break;
			case TILT_BACK_RIGHT:
				System.out.println("TILT_BACK_RIGHT");
			case TILT_FRONT:
				System.out.println("TILT_FRONT");
				break;
			case TILT_BACK:
				System.out.println("TILT_BACK");
				break;
			case DEFAULT:
				System.out.println("No GyroRule was applicable");
				// Don't do anything
				newField = field;
				break;
			}
			field = newField;
		} finally {
			l.unlock();
			System.out.println("Field unlocked by gyroRule");
		}
		System.out.println("Field returned by gyroRule");
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
	public Tuple[] getDifferences() {
		Tuple[] diff = new Tuple[differences.size()];
		int i = 0;
		for (Tuple cellDiff : differences) {
			diff[i] = cellDiff;
			i++;
		}
		return diff;
	}
}