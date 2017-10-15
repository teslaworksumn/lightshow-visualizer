package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class Sun extends Shape {

	private static class Spoke {
		final int channel;
		final int red, green, blue;
		final int endX, endY, oX, oY;

		Spoke(int channel, int red, int green, int blue, int endX, int endY, int oX, int oY) {
			this.channel = channel;

			this.red = red;
			this.green = green;
			this.blue = blue;

			this.endX = endX;
			this.endY = endY;

			this.oX = oX;
			this.oY = oY;
		}
	}

	public final int width;
	public final int height;
	public final int originX;
	public final int originY;
	public final List<Spoke> spokes;

	@SuppressWarnings("unchecked")
	protected Sun(Element e) {
		super(e);

		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));

		int widthRadius = width;
		originX = x + widthRadius;
		originY = y + height;

		spokes = new ArrayList<>();

		int spokeCount = Integer.parseInt(e.attributeValue("spokes"));
		double angleSize = (2 * Math.PI) / (spokeCount);

		int k = 0;
		for (int i = 0; i < spokeCount * 3; i++) {
			int endX1 = (int) (originX + (Math.cos(angleSize * k) * widthRadius / 3));
			int endY1 = (int) (originY - (Math.sin(angleSize * k) * height / 3));
			int endX2 = (int) (originX + (Math.cos(angleSize * k) * (2 * widthRadius / 3)));
			int endY2 = (int) (originY - (Math.sin(angleSize * k) * (2 * height / 3)));
			int endX3 = (int) (originX + (Math.cos(angleSize * k) * widthRadius));
			int endY3 = (int) (originY - (Math.sin(angleSize * k) * height));

			spokes.add(new Spoke(channel + i, 255, 0, 0, endX1, endY1, originX, originY));
			i++;
			spokes.add(new Spoke(channel + i, 0, 255, 0, endX2, endY2, endX1, endY1));
			i++;
			spokes.add(new Spoke(channel + i, 0, 0, 255, endX3, endY3, endX2, endY2));
			k++;
		}
	}

	// Draw this fan
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		for (Spoke s : spokes) {
			g2d.setColor(new Color(s.red, s.green, s.blue, channelValues[s.channel]));
			g2d.drawLine(s.oX, s.oY, s.endX, s.endY);
		}
	}
}
