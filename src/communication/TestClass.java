package communication;

public class TestClass {

	public static void main(String[] args) throws PinException {

		System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

		CommunicationController controller = new CommunicationController();

		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						byte b = (byte) 0b11111111;
						controller.setByte(b);
						controller.setWrite();
						controller.setEnabled();
						Thread.sleep(1000);
						controller.setDisabled();
						byte b2 = (byte) 0b01010101;
						controller.setByte(b2);
						Thread.sleep(10);
						controller.setEnabled();
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (PinException e) {
						e.printStackTrace();
					}
					System.out.println("Still running");
				}
			}
		};
		t.start();
//		CommunicationController.test(GpioFactory.getInstance());
	}
}
