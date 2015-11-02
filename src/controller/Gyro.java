package controller;
import model.GyroInput;
import model.GyroRule;

public class Gyro {
	static final int INPUTCOUNT = 10;
	static final int INTENSITY_TRESHOLD = 40;
	
	public GyroInput[] inputs;
	
	public Gyro(){
		inputs = new GyroInput[INPUTCOUNT];
	}
	
	public void receiveInput(GyroInput input){
		GyroInput[] newInputs = new GyroInput[INPUTCOUNT];
		for(int i = 0; i < INPUTCOUNT - 1; i++){
			newInputs[i+1] = inputs[i];
		}
		newInputs[0] = input;
		inputs = newInputs;
	}
	
	//returns the rule the game should use according to the gyro inputs
		public GyroRule checkMovement(){
			//Check for left tilts
			if (inputs[0].angleX > inputs[INPUTCOUNT - 1].angleX){
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
			if (inputs[0].angleX > inputs[INPUTCOUNT - 1].angleX){
				//right tilt occurred
				//check intensity
				if(inputs[0].angleX > inputs[INPUTCOUNT - 1].angleX + INTENSITY_TRESHOLD){
					return GyroRule.STEEP_TILT_RIGHT;
				} else {
					return GyroRule.TILT_RIGHT;
				}
			}
			//check for front tilts
			if(inputs[0].angleY < inputs[INPUTCOUNT - 1].angleY){
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
			if(inputs[0].angleY > inputs[INPUTCOUNT - 1].angleY){
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
