package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import org.dom4j.Element;

public abstract class Shape {

	// DMX
	public final String name;
	public final int channel;
	
	// Position
	public final int x;
	public final int y;
	
	// Color
	public final int red;
	public final int blue;
	public final int green;
	
	public static Shape makeShape(Element e) {
		String type = e.attributeValue("type");
		switch(type) {
		case "rect":
			return new Rectangle(e);
		}
		throw new IllegalArgumentException("Unknown shape type: " + e.asXML());
	}
	
	public Shape(Element e) {
    	name = e.attributeValue("name");
    	channel = Integer.parseInt(e.attributeValue("channel"));
    	
    	x = Integer.parseInt(e.attributeValue("x"));
    	y = Integer.parseInt(e.attributeValue("y"));
    	
    	red = Integer.parseInt(e.attributeValue("red"));
    	blue = Integer.parseInt(e.attributeValue("blue"));
    	green = Integer.parseInt(e.attributeValue("green"));
	}
	
	public String toString() {
		return name;
	}
	
    public abstract void paint(Graphics2D g2d, int channelValue);
}
