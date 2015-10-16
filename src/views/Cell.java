package views;

import javax.swing.JButton;

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
		if (hasLived()) {
			this.setBackground(gui.getHasLivedColor());
		}
	}

	public boolean isAlive() {
		return alive;
	}

	public boolean hasLived() {
		return hasLived;
	}

	public void toggleState() {
		alive = !alive;
		if (alive) {
			setBackground(gui.getAliveColor());
		} else {
			setBackground(gui.getDeadColor());
		}
	}

}
