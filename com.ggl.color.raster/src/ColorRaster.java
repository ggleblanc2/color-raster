package com.ggl.color.raster;

import javax.swing.SwingUtilities;

import com.ggl.color.raster.model.ColorRasterModel;
import com.ggl.color.raster.view.ColorRasterFrame;

public class ColorRaster implements Runnable {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1");
		SwingUtilities.invokeLater(new ColorRaster());
	}

	@Override
	public void run() {
		new ColorRasterFrame(new ColorRasterModel());
	}

}
