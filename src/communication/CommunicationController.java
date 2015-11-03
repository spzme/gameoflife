package communication;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.RaspiPin;

public class CommunicationController {
	private static GpioController gpio;
	private static GpioPinDigitalMultipurpose bit0;
	private static GpioPinDigitalMultipurpose bit1;
	private static GpioPinDigitalMultipurpose bit2;
	private static GpioPinDigitalMultipurpose bit3;
	private static GpioPinDigitalMultipurpose bit4;
	private static GpioPinDigitalMultipurpose bit5;
	private static GpioPinDigitalMultipurpose bit6;
	private static GpioPinDigitalMultipurpose bit7;
	private static GpioPinDigitalOutput readWrite;
	private static GpioPinDigitalOutput enableDisable;

	public static void init() throws PinException {
		gpio = GpioFactory.getInstance();
		
		bit0 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(9), PinMode.DIGITAL_OUTPUT);
		bit1 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(10), PinMode.DIGITAL_OUTPUT);
		bit2 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(11), PinMode.DIGITAL_OUTPUT);
		bit3 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(12), PinMode.DIGITAL_OUTPUT);
		bit4 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(13), PinMode.DIGITAL_OUTPUT);
		bit5 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(14), PinMode.DIGITAL_OUTPUT);
		bit6 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(15), PinMode.DIGITAL_OUTPUT);
		bit7 = gpio.provisionDigitalMultipurposePin(CommunicationController
				.getPin(21), PinMode.DIGITAL_OUTPUT);
		
		readWrite = gpio.provisionDigitalOutputPin(CommunicationController
				.getPin(22));
		enableDisable = gpio.provisionDigitalOutputPin(CommunicationController
				.getPin(23));
	}

	public static void setEnabled() {
		enableDisable.high();
	}

	public static void setDisabled() {
		enableDisable.low();
	}

	public static void setRead() {
		readWrite.high();
	}

	public static void setWrite() {
		readWrite.low();
	}
	
	public static void setByte(Byte b) throws PinException {
		bit0.setMode(PinMode.DIGITAL_OUTPUT);
		bit1.setMode(PinMode.DIGITAL_OUTPUT);
		bit2.setMode(PinMode.DIGITAL_OUTPUT);
		bit3.setMode(PinMode.DIGITAL_OUTPUT);
		bit4.setMode(PinMode.DIGITAL_OUTPUT);
		bit5.setMode(PinMode.DIGITAL_OUTPUT);
		bit6.setMode(PinMode.DIGITAL_OUTPUT);
		bit7.setMode(PinMode.DIGITAL_OUTPUT);
		
		Byte mask = 0b00000001;
		GpioPinDigitalOutput[] pins = getBytePins();
		for (int i = 0; i < 8; i++) {
			GpioPinDigitalOutput pin = pins[i];
			if ((b & mask) != 0) {
				pin.high();
			} else {
				pin.low();
			}
			mask = (byte) (mask << 1);
		}
	}

	public static Byte getByte() throws PinException {
		bit0.setMode(PinMode.DIGITAL_INPUT);
		bit1.setMode(PinMode.DIGITAL_INPUT);
		bit2.setMode(PinMode.DIGITAL_INPUT);
		bit3.setMode(PinMode.DIGITAL_INPUT);
		bit4.setMode(PinMode.DIGITAL_INPUT);
		bit5.setMode(PinMode.DIGITAL_INPUT);
		bit6.setMode(PinMode.DIGITAL_INPUT);
		bit7.setMode(PinMode.DIGITAL_INPUT);
		
		Byte mask = 0b00000001;
		Byte result = 0b00000000;
		GpioPinDigitalInput[] pins = getBytePins();
		for (int i = 0; i < 8; i++) {
			GpioPinDigitalInput pin = pins[i];
			if (pin.isHigh()) {
				result = (byte) (result ^ mask);
			}
			mask = (byte) (mask << 1);
		}
		return result;
	}

	private static GpioPinDigitalMultipurpose[] getBytePins() {
		GpioPinDigitalMultipurpose[] result = new GpioPinDigitalMultipurpose[8];
		result[0] = bit0;
		result[1] = bit1;
		result[2] = bit2;
		result[3] = bit3;
		result[4] = bit4;
		result[5] = bit5;
		result[6] = bit6;
		result[7] = bit7;
		return result;
	}
	
	public static Pin getPin(int i) throws PinException {
		switch (i) {
		case 9:
			return RaspiPin.GPIO_00;
		case 10:
			return RaspiPin.GPIO_01;
		case 11:
			return RaspiPin.GPIO_02;
		case 12:
			return RaspiPin.GPIO_03;
		case 13:
			return RaspiPin.GPIO_04;
		case 14:
			return RaspiPin.GPIO_05;
		case 15:
			return RaspiPin.GPIO_06;
		case 21:
			return RaspiPin.GPIO_12;
		case 22:
			return RaspiPin.GPIO_13;
		case 23:
			return RaspiPin.GPIO_14;
		default:
			throw new PinException();
		}
	}

	public static byte getGyroInformation(){
		byte b = 0;
		setRead();
		setEnabled();
		try {
			b = getByte();
		} catch (PinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDisabled();
		return b;
	}
}
