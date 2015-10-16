package utils;

import java.awt.Color;
import java.lang.reflect.Field;

public class CellColors {
	private static Color[] colors = { Color.BLACK, Color.DARK_GRAY, Color.GRAY,
			Color.LIGHT_GRAY, Color.WHITE, Color.RED, Color.ORANGE,
			Color.YELLOW, Color.PINK, Color.BLUE, Color.CYAN, Color.GREEN,
			Color.MAGENTA };

	public static Color[] getColors() {
		return colors;
	}

	public static String getColorName(Color c) {
		for (Field f : Color.class.getFields()) {
			try {
				if (f.getType() == Color.class && f.get(null).equals(c)) {
					return getName(f);
				}
			} catch (java.lang.IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return "ErrorReadingColor";
	}

	public static String getName(Field f) {
		String s = f.getName();
		String[] ss = { s.substring(0, 1), s.substring(1, s.length()) };
		return ss[0].toUpperCase() + ss[1];
	}
}
