package model;

public enum GyroRule {
	DEFAULT(0),
	TILT_LEFT(1),
	STEEP_TILT_LEFT(2),
	TILT_RIGHT(3),
	STEEP_TILT_RIGHT(4),
	TILT_FRONT(5),
	STEEP_TILT_FRONT(6),
	TILT_BACK(7),
	STEEP_TILT_BACK(8);

	private int value;
	private GyroRule(int value){
		this.value = value;
	}
	
	public static GyroRule getByValue(int value){
		switch (value){
		case 1:
			return TILT_LEFT;
		case 2:
			return STEEP_TILT_LEFT;
		case 3:
			return TILT_RIGHT;
		case 4:
			return STEEP_TILT_RIGHT;
		case 5:
			return TILT_FRONT;
		case 6:
			return STEEP_TILT_FRONT;
		case 7:
			return TILT_BACK;
		case 8: 
			return STEEP_TILT_BACK;
		default:
			return DEFAULT;
		}
	}
}
