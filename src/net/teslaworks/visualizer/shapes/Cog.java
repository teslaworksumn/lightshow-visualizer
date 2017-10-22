package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import org.dom4j.Element;

public class Cog extends Shape {
	
	public final int width;
	public final int red2, green2, blue2;
	
	protected Cog(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		red2 = Integer.parseInt(e.attributeValue("red2"));
		green2 = Integer.parseInt(e.attributeValue("green2"));
		blue2 = Integer.parseInt(e.attributeValue("blue2"));
	}
	
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		g2d.fillArc(x, y, width, width, 90, -180);
		g2d.setPaint(new Color(red2, green2, blue2, channelValues[channel]));
		g2d.fillArc(x, y, width, width, 90, 180);
	}
}