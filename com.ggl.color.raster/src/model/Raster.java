package com.ggl.color.raster.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class Raster {
	
	private Dimension dimension;
	
	private final List<PixelColor> pixelColorList;
	
	public Raster() {
		this.pixelColorList = new ArrayList<>();
	}
	
	public void clearPixelColorList() {
		this.pixelColorList.clear();
	}
	
	public void addPixelColor(PixelColor pixelColor) {
		this.pixelColorList.add(pixelColor);
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public List<PixelColor> getPixelColorList() {
		return pixelColorList;
	}

}
