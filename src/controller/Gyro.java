package controller;
import model.GyroInput;
import model.GyroRule;

public class Gyro {
	static final int INPUTCOUNT = 10;
	
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
			
			return GyroRule.DEFAULT;
		}
}
