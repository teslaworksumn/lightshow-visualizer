package net.teslaworks.visualizer.shapes;

import java.awt.Graphics2D;
import java.awt.Color;
import org.dom4j.Element;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class Arch extends Shape {
	
	private static class SmallArc {
		final int channel;
		final int red, green, blue;
		final int sweepAngle;
		
		SmallArc(int channel, int red, int green, int blue, int sections) {
			this.channel = channel;
			this.red = red;
			this.green = green;
			this.blue = blue;
			sweepAngle = 180 / sections;
		}
	}
	
	//Random rnd = new Random();
	
	public final int SEMI_CIRCLE_DEG = 180;
	public final int GAP = 25; // might not need
	public final int width;
	public final int height;
	public final int sections;
	public final List<SmallArc> smallArcs;
	
	protected Arch(Element e) {
		super(e);
		width = Integer.parseInt(e.attributeValue("width"));
		height = Integer.parseInt(e.attributeValue("height"));
		sections = Integer.parseInt(e.attributeValue("sections"));
		smallArcs = new ArrayList<>();
		
		for(int i = 0; i < sections; i++) { // add each arch section
			smallArcs.add(new SmallArc(channel + i, red, green, blue, sections));
			//smallArcs.add(new SmallArc(channel + i, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), sections));
		}
		
	}
	
	public void paintWork(Graphics2D g2d, int[] channelValues) {
		int arcAngle = SEMI_CIRCLE_DEG / sections;
		int startAngle = SEMI_CIRCLE_DEG;
		
		for (SmallArc s : smallArcs) {
			g2d.setColor(new Color(s.red, s.green, s.blue, channelValues[s.channel]));
			g2d.drawArc(x, y, width, height, startAngle, -s.sweepAngle);
			startAngle -= arcAngle;
		}
		/*
		for(int i = 0; i < sections; i++) {
			System.out.println("startAngle: " + startAngle);
			System.out.println("arcAngle: " + arcAngle);
			g2d.drawArc(x, y, width, height, startAngle, -(arcAngle - (GAP / 3)));
			startAngle -= arcAngle;
		}*/
	}
}