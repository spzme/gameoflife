package utils;

import javafx.scene.paint.Color;

public class ColorConverter {

	public static String getColor(Color c) {

		StringBuilder sb = new StringBuilder("-fx-base: #");
		String red = Integer.toHexString((int) (c.getRed() * 255));
		if (red.length() == 1) {
			sb.append("0");
		}
		sb.append(red);
		String green = Integer.toHexString((int) (c.getGreen() * 255));
		if (green.length() == 1) {
			sb.append("0");
		}
		sb.append(green);
		String blue = Integer.toHexString((int) (c.getBlue() * 255));
		if (blue.length() == 1) {
			sb.append("0");
		}
		sb.append(blue);
		sb.append(";");
		return sb.toString();
	}

}
