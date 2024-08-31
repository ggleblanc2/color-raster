package com.ggl.color.raster.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.ggl.color.raster.controller.FormattedIntegerRenderer;
import com.ggl.color.raster.model.ColorRasterModel;
import com.ggl.color.raster.model.ColorTableModel;
import com.ggl.color.raster.model.Raster;

public class ColorTablePanel {
	
	private final ColorRasterModel model;
	
	private final ColorTableModel tableModel;
	
	private final JPanel panel;
	
	private JProgressBar progressBar;
	
	private JTable table;
	
	private JTextField widthField, heightField, colorCountField;

	public ColorTablePanel(ColorRasterModel model) {
		this.model = model;
		
		String[] columns = { "Color", "Alpha", "Red", "Green", "Blue", "Pixel Count" };
		this.tableModel = new ColorTableModel(columns, 0);
		this.panel = createMainPanel();
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JPanel tablePanel = createTablePanel();
		Dimension d = tablePanel.getPreferredSize();
		d.width += 40;
		JScrollPane scrollPane = new JScrollPane(tablePanel);
		scrollPane.setPreferredSize(d);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(50);
		scrollPane.getVerticalScrollBar().setUnitIncrement(120);
		
		panel.add(createHeaderPanel(), BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createHeaderPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font normalFont = panel.getFont().deriveFont(16f);
		Font boldFont = normalFont.deriveFont(Font.BOLD);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.green);
		Dimension d = progressBar.getPreferredSize();
		d.height = 24;
		progressBar.setPreferredSize(d);
		panel.add(progressBar, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 1;
		JLabel label = new JLabel("Image Size:");
		label.setFont(boldFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		widthField = new JTextField(5);
		widthField.setEditable(false);
		widthField.setFont(normalFont);
		panel.add(widthField, gbc);
		
		gbc.gridx++;
		label = new JLabel("X");
		label.setFont(boldFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		heightField = new JTextField(5);
		heightField.setEditable(false);
		heightField.setFont(normalFont);
		panel.add(heightField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		label = new JLabel("Color Count:");
		label.setFont(boldFont);
		panel.add(label, gbc);
		
		gbc.gridx++;
		gbc.gridwidth = 3;
		colorCountField = new JTextField(10);
		colorCountField.setEditable(false);
		colorCountField.setFont(normalFont);
		panel.add(colorCountField, gbc);
		
		return panel;
	}
	
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		table = new JTable(tableModel);
		table.setFont(panel.getFont().deriveFont(16f));
		table.setDefaultRenderer(Integer.class, new FormattedIntegerRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.getTableHeader().setFont(panel.getFont().deriveFont(Font.BOLD, 16f));
        table.setAutoCreateRowSorter(false);
        table.setRowHeight(24);

		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		
		return panel;
	}
	
	public void addTableData() {
		resetRowCount();
		table.setAutoCreateRowSorter(false);
		table.setRowSorter(null);
		Map<Integer, List<Point>> colorMap = model.getColorMap();
		Set<Integer> colorSet = colorMap.keySet();
		Iterator<Integer> iter = colorSet.iterator();
		
		Raster raster = model.getRaster();
		Dimension d = raster.getDimension();
		widthField.setText(String.format("%,d", d.width));
		heightField.setText(String.format("%,d", d.height));
		colorCountField.setText(String.format("%,d", colorMap.size()));
		
		while (iter.hasNext()) {
			Integer colorValue = iter.next();
			List<Point> points = colorMap.get(colorValue);
			addTableRow(colorValue, points.size());
		}
		
		table.setAutoCreateRowSorter(true);
	}
	
	private void addTableRow(int colorValue, int size) {
		int alpha = (colorValue & 0xff000000) >>> 24;
		int red = (colorValue & 0x00ff0000) >>> 16;
		int green = (colorValue & 0x0000ff00) >>> 8;
		int blue = colorValue & 0x000000ff;
		
		Object[] rowData = new Object[6];
		rowData[0] = createImage(new Color(red, green, blue, alpha));
		rowData[1] = alpha;
		rowData[2] = red;
		rowData[3] = green;
		rowData[4] = blue;
		rowData[5] = size;
		
		tableModel.addRow(rowData);
	}
	
	private ImageIcon createImage(Color color) {
		BufferedImage image = new BufferedImage(48, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.dispose();
		
		return new ImageIcon(image);
	}
	
	public void resetRowCount() {
//		progressBar.setValue(0);
		widthField.setText(" ");
		heightField.setText(" ");
		colorCountField.setText(" ");
		tableModel.setRowCount(0);
	}
	
	public void setProgressBarValue(int count) {
		progressBar.setValue(count);
	}
	
	public void setProgressBarMaximum(int pixelCount) {
		progressBar.setMaximum(pixelCount);
	}

	public JPanel getPanel() {
		return panel;
	}

}
