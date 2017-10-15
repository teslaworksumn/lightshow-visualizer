package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.Rectangle;

public class Snowguitarist extends Shape {

	// Shape size
	public final int BASE_HEIGHT = 80;
	private final int height;
	private final double scale;

	// Set values unique to rectangles
	protected Snowguitarist(Element e) {
		super(e);
		height = Integer.parseInt(e.attributeValue("height"));
		scale = (1.0 * height) / BASE_HEIGHT;
	}

	// Draw this oval
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.translate(x, y);
		g2d.scale(scale, scale);

		paintBodyWork(g2d);
		paintGuitarWork(g2d, channelValues);
		paintArmsWork(g2d, channelValues);

		g2d.scale(1.0 / scale, 1.0 / scale);
		g2d.translate(0 - x, 0 - y);
	}

	private void paintBodyWork(Graphics2D g2d) {
		// Needs to be called first, so paint is already set.
		// head, torso, legs
		g2d.drawOval((3 * BASE_HEIGHT) / 20, 0, BASE_HEIGHT / 5, BASE_HEIGHT / 5);
		g2d.drawOval(BASE_HEIGHT / 10, BASE_HEIGHT / 5, (3 * BASE_HEIGHT) / 10, (3 * BASE_HEIGHT / 10));
		g2d.drawOval(0, BASE_HEIGHT / 2, BASE_HEIGHT / 2, BASE_HEIGHT / 2);
	}

	private void paintArmsWork(Graphics2D g2d, int[] channelValues) {
		// right arm up
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 1]));
		g2d.drawLine(13, 25, 3, 38);
		g2d.drawLine(3, 38, 20, 35);

		// right arm down
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 2]));
		g2d.drawLine(13, 25, 3, 38);
		g2d.drawLine(3, 38, 20, 55);

		// left arm up
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 3]));
		g2d.drawLine(27, 25, 45, 50);
		g2d.drawLine(45, 50, 65, 29);

		// left arm down
		g2d.setPaint(new Color(255, 255, 0, channelValues[channel + 4]));
		g2d.drawLine(27, 25, 45, 50);
		g2d.drawLine(45, 50, 50, 33);
	}

	private void paintGuitarWork(Graphics2D g2d, int[] channelValues) {
		g2d.setPaint(new Color(255, 0, 0, channelValues[channel]));
		g2d.rotate(Math.toRadians(-20));

		g2d.draw(new Rectangle(-10, 43, 30, 18));
		g2d.drawLine(-5, 48, 55, 48);
		g2d.drawLine(-5, 52, 55, 52);
		g2d.drawLine(-5, 56, 55, 56);

		g2d.rotate(Math.toRadians(20));
	}
}
