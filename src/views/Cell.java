package views;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton {

	// A boolean to indicate whether the cell is currently alive or dead
	private boolean alive;
	// A boolean to indicate if the cell has ever lived
	private boolean hasLived;

	// Color variables so the user has can determine the color of the cells
	// during runtime
	private Color aliveColor;
	private Color deadColor;
	private Color hasLivedColor;

	public Cell(Color aliveColor, Color deadColor, Color hasLivedColor) {
		super();
		this.aliveColor = aliveColor;
		this.deadColor = deadColor;
		this.hasLivedColor = hasLivedColor;
		alive = false;
		hasLived = true;
		this.setBackground(deadColor);
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
		this.setBackground(aliveColor);
	}

	public void setDead() {
		alive = false;
		if (hasLived()) {
			this.setBackground(hasLivedColor);
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
			setBackground(aliveColor);
		} else {
			setBackground(deadColor);
		}
	}

}
