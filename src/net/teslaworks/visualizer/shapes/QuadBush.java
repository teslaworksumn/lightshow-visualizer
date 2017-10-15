package net.teslaworks.visualizer.shapes;

import org.dom4j.Element;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Created by ILS on 11/2/16.
 */
public class QuadBush extends Shape {

	// Shape size
	public final int width;
	public final int height;

	// Secondary colors
	public final int red2, green2, blue2, red3, green3, blue3, red4, green4, blue4;
	private final int offset;

	// Set values unique to rectangles
	protected QuadBush(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));
		offset = Integer.parseInt(e.attributeValue("offset", "1"));

		red2 = Integer.parseInt(e.attributeValue("red2", "0"));
		green2 = Integer.parseInt(e.attributeValue("green2", "0"));
		blue2 = Integer.parseInt(e.attributeValue("blue2", "0"));

		red3 = Integer.parseInt(e.attributeValue("red3", "0"));
		green3 = Integer.parseInt(e.attributeValue("green3", "0"));
		blue3 = Integer.parseInt(e.attributeValue("blue3", "0"));

		red4 = Integer.parseInt(e.attributeValue("red4", "0"));
		green4 = Integer.parseInt(e.attributeValue("green4", "0"));
		blue4 = Integer.parseInt(e.attributeValue("blue4", "0"));

	}

	protected TexturePaint makeTexturePaint(int[] channelValues) {
		int size = 12;
		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();

		// Red
		g2d.setPaint(new Color(red, green, blue, channelValues[channel]));
		g2d.fill(new java.awt.Rectangle(0, 0, 2, 2));
		g2d.fill(new java.awt.Rectangle(4, 4, 2, 2));
		g2d.fill(new java.awt.Rectangle(8, 8, 2, 2));
		g2d.fill(new java.awt.Rectangle(12, 12, 2, 2));

		// Green
		g2d.setPaint(new Color(red2, green2, blue2, channelValues[channel + offset]));
		g2d.fill(new java.awt.Rectangle(4, 0, 2, 2));
		g2d.fill(new java.awt.Rectangle(8, 4, 2, 2));
		g2d.fill(new java.awt.Rectangle(12, 8, 2, 2));
		g2d.fill(new java.awt.Rectangle(0, 12, 2, 2));

		// Blue
		g2d.setPaint(new Color(red3, green3, blue3, channelValues[channel + 2 * offset]));
		g2d.fill(new java.awt.Rectangle(8, 0, 2, 2));
		g2d.fill(new java.awt.Rectangle(12, 4, 2, 2));
		g2d.fill(new java.awt.Rectangle(0, 8, 2, 2));
		g2d.fill(new java.awt.Rectangle(4, 12, 2, 2));

		// Warm White
		g2d.setPaint(new Color(red4, green4, blue4, channelValues[channel + 3 * offset]));
		g2d.fill(new java.awt.Rectangle(12, 0, 2, 2));
		g2d.fill(new java.awt.Rectangle(0, 4, 2, 2));
		g2d.fill(new java.awt.Rectangle(4, 8, 2, 2));
		g2d.fill(new java.awt.Rectangle(8, 12, 2, 2));

		TexturePaint paint = new TexturePaint(image, new Rectangle(0, 0, size, size));
		g2d.dispose();

		return paint;
	}

	// Draw this rectangle
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.setPaint(makeTexturePaint(channelValues));
		g2d.fill(new java.awt.Rectangle(x, y, width, height));
	}

}
