package communication;

public class TestClass {

	public static void main(String[] args) throws PinException {

		CommunicationController controller = new CommunicationController();

		System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

		while (true) {
			try {
				byte b = (byte) 0b11111111;
				controller.setByte(b);
				controller.setWrite();
				controller.setEnabled();
				Thread.sleep(1);
				controller.setDisabled();
				byte b2 = (byte) 0b01010101;
				controller.setByte(b2);
				controller.setEnabled();
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				byte readByte = controller.getByte();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
				controller.setDisabled();
				controller.setRead();
				controller.setEnabled();
				System.out.println("Read the following byte: " + readByte);
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (PinException e) {
				e.printStackTrace();
			}
			System.out.println("Still running");
		}
	}
}
