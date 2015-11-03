package model;

public class Rule {
	private boolean[] survive;
	private boolean[] birth;

	public Rule(boolean[] survive, boolean[] birth) {
		this.survive = survive;
		this.birth = birth;
	}

	public boolean getLiveNextState(int neighbours) {
		return survive[neighbours];
	}

	public boolean getDeadNextState(int neighbours) {
		return birth[neighbours];
	}

}
