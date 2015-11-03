package views;

import javafx.scene.control.Button;
import utils.ColorConverter;
import application.Main;

public class Cell extends Button {

	// A boolean to indicate whether the cell is currently alive or dead
	private boolean alive;
	// A boolean to indicate if the cell has ever lived
	private boolean hasLived;
	private Main gui;

	public Cell(Main newGUI) {
		super();
		this.gui = newGUI;
		alive = false;
		hasLived = false;
		setStyle(ColorConverter.getColor(gui.getDeadColor()));
	}

	public void setAlive(boolean b) {
		if (b) {
			setAlive();
		} else {
			setDead();
		}
	}

	public void setAlive() {
		alive = true;
		hasLived = true;
		setStyle(ColorConverter.getColor(gui.getAliveColor()));
	}

	public void setDead() {
		alive = false;
		updateColor();
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean hasLived() {
		return hasLived;
	}

	public void toggleState() {
		// !Only for use by the GUI!
		if (alive) {
			alive = false;
			hasLived = false;
			setStyle(ColorConverter.getColor(gui.getDeadColor()));
		} else {
			alive = true;
			hasLived = true;
			setStyle(ColorConverter.getColor(gui.getAliveColor()));
		}
	}

	// update representation of cell in GUI.
	public void updateColor() {
		if (alive) {
			setStyle(ColorConverter.getColor(gui.getAliveColor()));
		} else if (hasLived) {
			setStyle(ColorConverter.getColor(gui.getHasLivedColor()));
		} else {
			setStyle(ColorConverter.getColor(gui.getDeadColor()));
		}
	}

}
