package views;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import utils.CellColors;

@SuppressWarnings("serial")
public class ColorCellRenderer extends DefaultListCellRenderer {

	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String s = CellColors.getColorName((Color) value);
		Component c = super.getListCellRendererComponent(list, s, index,
				isSelected, cellHasFocus);
		c.setBackground((Color) value);
		return c;
	}
}
