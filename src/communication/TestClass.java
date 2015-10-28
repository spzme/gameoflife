package communication;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class TestClass {

	public static void main(String[] args) {
		// TODO code application logic here

		final GpioController gpio = GpioFactory.getInstance();

		// GPIO_01 is GPIO_18 on the pi
		final GpioPinDigitalOutput led = gpio
				.provisionDigitalOutputPin(RaspiPin.GPIO_01);

		System.out
				.println(" ... the LED will continue blinking until the program is terminated.");
		System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

		// keep program running until user aborts (CTRL-C)
		while (true) {
			led.blink(1000, 1000);
		}
	}
}
