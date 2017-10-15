package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;

import org.dom4j.Element;

public class Oval extends Shape {

	// Shape size
	public final int width;
	public final int height;
	public final boolean fill;

	// Set values unique to rectangles
	protected Oval(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));
		fill = Boolean.parseBoolean(e.attributeValue("fill"));
	}

	// Draw this oval
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		if (fill) {
			g2d.fillOval(x, y, width, height);
		} else {
			g2d.drawOval(x, y, width, height);
		}
	}
}
