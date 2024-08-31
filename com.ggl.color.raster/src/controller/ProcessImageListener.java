package com.ggl.color.raster.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.ggl.color.raster.model.ColorRasterModel;
import com.ggl.color.raster.model.Raster;
import com.ggl.color.raster.view.ColorRasterFrame;

public class ProcessImageListener implements ActionListener {

	private final ColorRasterFrame view;

	private final ColorRasterModel model;

	public ProcessImageListener(ColorRasterFrame view, ColorRasterModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		view.updateImagePanel();
		
		BufferedImage image = model.getImage();
		Map<Integer, List<Point>> colorMap = model.getColorMap();
		colorMap.clear();
	
		Raster raster = model.getRaster();
		raster.setDimension(new Dimension(image.getWidth(), image.getHeight()));
		view.setProgressBarMaximum(image.getWidth() * image.getHeight());
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int row = 0; row < image.getHeight(); row++) {
					for (int column = 0; column < image.getWidth(); column++) {
						int colorValue = image.getRGB(column, row);
						Point point = new Point(column, row);
						List<Point> points = colorMap.get(colorValue);
						if (points == null) {
							points = new ArrayList<>();
							points.add(point);
							colorMap.put(Integer.valueOf(colorValue), points);
						} else {
							points.add(point);
						}
					}
					
					int count = (row + 1) * image.getWidth();
					updateProgressBar(count);
				}
				
				addTableData();
			}
			
			private void updateProgressBar(int count) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						view.setProgressBarValue(count);
					}
				});
			}
			
			private void addTableData() {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						view.addTableData();
					}
				});
			}
		};
		
		new Thread(runnable).start();
	}

}
