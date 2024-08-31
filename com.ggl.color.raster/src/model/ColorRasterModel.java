package com.ggl.color.raster.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ColorRasterModel {
	
	private BufferedImage image;
	
	private File directory;
	
	private final Map<Integer, List<Point>> colorMap;
	
	private final Raster raster;

	public ColorRasterModel() {
		this.directory = new File(System.getProperty("user.home"));
		this.colorMap = new TreeMap<>();
		this.raster = new Raster();
	}

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Map<Integer, List<Point>> getColorMap() {
		return colorMap;
	}

	public Raster getRaster() {
		return raster;
	}
	
}
