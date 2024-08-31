package com.ggl.color.raster.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ggl.color.raster.model.ColorRasterModel;

public class ColorRasterFrame {
	
	private final ColorTablePanel colorTablePanel;
	
	private final FileChooserPanel fileChooserPanel;
	
	private final ImagePanel imagePanel;
	
	private final JFrame frame;

	public ColorRasterFrame(ColorRasterModel model) {
		this.fileChooserPanel = new FileChooserPanel(this, model);
		this.imagePanel = new ImagePanel(model);
		this.colorTablePanel = new ColorTablePanel(model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Color Raster");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(fileChooserPanel.getPanel(), BorderLayout.NORTH);
		frame.add(imagePanel.getPanel(), BorderLayout.CENTER);
		frame.add(colorTablePanel.getPanel(), BorderLayout.EAST);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public void resetRowCount() {
		colorTablePanel.resetRowCount();
	}
	
	public void addTableData() {
		colorTablePanel.addTableData();
	}
	
	public void setProgressBarValue(int count) {
		colorTablePanel.setProgressBarValue(count);
	}
	
	public void setProgressBarMaximum(int pixelCount) {
		colorTablePanel.setProgressBarMaximum(pixelCount);
	}
	
	public void updateFileNameField() {
		fileChooserPanel.updateFileNameField();
	}
	
	public void updateImagePanel() {
		frame.remove(imagePanel.getPanel());
		imagePanel.updateImage();
		frame.add(imagePanel.getPanel(), BorderLayout.CENTER);
		frame.pack();
	}

}
