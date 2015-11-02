package controller;

import model.GyroInput;
import model.GyroRule;

public class Gyro {
	static final int INPUTCOUNT = 10;
	static final int MOVEMENT_TRESHOLD = 10; //when it is considered a movement
	static final int INTENSITY_TRESHOLD = 40; //when it is considered a strong movement
	
	public GyroInput[] inputs;
	
	public Gyro(){
		inputs = new GyroInput[INPUTCOUNT];
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
	
	public void insertInput(GyroInput input){
		//System.out.println("An input was added");
		GyroInput[] newInputs = new GyroInput[INPUTCOUNT];
		for(int i = 0; i < INPUTCOUNT - 1; i++){
			newInputs[i+1] = inputs[i];
		}
		newInputs[0] = input;
		inputs = newInputs;
	}
	
	//returns the rule the game should use according to the gyro inputs
		public GyroRule checkMovement(){
			if(inputs[INPUTCOUNT-1] == null){
				System.out.println("Don't have enough gyroInputs yet in history to determine movement");
				return GyroRule.DEFAULT;
			}
			//Check for left tilts
			if (inputs[0].angleX > inputs[INPUTCOUNT - 1].angleX + MOVEMENT_TRESHOLD){
				//left tilt occurred
				//check intensity
				if(inputs[0].angleX > inputs[INPUTCOUNT - 1].angleX + INTENSITY_TRESHOLD){
					return GyroRule.STEEP_TILT_LEFT;
				} else {
					return GyroRule.TILT_LEFT;
				}
			}
			//Check for right tilts
			//TODO: take into account that from 0 to 360 is only one degree difference
			if (inputs[0].angleX < inputs[INPUTCOUNT - 1].angleX - MOVEMENT_TRESHOLD){
				//right tilt occurred
				//check intensity
				if(inputs[0].angleX < inputs[INPUTCOUNT - 1].angleX + INTENSITY_TRESHOLD){
					return GyroRule.STEEP_TILT_RIGHT;
				} else {
					return GyroRule.TILT_RIGHT;
				}
			}
			//check for front tilts
			if(inputs[0].angleY < inputs[INPUTCOUNT - 1].angleY - MOVEMENT_TRESHOLD){
				//front tilt occured
				//check intensity
				if(inputs[0].angleY < inputs[INPUTCOUNT-1].angleY - INTENSITY_TRESHOLD){
					return GyroRule.STEEP_TILT_FRONT;
				} else {
					return GyroRule.TILT_FRONT;
				}
			}
			//check for back tilts
			//TODO: take into account that from 0 to 360 is only one degree difference
			if(inputs[0].angleY > inputs[INPUTCOUNT - 1].angleY + MOVEMENT_TRESHOLD){
				//back tilt occured
				//check intensity
				if(inputs[0].angleY > inputs[INPUTCOUNT - 1].angleY + INTENSITY_TRESHOLD){
					return GyroRule.STEEP_TILT_BACK;
				}	else { 
					return GyroRule.TILT_BACK;
				}
			}
			
			
			return GyroRule.DEFAULT;
		}
}
