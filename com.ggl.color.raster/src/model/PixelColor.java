package com.ggl.color.raster.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PixelColor {
	
	private final int colorValue;
	
	private final List<Point> points;

	public PixelColor(int colorValue) {
		this.colorValue = colorValue;
		this.points = new ArrayList<>();
	}

	public int getColorValue() {
		return colorValue;
	}

	public List<Point> getPoints() {
		return points;
	}

}
