package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import net.teslaworks.visualizer.ConfigXMLParser;

import org.dom4j.Element;

public class Rectangle extends Shape {
	
	// Shape size
	public int width = 0;
	public int height = 0;
	public boolean fillMode = false;

	public Rectangle(int x, int y, int[] color, String name, int channel) {
		super(x, y, color, name, channel);
	}
	
	public Rectangle(Element e) {
        this(
        	Integer.parseInt(e.attributeValue("x")),
        	Integer.parseInt(e.attributeValue("y")),
        	ConfigXMLParser.parseColor(e.attributeValue("color")),
        	e.attributeValue("name"),
        	Integer.parseInt(e.attributeValue("channel")));
        width = Integer.parseInt(e.attributeValue("width"));
        height = Integer.parseInt(e.attributeValue("height"));
        fillMode = "fill".equals(e.attributeValue("mode"));
	}
	
	public String toString() {
		String result = name + " " + x + " " + y + " " + width + " " + height 
				+ " " + color[0] + " " + color[1] + " " + color[2] + " " + channel;
		if (fillMode) {
			return result + " true";
		}
		else {
			return result + " false";
		}
	}

    public void paint(Graphics2D g2d, int channelValue) {
    	g2d.setColor(new Color(color[0], color[1], color[2], channelValue));
    	g2d.fillRect(x, y, width, height);
    }
}
