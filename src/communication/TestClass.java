package communication;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class TestClass {

	public static void main(String[] args) {
		// TODO code application logic here

		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalOutput led1 = gpio
				.provisionDigitalOutputPin(RaspiPin.GPIO_17);

		// continuously blink the led every 1/2 second for 15 seconds
		led1.blink(500, 15000);

		System.out
				.println(" ... the LED will continue blinking until the program is terminated.");
		System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

		// keep program running until user aborts (CTRL-C)
		for (;;) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
