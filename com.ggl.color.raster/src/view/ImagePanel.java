package com.ggl.color.raster.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ggl.color.raster.model.ColorRasterModel;

public class ImagePanel {
	
	private final Dimension minimumDimension;
	
	private final ColorRasterModel model;
	
	private JPanel panel;

	public ImagePanel(ColorRasterModel model) {
		this.model = model;
		this.minimumDimension = new Dimension(640, 480);
		this.panel = createMainPanel();
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JPanel innerPanel = createImagePanel();
		JScrollPane scrollPane = new JScrollPane(innerPanel);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(50);
		scrollPane.getVerticalScrollBar().setUnitIncrement(50);
		Dimension d = new Dimension(minimumDimension);
		d.width += 30;
		d.height += 30;
		scrollPane.setPreferredSize(d);
		panel.add(scrollPane);
		
		return panel;
	}
	
	private JPanel createImagePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		
		BufferedImage image = model.getImage();
		JLabel imageLabel = new JLabel();
		if (image != null) {
			imageLabel.setIcon(new ImageIcon(image));
		}
		panel.add(imageLabel);
		
		Dimension d = panel.getPreferredSize();
		if (d.width < minimumDimension.width || d.height < minimumDimension.height) {
			panel.setPreferredSize(minimumDimension);
		}
		
		return panel;
	}
	
	public void updateImage() {
		this.panel = createMainPanel();
	}

	public JPanel getPanel() {
		return panel;
	}

}
