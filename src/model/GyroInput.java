package model;

//To model one input from the gyro sensor.
public class GyroInput {
	//The fields that have to be stored here
	//are dependant on the type of gyro sensor we use,
	//so, this probably is not complete.
//	public float accX; //Acceleration in X direction, m/s^2
//	public float accY; //Acceleration in Y direction, m/s^2
//	public float accZ; //Acceleration in Z direction, m/s^2	
	public float angleX;
	public float angleY;
	public float angleZ;
	
	public GyroInput(/*float accX, float accY, float accZ,*/ float angleX, float angleY, float angleZ){
//		this.accX=accX;
//		this.accY=accY;
//		this.accZ=accZ;
		this.angleX=angleX;
		this.angleY=angleY;
		this.angleZ=angleZ;
	}
	
	
	
}
