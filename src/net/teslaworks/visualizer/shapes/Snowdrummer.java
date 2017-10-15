package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;

public class Snowdrummer extends Shape {

	// Shape size
	public final int height;

	// Set values unique to rectangles
	protected Snowdrummer(Element e) {
		super(e);
		height = Integer.parseInt(e.attributeValue("height"));
	}

	// Draw this oval
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.translate(x, y);

		paintBodyWork(g2d);
		paintDrumWork(g2d, channelValues);
		paintArmsWork(g2d, channelValues);

		g2d.translate(0 - x, 0 - y);
	}

	private void paintBodyWork(Graphics2D g2d) {
		// Needs to be called first, so paint is already set.
		// head, torso, legs
		g2d.drawOval((3 * height) / 20, 0, height / 5, height / 5);
		g2d.drawOval(height / 10, height / 5, (3 * height) / 10, (3 * height / 10));
		g2d.drawOval(0, height / 2, height / 2, height / 2);
	}

	private void paintArmsWork(Graphics2D g2d, int[] channelValues) {
		// right drumming arm
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 1]));
		g2d.drawLine(height / 20, (10 * height) / 20, (3 * height) / 20, (7 * height) / 20);
		g2d.drawLine(height / 20, (10 * height) / 20, (3 * height) / 20, (13 * height) / 20);

		// left drumming arm
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 2]));
		g2d.drawLine((9 * height) / 20, (10 * height) / 20, (7 * height) / 20, (7 * height) / 20);
		g2d.drawLine((9 * height) / 20, (10 * height) / 20, (7 * height) / 20, (13 * height) / 20);

		// right raised arm
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 3]));
		g2d.drawLine(height / 20, (4 * height) / 20, (3 * height) / 20, (7 * height) / 20);
		g2d.drawLine(height / 20, (4 * height) / 20, (3 * height) / 20, height / 20);

		// left raised arm
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 4]));
		g2d.drawLine((9 * height) / 20, (4 * height) / 20, (7 * height) / 20, (7 * height) / 20);
		g2d.drawLine((9 * height) / 20, (4 * height) / 20, (7 * height) / 20, height / 20);
	}

	private void paintDrumWork(Graphics2D g2d, int[] channelValues) {
		// drum
		g2d.setPaint(new Color(255, 0, 0, channelValues[channel]));
		g2d.translate(height / 10, (13 * height) / 20);

		// drum sides
		g2d.drawLine(0, 0, 0, height / 5);
		g2d.drawLine((3 * height) / 10, 0, (3 * height) / 10, height / 5);

		// drum top and bottom
		g2d.drawOval(0, -3 - (height / 20), (3 * height) / 10, height / 5);
		g2d.drawArc(0, -3 + ((3 * height) / 20), (3 * height) / 10, height / 5, 180, 180);

		// end drum
		g2d.translate(0 - (height / 10), 0 - ((13 * height) / 20));
	}
}
