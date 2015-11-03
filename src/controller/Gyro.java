package controller;

import communication.CommunicationController;
import communication.PinException;
import model.GyroInput;
import model.GyroRule;

public class Gyro {
	static final int INPUTCOUNT = 10;
	static final int MOVEMENT_TRESHOLD = 10; //when it is considered a movement
	static final int INTENSITY_TRESHOLD = 40; //when it is considered a strong movement
	boolean initialized = false;
	
	CommunicationController controller;
	
	public GyroInput[] inputs;
	
	public Gyro(){
		inputs = new GyroInput[INPUTCOUNT];
		if(!initialized){
		try {
			controller = new CommunicationController();
			initialized = true;
		} catch (PinException e) {
			//bla
		}
		} else {
			System.out.println("The controller already exists! I will not make a new one");
		}
	}
	
	public GyroInput receiveInput(){
//		float accX; //Acceleration in X direction, m/s^2
//		float accY; //Acceleration in Y direction, m/s^2
//		float accZ; //Acceleration in Z direction, m/s^2	
		//TODO: Actually read input from gyro sensor
		float angleX = 0;
		float angleY = 0;
		float angleZ = 0;
		GyroInput input = new GyroInput(angleX,angleY,angleZ);
		
		insertInput(input);
		return input;
	}
	
	public void clearInputHistory(){
		inputs = new GyroInput[INPUTCOUNT];
	}
	
	public void insertInput(GyroInput input){
		//System.out.println("An input was added");
		GyroInput[] newInputs = new GyroInput[INPUTCOUNT];
		for(int i = 0; i < INPUTCOUNT - 1; i++){
			newInputs[i+1] = inputs[i];
		}
		newInputs[0] = input;
		inputs = newInputs;
	}
	// returns the rule the game should use according to the gyro inputs
		public GyroRule checkMovement() {
			System.out.println("Checking movement");
			byte b = controller.getGyroInformation();
			System.out.println(b);
			byte xAngle = (byte) (b & 0b11);
			byte yAngle = (byte) ((b >> 2) & 0b11);
			
			if (xAngle == 0b01) {
				if (yAngle == 0b01) {
					return GyroRule.TILT_BACK_LEFT;
				} else if (yAngle == 0b10) {
					return GyroRule.TILT_FRONT_LEFT;
				} else {
					return GyroRule.TILT_LEFT;
				}
			} else if (xAngle == 0b10) {
				if (yAngle == 0b01) {
					return GyroRule.TILT_BACK_RIGHT;
				} else if (yAngle == 0b10) {
					return GyroRule.TILT_FRONT_RIGHT;
				} else {
					return GyroRule.TILT_RIGHT;
				}
			} else {
				if (yAngle == 0b01) {
					return GyroRule.TILT_BACK;
				} else if (yAngle == 0b10) {
					return GyroRule.TILT_FRONT;
				}
			}
			return GyroRule.DEFAULT;
		}
}
