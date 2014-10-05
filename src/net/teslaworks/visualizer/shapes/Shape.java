package net.teslaworks.visualizer.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape {
	
	// Position
	public int x = 0;
	public int y = 0;
	
	// Color
	public int[] color = null;
	
	// DMX
	public String name = "";
	public int channel = 0;

	public Shape(int x, int y, int[] color, String name, int channel) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.name = name;
		this.channel = channel;
	}
	
    public abstract void paint(Graphics2D g2d, int channelValue);
}
