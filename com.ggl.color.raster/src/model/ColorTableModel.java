package com.ggl.color.raster.model;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class ColorTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public ColorTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ImageIcon.class;
		default:
			return Integer.class;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
