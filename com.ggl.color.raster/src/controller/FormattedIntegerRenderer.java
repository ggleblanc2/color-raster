package com.ggl.color.raster.controller;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class FormattedIntegerRenderer extends JLabel
		implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String s = String.format("%,d", (Integer) value);
		this.setFont(this.getFont().deriveFont(16f));
		this.setText(s + " ");
		this.setHorizontalAlignment(JLabel.RIGHT);
		return this;
	}

}
