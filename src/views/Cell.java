package views;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton {

	// A boolean to indicate whether the cell is currently alive or dead
	private boolean alive;
	// A boolean to indicate if the cell has ever lived
	private boolean hasLived;
	private GUI gui;

	public Cell(GUI newGUI) {
		super();
		this.gui = newGUI;
		alive = false;
		hasLived = false;
		setBackground(gui.getDeadColor());
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
		if (alive) {
			alive = false;
			hasLived = false;
			setBackground(gui.getDeadColor());
		} else {
			alive = true;
			hasLived = true;
			setBackground(gui.getAliveColor());
		}
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
