package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import net.teslaworks.visualizer.ConfigXML;

import org.dom4j.Element;

public class Rectangle extends Shape {

	// Shape size
	public final int width;
	public final int height;
	public final boolean fillMode;

	// Set values unique to rectangles
	protected Rectangle(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));
		fillMode = "fill".equals(e.attributeValue("mode"));
	}

	// Draw this rectangle
	public void paint(Graphics2D g2d, int channelValue) {
		super.paint(g2d, channelValue);
		g2d.fillRect(x, y, width, height);
	}
}
