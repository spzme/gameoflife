package model;

import javax.swing.JButton;

import views.GUI;

@SuppressWarnings("serial")
public class Cell extends JButton {

	// A boolean to indicate whether the cell is currently alive or dead
	private boolean alive;
	// A boolean to indicate if the cell has ever lived
	private boolean hasLived;
	private GUI gui;

	public Cell(GUI gui) {
		super();
		this.gui = gui;
		alive = false;
		hasLived = true;
		this.setBackground(gui.getDeadColor());
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
		this.setBackground(gui.getAliveColor());
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
		//!Only for use by the GUI!
		alive = !alive;
		updateColor();
	}
	
	//update representation of cell in GUI.
	public void updateColor(){
		if (alive) {
			setBackground(gui.getAliveColor());
		} else if (hasLived) {
			setBackground(gui.getHasLivedColor());
		} else {
			setBackground(gui.getDeadColor());
		}
	}

}
