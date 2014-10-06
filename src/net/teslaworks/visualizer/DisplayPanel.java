package net.teslaworks.visualizer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import net.teslaworks.visualizer.shapes.Shape;

class DisplayPanel extends JPanel {
	
	List<Shape> shapes;
	int[] channelValues;
	
	public DisplayPanel(int[] channelValues, List<Shape> shapes) {
		this.channelValues = channelValues;
		this.shapes = shapes;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        for (Shape shape : shapes) {
        	shape.paint(g2d, channelValues[shape.channel]);
        }
    }
}