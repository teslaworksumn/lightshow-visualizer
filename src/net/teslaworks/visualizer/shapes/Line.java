package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;

import org.dom4j.Element;

public class Line extends Shape {

	// Shape size
	public final int x2;
	public final int y2;
	public final boolean relative;

	// Set values unique to rectangles
	protected Line(Element e) {
		super(e);
		x2 = Integer.parseInt(e.attributeValue("x2"));
		y2 = Integer.parseInt(e.attributeValue("y2"));
		relative = Boolean.parseBoolean(e.attributeValue("relative"));
	}

	// Draw this line
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		if (relative) {
			g2d.drawLine(x, y, x + x2, y + y2);
		} else {
			g2d.drawLine(x, y, x2, y2);
		}
	}
}
