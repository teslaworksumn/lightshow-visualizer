package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import org.dom4j.Element;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class Arch extends Shape {
	
	private static class SmallArc {
		final int channel;
		final int red, green, blue;
		final int sweepAngle;
		
		SmallArc(int channel, int red, int green, int blue, int sweepAngle) {
			this.channel = channel;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.sweepAngle = sweepAngle;
		}
	}
	
	public final int SEMI_CIRCLE_DEG = 180;
	public final int width;
	public final int height;
	public final List<SmallArc> smallArcs;
	
	protected Arch(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));
		smallArcs = new ArrayList<>();
		
		List<Element> arcElements = (List<Element>) e.elements("arc");
		int arcCount = arcElements.size();
		
		for(int i = 0; i < arcCount; i++) { // add each arch section
			Element arcElement = arcElements.get(i);
			
			int r = Integer.parseInt(arcElement.attributeValue("red"));
			int g = Integer.parseInt(arcElement.attributeValue("green"));
			int b = Integer.parseInt(arcElement.attributeValue("blue"));
			
			smallArcs.add(new SmallArc(channel + i, r, g, b, SEMI_CIRCLE_DEG / arcCount));
		}
		
	}
	
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		int startAngle = SEMI_CIRCLE_DEG;
		
		for (SmallArc s : smallArcs) {
			g2d.setColor(new Color(s.red, s.green, s.blue, channelValues[s.channel]));
			g2d.drawArc(x, y, width, height, startAngle, -s.sweepAngle);
			startAngle -= s.sweepAngle;
		}
	}
}