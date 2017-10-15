package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;

public class Snowflake extends Shape {

	// Shape size
	private final int halfWidth, halfHeight;

	// Set values unique to rectangles
	protected Snowflake(Element e) {
		super(e);
		int width = Integer.parseInt(e.attributeValue("width"));
		int height = Integer.parseInt(e.attributeValue("height"));

		halfWidth = width / 2;
		halfHeight = height / 2;
	}

	// Draw this line
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.translate(x + halfWidth, y + halfHeight);

		g2d.drawLine(0, halfHeight, 0, 0 - halfHeight);
		g2d.rotate(Math.toRadians(60));
		g2d.drawLine(0, halfHeight, 0, 0 - halfHeight);
		g2d.rotate(Math.toRadians(60));
		g2d.drawLine(0, halfHeight, 0, 0 - halfHeight);
		g2d.rotate(Math.toRadians(-120));

		g2d.translate(0 - (x + halfWidth), 0 - (y + halfHeight));
	}
}
