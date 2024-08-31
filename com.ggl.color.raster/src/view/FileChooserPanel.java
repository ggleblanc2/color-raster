package com.ggl.color.raster.view;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.color.raster.controller.FileChooserListener;
import com.ggl.color.raster.controller.ProcessImageListener;
import com.ggl.color.raster.model.ColorRasterModel;

public class FileChooserPanel {
	
	private final ColorRasterFrame view;
	
	private final ColorRasterModel model;
	
	private final JPanel panel;
	
	private JTextField fileNameField;

	public FileChooserPanel(ColorRasterFrame view, ColorRasterModel model) {
		this.view = view;
		this.model = model;
		this.panel = createMainPanel();
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		Font normalFont = panel.getFont().deriveFont(16f);
		Font boldFont = normalFont.deriveFont(Font.BOLD);
		
		JLabel label = new JLabel("File:");
		label.setFont(boldFont);
		panel.add(label);
		
		fileNameField = new JTextField(60);
		fileNameField.setFont(normalFont);
		panel.add(fileNameField);
		
		JButton fileChooserButton = new JButton("Choose File");
		fileChooserButton.addActionListener(new FileChooserListener(view, model));
		fileChooserButton.setFont(boldFont);
		panel.add(fileChooserButton);
		
		JButton processImageButton = new JButton("Process Image");
		processImageButton.addActionListener(new ProcessImageListener(view, model));
		processImageButton.setFont(boldFont);
		panel.add(processImageButton);
		
		return panel;
	}
	
	public void updateFileNameField() {
		fileNameField.setText(model.getDirectory().getAbsolutePath());
	}

	public JPanel getPanel() {
		return panel;
	}
	
}
